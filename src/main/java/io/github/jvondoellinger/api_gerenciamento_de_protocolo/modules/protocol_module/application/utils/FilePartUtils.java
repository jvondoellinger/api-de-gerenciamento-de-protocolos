package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.utils;

import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;

import java.nio.ByteBuffer;

public class FilePartUtils {
	private FilePartUtils() {
	}

	public static Flux<ByteBuffer> toByteBuffer(FilePart filePart) {
		return filePart.content()
			   .map(dataBuffer -> {
				   byte[] bytes = new byte[dataBuffer.readableByteCount()];
				   dataBuffer.read(bytes);
				   DataBufferUtils.release(dataBuffer);
				   return ByteBuffer.wrap(bytes);
			   });
	}
}
