package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.api.resolvers;

import org.springframework.http.MediaType;

public final class ReactiveDownloadHeaders {
	private ReactiveDownloadHeaders() {}

	public static MediaType resolve(String filename) {
		String f = filename.toLowerCase();

		// IMAGENS
		if (f.endsWith(".png")) return MediaType.IMAGE_PNG;
		if (f.endsWith(".jpg") || f.endsWith(".jpeg")) return MediaType.IMAGE_JPEG;
		if (f.endsWith(".gif")) return MediaType.IMAGE_GIF;
		if (f.endsWith(".bmp")) return MediaType.valueOf("image/bmp");
		if (f.endsWith(".webp")) return MediaType.valueOf("image/webp");
		if (f.endsWith(".svg")) return MediaType.valueOf("image/svg+xml");
		if (f.endsWith(".tiff") || f.endsWith(".tif")) return MediaType.valueOf("image/tiff");

		// VÍDEOS
		if (f.endsWith(".mp4")) return MediaType.valueOf("video/mp4");
		if (f.endsWith(".webm")) return MediaType.valueOf("video/webm");
		if (f.endsWith(".avi")) return MediaType.valueOf("video/x-msvideo");
		if (f.endsWith(".mov")) return MediaType.valueOf("video/quicktime");
		if (f.endsWith(".mkv")) return MediaType.valueOf("video/x-matroska");
		if (f.endsWith(".ogg")) return MediaType.valueOf("video/ogg");

		// AUDIOS
		if (f.endsWith(".mp3")) return MediaType.valueOf("audio/mpeg");
		if (f.endsWith(".wav")) return MediaType.valueOf("audio/wav");
		if (f.endsWith(".aac")) return MediaType.valueOf("audio/aac");
		if (f.endsWith(".flac")) return MediaType.valueOf("audio/flac");
		if (f.endsWith(".m4a")) return MediaType.valueOf("audio/mp4");
		if (f.endsWith(".ogg")) return MediaType.valueOf("audio/ogg");

		// DOCUMENTOS
		if (f.endsWith(".pdf")) return MediaType.APPLICATION_PDF;
		if (f.endsWith(".txt")) return MediaType.TEXT_PLAIN;
		if (f.endsWith(".csv")) return MediaType.valueOf("text/csv");
		if (f.endsWith(".json")) return MediaType.APPLICATION_JSON;
		if (f.endsWith(".xml")) return MediaType.APPLICATION_XML;
		if (f.endsWith(".html") || f.endsWith(".htm")) return MediaType.TEXT_HTML;
		if (f.endsWith(".md")) return MediaType.valueOf("text/markdown");
		if (f.endsWith(".doc")) return MediaType.valueOf("application/msword");
		if (f.endsWith(".docx")) return MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		if (f.endsWith(".xls")) return MediaType.valueOf("application/vnd.ms-excel");
		if (f.endsWith(".xlsx")) return MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		if (f.endsWith(".ppt")) return MediaType.valueOf("application/vnd.ms-powerpoint");
		if (f.endsWith(".pptx")) return MediaType.valueOf("application/vnd.openxmlformats-officedocument.presentationml.presentation");

		// COMPACTADOS
		if (f.endsWith(".zip")) return MediaType.valueOf("application/zip");
		if (f.endsWith(".rar")) return MediaType.valueOf("application/vnd.rar");
		if (f.endsWith(".7z")) return MediaType.valueOf("application/x-7z-compressed");
		if (f.endsWith(".tar")) return MediaType.valueOf("application/x-tar");
		if (f.endsWith(".gz")) return MediaType.valueOf("application/gzip");

		// FALLBACK
		return MediaType.APPLICATION_OCTET_STREAM;
	}

	public static boolean isInline(MediaType type) {
		return type.getType().equals("image")
			   || type.getType().equals("video")
			   || type.getType().equals("audio");
	}
}
