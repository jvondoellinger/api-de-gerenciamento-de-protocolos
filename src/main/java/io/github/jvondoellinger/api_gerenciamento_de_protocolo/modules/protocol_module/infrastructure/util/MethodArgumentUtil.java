package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class MethodArgumentUtil {
      private static final String defaultError = "Missing data to get arguments";

      public  <T> T getRequest(Object[] args, Class<T> clazz, String errorMessage) {
            var error= Objects.requireNonNullElse(errorMessage, defaultError);

            return Arrays
                    .stream(args)
                    .filter(clazz::isInstance) // Get all args of this type
                    .map(x -> (T) x) // Cast
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException(errorMessage));
      }
}
