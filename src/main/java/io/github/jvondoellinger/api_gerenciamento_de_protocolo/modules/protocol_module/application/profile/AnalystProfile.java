package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.profile;


public class AnalystProfile implements ProtocolProfile {

      @Override
      public String getRule() {
            return ProtocolProfiles.ANALYST_RULE;
      }

      @Override
      public boolean compatible(String rule) {
            return ProtocolProfiles.ANALYST_RULE.equals(rule);
      }
}
