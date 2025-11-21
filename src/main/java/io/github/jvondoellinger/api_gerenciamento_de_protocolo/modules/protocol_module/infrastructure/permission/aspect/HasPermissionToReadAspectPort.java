package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.aspect;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToQuery;
import org.aspectj.lang.ProceedingJoinPoint;

public interface HasPermissionToReadAspectPort {
      Object checkPermissions(ProceedingJoinPoint pjp, HasPermissionToQuery permission) throws Throwable;
}
