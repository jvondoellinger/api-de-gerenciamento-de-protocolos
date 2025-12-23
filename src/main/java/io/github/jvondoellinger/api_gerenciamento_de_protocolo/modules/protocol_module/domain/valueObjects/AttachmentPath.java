package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.io.FilenameUtils;

import java.time.Clock;
import java.time.Instant;
import java.util.Objects;
import java.util.regex.Pattern;

public final class AttachmentPath {
	private static final Clock DEFAULT_CLOCK = Clock.systemUTC();

	// Esse formato espera: Numero de protocolo/Timestamp.EXTENSAO
	private static final String PATH_FORMAT = "%s/%d.%s";

	private final String filename;
	private final ProtocolNumber protocolNumber;
	private final String extension;
	private final long epochMilli;
	private String value;

	private AttachmentPath(String filename, ProtocolNumber protocolNumber) {
		this.filename = filename;
		this.protocolNumber = protocolNumber;

		this.extension = FilenameUtils.getExtension(filename);
		this.epochMilli = Instant.now(DEFAULT_CLOCK).toEpochMilli();

		value = extract();
	}

	public AttachmentPath(String filename, ProtocolNumber protocolNumber, String extension, long epochMilli) {
		this.filename = filename;
		this.protocolNumber = protocolNumber;
		this.extension = extension;
		this.epochMilli = epochMilli;

		value = extract();
	}

	public static AttachmentPath create(String filename, ProtocolNumber protocolNumber) {
		Objects.requireNonNull(filename, "Filename can't be null. Class: %s".formatted(AttachmentPath.class));
		Objects.requireNonNull(protocolNumber, "ProtocolNumber can't be null. Class: %s".formatted(AttachmentPath.class));

		return new AttachmentPath(filename, protocolNumber);
	}

	public static AttachmentPath parse(String value) {
		Objects.requireNonNull(value, "Filename can't be null. Class: %s".formatted(AttachmentPath.class));

		var pattern = Pattern.compile("^([^/]+)/(\\d+)\\.([^/.]+)$");
		var matcher = pattern.matcher(value);

		if(!matcher.matches()) {
			throw new RuntimeException("Invalid path format");
		}

		var protocol = matcher.group(1);
		var timestamp = matcher.group(2);
		var ext = matcher.group(3);

		var protocolNumber = ProtocolNumber.parse(protocol);
		var epoch = Long.parseLong(timestamp);

		return new AttachmentPath("", protocolNumber, ext, epoch);
	}


	private String extract() {
		return PATH_FORMAT.formatted(
			   protocolNumber.getValue(),
			   epochMilli,
			   extension
		);
	}


	@Override
	public String toString() {
		return value;
	}
}
