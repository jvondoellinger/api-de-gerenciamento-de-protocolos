package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveObjectUtil {
      public boolean isReactive(Object method) {
            return (method instanceof Mono) || (method instanceof Flux);
      }

      public void throwIfNotReactive(Object method) {
            if (!isReactive(method))
                  throw new RuntimeException("This method i't reactive");
      }

      public Object resolveReactiveObject(Mono<?> continueFrom, Object method) {
            if (method instanceof Mono<?> mono)
                  return continueFrom.then(mono);
            else if (method instanceof Flux<?> flux)
                  return continueFrom.thenMany(flux);
            else
                  throw new IllegalArgumentException("Expected Mono or Flux from target method.");
      }
}
