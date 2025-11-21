package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mounter.ProtocolMounterHelper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToQuery;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PublishPermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.ReadPermissions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ProtocolQueryFacade {
    private final ProtocolReadRepository protocolReadRepository;
    private final InteractionsReadRepository interactionsReadRepository;
    private final QueuesReadRepository queuesReadRepository;
    private final ProtocolMounterHelper mounterHelper;
    private final PublishPermissionChecker checker;

    public ProtocolQueryFacade(ProtocolReadRepository protocolReadRepository,
                               InteractionsReadRepository interactionsReadRepository,
                               QueuesReadRepository queuesReadRepository, ProtocolMounterHelper mounterHelper, PublishPermissionChecker checker) {
        this.protocolReadRepository = protocolReadRepository;
        this.interactionsReadRepository = interactionsReadRepository;
        this.queuesReadRepository = queuesReadRepository;
        this.mounterHelper = mounterHelper;
        this.checker = checker;
    }

    // Em sintese, essa annotation não representa com exatidão se há permissão ou não, pois, por definição,
    // necessita de ReadPermissions para validar (será alterado no proximo commit)
    // Se aquele protocolo contiver dados sensiveis, essa annotation não vai conseguir tratar (ou vai precisar de alterações)
    // proponho para de usar anotation e usar uma proxy que assuma esse papel de verificação
    // Assim, a proxy se passa pelo facade, verifica as permissões e segue o fluxo

    @HasPermissionToQuery(ReadPermissions.READ)
    public Mono<Protocolo> query(ProtocolQueryByProtocolNumberRequest request) {
        // Filters
        var pn = ProtocolNumber.parse(request.protocolNumber());
        var filter = ProtocolNumberFilter.create(pn);

        // TTL (time to live) para evitar de caching infinito
        var ttl = Duration.ofSeconds(15); // TTL

        // Monos
        var protocolMono = protocolReadRepository.query(filter).cache(ttl);
        var interactionsMono = interactionsReadRepository.query(filter).collectList();
        var queueMono = protocolMono.flatMap(x -> queuesReadRepository.query(x.getQueue().getId()));

        // Mounting and returning complete protocol
        return mounterHelper.mount(protocolMono, queueMono, interactionsMono);
    }
}
