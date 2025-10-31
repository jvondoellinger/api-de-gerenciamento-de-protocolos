package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.handlers;

public interface Resolver<T, Y> {
    T resolve(Y value);
}
