package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Configuration;

@Configuration
public class S3Connection {
	private final S3Properties properties;

	public S3Connection(S3Properties properties) {
		this.properties = properties;
	}

	@Bean
	public S3AsyncClient s3AsyncClient() {
		var region = Region.of(properties.getRegion());
		var endpoint = properties.getEndpoint();
		var basicCredentials = AwsBasicCredentials.create(properties.getAccessKey(), properties.getSecretKey());
		var staticCredentials = StaticCredentialsProvider.create(basicCredentials);
		var httpClient = NettyNioAsyncHttpClient.builder().maxConcurrency(100).build();
		var serviceConfiguration = S3Configuration.builder()
			   .pathStyleAccessEnabled(true)
			   .build();

		return S3AsyncClient.builder()
			   .region(region)
			   .endpointOverride(endpoint)
			   .credentialsProvider(staticCredentials)
			   .httpClient(httpClient)
			   .serviceConfiguration(serviceConfiguration)
			   .build();
	}
}
