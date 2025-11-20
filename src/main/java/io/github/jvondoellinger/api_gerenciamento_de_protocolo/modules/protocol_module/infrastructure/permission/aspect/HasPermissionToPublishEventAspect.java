package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToPublishEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PublishPermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions.EventNotActivatedByUserException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions.MissingPermissionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Arrays;

@Aspect
@Component
public class HasPermissionToPublishEventAspect {
      private final PublishPermissionChecker checker;
      public HasPermissionToPublishEventAspect(PublishPermissionChecker checker) {
            this.checker = checker;
      }

      /**
            * @exception IllegalArgumentException Trará essa exception quando o retorno do metodo não for um tipo reativo
            * @exception MissingPermissionException Trará essa exception quando não houver permissões suficientes para aquela ação
            * @return Retorna o mesmo tipo que foi-lhe entregue, desde que também seja um tipo reativo
       */
      @Around("@annotation(permission)")
      public Object checkPermissions(ProceedingJoinPoint pjp, HasPermissionToPublishEvent permission) throws Throwable {
            var method = pjp.proceed();

            throwIfNotReactive(method); // Caso não seja um tipo reativo (Mono ou Flux) dá uma exception

            var args = pjp.getArgs();

            var event = getEvent(args);

            var check = checker
                    .hasPermission(permission.value(), event.getUserId())
                    .doOnNext(this::throwIfNotAllowed)
                    .then();

            return resolveReactiveObject(check, method);
      }

      private Object resolveReactiveObject(Mono<Void> empty, Object method) {
            if (method instanceof Mono<?> mono)
                  return empty.then(mono);
            else if (method instanceof Flux<?> flux)
                  return empty.thenMany(flux);
            else
                  throw new IllegalArgumentException("Expected Mono or Flux from target method.");
      }

      private boolean isReactive(Object method) {
            return (method instanceof Mono) || (method instanceof Flux);
      }

      private void throwIfNotReactive(Object method) {
            if (!isReactive(method))
                  throw new RuntimeException("This method i't reactive");
      }

      private void throwIfNotAllowed(Boolean allowed) {
            if (!allowed)
                  throw new MissingPermissionException("Missing permission to do this.");
      }

      private UserActivityEvent getEvent(Object[] obj) {
            return Arrays
                    .stream(obj)
                    .filter(x -> x instanceof UserActivityEvent)
                    .findFirst()
                    .map(x -> (UserActivityEvent) x)
                    .orElseThrow(() -> new EventNotActivatedByUserException("Missing userId to activate this event"));
      }
}
