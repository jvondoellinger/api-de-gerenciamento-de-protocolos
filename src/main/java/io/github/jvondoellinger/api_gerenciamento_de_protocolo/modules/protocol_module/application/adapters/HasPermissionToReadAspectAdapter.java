package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.adapters;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.requests.ProtocolQueryRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.PermissionFactory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToQuery;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect.HasPermissionToReadAspectPort;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.MethodArgumentUtil;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.ReactiveObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HasPermissionToReadAspectAdapter implements HasPermissionToReadAspectPort {
      private final ReactiveObjectUtil reactiveObjectUtil;
      private final MethodArgumentUtil argumentUtil;
      private final PermissionChecker checker;
      public HasPermissionToReadAspectAdapter(ReactiveObjectUtil reactiveObjectUtil, MethodArgumentUtil argumentUtil, PermissionChecker checker) {
            this.reactiveObjectUtil = reactiveObjectUtil;
            this.argumentUtil = argumentUtil;
            this.checker = checker;
      }

      @Override
      @Around("@annotation(ann)")
      public Object checkPermissions(ProceedingJoinPoint pjp, HasPermissionToQuery ann) throws Throwable {
            var method = pjp.proceed();

            reactiveObjectUtil.throwIfNotReactive(method);
            // Parameters
            var parameters = pjp.getArgs();

            // Arguments
            var arg = argumentUtil.getRequest(parameters, ProtocolQueryRequest.class, null);
            var userId = DomainId.from(arg.getUserId());
            
            // Mono
            var permission = PermissionFactory.getInstance(ann.value().name());
            var check = checker.hasPermission(permission, userId);

            return reactiveObjectUtil.resolveReactiveObject(check, method);
      }
}
