package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.tests;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.EnvironmentProfiles;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.StorageService;
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
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Profile(EnvironmentProfiles.DEVELOPING)
public class LocalStackS3TestStorageService implements StorageService {
	private final S3AsyncClient client;
	private final Duration timeout = Duration.ofSeconds(15);

	public LocalStackS3TestStorageService(S3AsyncClient client) {
		this.client = client;
	}

	public Mono<FileDetails> upload(String bucket, String key, Flux<ByteBuffer> stream) {
		System.out.println("INFO: ACTIVATED LOCALSTACK/S3 TEST CLASS");

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
				   .timeout(timeout);
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
			   .timeout(timeout);
	}

	@Override
	public Flux<S3Object> searchPaths(String bucket, String prefix) {
		var request = ListObjectsV2Request.builder()
			   .bucket(bucket)
			   .prefix(prefix)
			   .build();
		return Mono.fromFuture(client.listObjectsV2(request))
			   .flatMapMany(response -> Flux.fromIterable(response.contents()));
	}
}
