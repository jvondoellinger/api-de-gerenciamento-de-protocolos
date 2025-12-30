package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.tests;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.EnvironmentProfiles;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.helper.ReactiveLog;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.S3Config;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;

@Service
@Profile(EnvironmentProfiles.DEVELOPING)
public class LocalStackS3TestStorageService implements StorageService {
	private static final Logger log = LoggerFactory.getLogger(LocalStackS3TestStorageService.class);
	private final S3AsyncClient client;
	private final S3Config config;

	public LocalStackS3TestStorageService(S3AsyncClient client, S3Config config) {
		this.client = client;
		this.config = config;
	}

	public Mono<FileDetails> upload(String bucket, String key, Flux<ByteBuffer> stream) {
		var cache = stream.cache();
		var length = cache.map(ByteBuffer::remaining).reduce(0L, Long::sum);

		return length.flatMap(contentLength -> {
			var putObjectRequest = PutObjectRequest.builder()
				   .bucket(bucket)
				   .key(key)
				   .contentLength(contentLength)
				   .build();

			var body = AsyncRequestBody.fromPublisher(cache);
			var response = client.putObject(putObjectRequest, body);

			return Mono.fromFuture(response)
				   .map(x -> FileDetails.create(key, LocalDateTime.now()))
				   .timeout(config.getTimeout())
				   .doOnEach(ReactiveLog.onNext(log, "action=upload_attachment bucket={} key={} length={} status=completed", bucket, key, contentLength))
				   .doOnEach(ReactiveLog.onError(log, "action=upload_attachment bucket={} key={} length={} status=error", bucket, key, contentLength));
		});
	}

	public Flux<ByteBuffer> download(String bucket, String key) {
		var putObjectRequest = GetObjectRequest.builder()
			   .bucket(bucket)
			   .key(key)
			   .build();

		return Mono.fromFuture(
					 client.getObject(putObjectRequest, AsyncResponseTransformer.toPublisher())
			   )
			   .flatMapMany(Flux::from)
			   .timeout(config.getTimeout())
			   .doOnEach(ReactiveLog.onNext(log, "action=download_attachment bucket={} key={} status=completed", bucket, key))
			   .doOnEach(ReactiveLog.onError(log, "action=download_attachment bucket={} key={} status=error", bucket, key));
	}

	@Override
	public Flux<S3Object> searchPaths(String bucket, String prefix) {
		var request = ListObjectsV2Request.builder()
			   .bucket(bucket)
			   .prefix(prefix)
			   .build();

		return Mono.fromFuture(client.listObjectsV2(request))
			   .flatMapMany(response -> Flux.fromIterable(response.contents()))
			   .timeout(config.getTimeout())
			   .doOnEach(ReactiveLog.onNext(log, "action=download_attachment bucket={} prefix={} status=completed", bucket, prefix))
			   .doOnEach(ReactiveLog.onError(log, "action=download_attachment bucket={} prefix={} status=error", bucket, prefix));
	}
}
