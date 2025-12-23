package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import java.time.LocalDateTime;

public class FileDetails {
	private String path;
	private LocalDateTime uploadedAt;

	private FileDetails(String path, LocalDateTime uploadedAt) {
		this.path = path;
		this.uploadedAt = uploadedAt;
	}

	public static FileDetails create(String path, LocalDateTime uploadedAt) {
		return new FileDetails(path, uploadedAt);
	}

	public String getPath() {
		return path;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}
}
