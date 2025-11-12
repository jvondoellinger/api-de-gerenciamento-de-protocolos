package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.tests;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.exception.ValidationException;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.validations.ReactiveValidator;

public class testingReactiveValidator {
      public testingReactiveValidator() {
            var validator = new ReactiveValidator<Protocolo>();

            try {
                  validator
                          .ruleFor(Protocolo::getProtocolNumber)
                          .when(() -> false)
                          .withMessage("")
                          .evaluate(null);
            } catch (ValidationException e) {

            }

      }
}
