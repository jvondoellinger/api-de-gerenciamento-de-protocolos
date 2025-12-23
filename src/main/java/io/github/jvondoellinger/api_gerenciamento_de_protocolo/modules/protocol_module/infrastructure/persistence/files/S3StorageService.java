package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.FileDetails;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.EnvironmentProfiles;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.config.ProtocolS3Config;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Profile(EnvironmentProfiles.PRODUCTION)
public class S3StorageService implements StorageService {
	private final S3AsyncClient client;
	private final Duration timeout = Duration.ofSeconds(15);

	public S3StorageService(S3AsyncClient client) {
		this.client = client;
	}

	public Mono<FileDetails> upload(String bucket, String key, Flux<ByteBuffer> stream) {
		var putObjectRequest = PutObjectRequest.builder()
			   .bucket(bucket)
			   .key(key)
			   .build();

		var body = AsyncRequestBody.fromPublisher(stream);

		var response = client.putObject(putObjectRequest, body);

		return Mono.fromFuture(response)
			   .map(x -> FileDetails.create(key, LocalDateTime.now()))
			   .timeout(timeout);
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
}
