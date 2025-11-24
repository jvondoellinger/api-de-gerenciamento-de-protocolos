package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.stategies;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.profile.ProtocolProfile;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocol;

public interface ProfileRuleDispatcher {
	Protocol dispatch(ProtocolProfile profile);
}
