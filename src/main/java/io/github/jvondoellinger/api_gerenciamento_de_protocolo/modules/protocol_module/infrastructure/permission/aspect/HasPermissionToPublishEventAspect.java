package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.UserActivityEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermission;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.exception.MissingPermissionException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.resolvers.PermissionsResolver;
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
      private final PermissionsResolver resolver;

      public HasPermissionToPublishEventAspect(PermissionChecker checker, ReactiveObjectUtil reactiveObjectUtil, MethodArgumentUtil argumentUtil, PermissionsResolver resolver) {
            this.checker = checker;
            this.reactiveObjectUtil = reactiveObjectUtil;
            this.argumentUtil = argumentUtil;
            this.resolver = resolver;
	 }

      /**
            * @exception IllegalArgumentException Trará essa exception quando o retorno do metodo não for um tipo reativo
            * @exception MissingPermissionException Trará essa exception quando não houver permissões suficientes para aquela ação
            * @return Retorna o mesmo tipo que foi-lhe entregue, desde que também seja um tipo reativo
       */
      @Around("@annotation(ann)")
      public Object checkPermissions(ProceedingJoinPoint pjp, HasPermission ann) throws Throwable {
            // Fields
            var method = pjp.proceed();
            reactiveObjectUtil.throwIfNotReactive(method);

            // Get method parameters
            var args = pjp.getArgs();

            // Get event by parameters
            var event = argumentUtil.getParameter(args,
                    UserActivityEvent.class,
                    "This method does not contain a user-triggered event.");

            // Checking...
            var userId = event.getUserId();
            var permissions = resolver.resolve(event);
            var check = checker.permittedOrThrow(permissions, userId);

            return reactiveObjectUtil.resolveReactiveObject(check, method);
      }

}
