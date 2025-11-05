package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.DomainException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ParsingException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ParsingFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.concurrent.ThreadLocalRandom;

public class ProtocolNumber {
    private final String value;
    private static final String timestamp_pattern = "yyMMddHHmmssSSS";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern(timestamp_pattern);


    // ? Contructors
    public ProtocolNumber(String value) {
        validate(value);
        this.value = value;
    }

    private ProtocolNumber() {
        value = random();
    }


    // ? Static methods
    public static ProtocolNumber generate() {
        return new ProtocolNumber();
    }

    public static ProtocolNumber parse(String protocolNumber) {
        return new ProtocolNumber(protocolNumber);
    }




    private String random() {
        var now = LocalDateTime.now();

        var formated = now.format(format); // Capturando o timestamp no formato: Ano Mes Dia Hora Minuto Segundo Milissegundos

        // Numero aleatorio para adicionar ao protocolo
        var randomNumber = ThreadLocalRandom.current().nextLong(1, 99999);

        return "%s%s".formatted(formated, randomNumber);
    }

    private void validate(String value) {
        try {
            var timestampLength = timestamp_pattern.length();

            // Vai validar se a entrada tem o tamanho o timestamp. Caso não tenha, é invalido.
            if (!(value.length() > timestampLength))
                throw new ParsingException("Invalid format");

            var extract = value.substring(0, timestampLength);
            LocalDateTime.parse(extract, format);

        } catch (DateTimeParseException exception) {
            throw new ParsingFormatException("Protocol number isn't valid.");
        } catch (NullPointerException exception) {
            throw new ParsingFormatException("It's not possible convert null value into a protocol number!.");
        }
    }

    public String getValue() {
        return value;
    }
}
