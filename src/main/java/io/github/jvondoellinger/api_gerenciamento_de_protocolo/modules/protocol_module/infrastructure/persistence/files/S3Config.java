package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.persistence.files;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@ConfigurationProperties("aws.s3")
public class S3Config {
	private Duration timeout;

	public Duration getTimeout() {
		return timeout;
	}

	public void setTimeout(Duration timeout) {
		this.timeout = timeout;
	}
}
