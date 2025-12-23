package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.text.AttributedString;

@Configuration
@ConfigurationProperties("aws.s3")
public class S3Properties {
	private String region;
	private URI endpoint;
	private String accessKey;
	private String secretKey;



	public String getRegion() {
		return region;
	}

	public URI getEndpoint() {
		return endpoint;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}



	public void setRegion(String region) {
		this.region = region;
	}

	public void setEndpoint(URI endpoint) {
		this.endpoint = endpoint;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
