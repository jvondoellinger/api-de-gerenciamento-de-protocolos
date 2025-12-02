package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.proxy;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.events.DomainEvent;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.events.validators.DynamicEventValidator;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.MethodArgumentUtil;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.util.ReactiveObjectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EventValidatorProxyAspect {
	private final ReactiveObjectUtil reactiveObjectUtil;
	private final MethodArgumentUtil argumentUtil;
	private final DynamicEventValidator dynamicValidator;

	public EventValidatorProxyAspect(ReactiveObjectUtil reactiveObjectUtil, MethodArgumentUtil argumentUtil, DynamicEventValidator dynamicValidator) {
		this.reactiveObjectUtil = reactiveObjectUtil;
		this.argumentUtil = argumentUtil;
		this.dynamicValidator = dynamicValidator;
	}


	@Around("@annotation(ann)")
	public Object checkPermissions(ProceedingJoinPoint pjp, EventValidatorProxy ann) throws Throwable {
		var method = pjp.proceed();
		var args = pjp.getArgs();
		var param = argumentUtil.getParameter(args, DomainEvent.class, "Any valid events found on method.");

		var validator = dynamicValidator.validate(param);

		return reactiveObjectUtil.resolveReactiveObject(validator, method);
	}
}
