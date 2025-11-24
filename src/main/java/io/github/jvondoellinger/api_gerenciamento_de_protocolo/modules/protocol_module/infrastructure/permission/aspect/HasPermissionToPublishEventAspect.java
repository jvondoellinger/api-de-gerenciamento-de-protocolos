package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToPublishEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exceptions.MissingPermissionException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.MethodArgumentUtil;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.ReactiveObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HasPermissionToPublishEventAspect {
      private final PermissionChecker checker;
      private final ReactiveObjectUtil reactiveObjectUtil;
      private final MethodArgumentUtil argumentUtil;
      public HasPermissionToPublishEventAspect(PermissionChecker checker, ReactiveObjectUtil reactiveObjectUtil, MethodArgumentUtil argumentUtil) {
            this.checker = checker;
            this.reactiveObjectUtil = reactiveObjectUtil;
            this.argumentUtil = argumentUtil;
      }

      /**
            * @exception IllegalArgumentException Trará essa exception quando o retorno do metodo não for um tipo reativo
            * @exception MissingPermissionException Trará essa exception quando não houver permissões suficientes para aquela ação
            * @return Retorna o mesmo tipo que foi-lhe entregue, desde que também seja um tipo reativo
       */
      @Around("@annotation(ann)")
      public Object checkPermissions(ProceedingJoinPoint pjp, HasPermissionToPublishEvent ann) throws Throwable {
            var method = pjp.proceed();

            reactiveObjectUtil.throwIfNotReactive(method);

            var args = pjp.getArgs();
            var event = argumentUtil.getRequest(args, UserActivityEvent.class, "Missing userId to activate this event");

            var permission = PermissionFactory.getInstance(ann.value().name());
            var check = checker
                    .hasPermission(permission, event.getUserId())
                    .doOnNext(this::throwIfNotAllowed);

            return reactiveObjectUtil.resolveReactiveObject(check, method);
      }

      private void throwIfNotAllowed(Boolean allowed) {
            if (!allowed)
                  throw new MissingPermissionException("Missing permission to do this.");
      }
}
