package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProtocolS3Config {
	private String bucket = "attachments";

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
}
