package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions.EventNotActivatedByUserException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions.MissingPermissionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class HasPermissionAspect {
      private final PermissionChecker checker;
      public HasPermissionAspect(PermissionChecker checker) {
            this.checker = checker;
      }

      @Around("@annotation(hasPermission)")
      public Object checkPermissions(ProceedingJoinPoint pjp, HasPermission hasPermission) throws Throwable {
            var args = pjp.getArgs();

            var event = getEvent(args);

            return checker
                    .hasPermission(hasPermission.value(), event.getUserId())
                    .doOnNext(allowed -> {
                        if (!allowed)
                              throw new MissingPermissionException("Missing permission to do this.");
                    });

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
