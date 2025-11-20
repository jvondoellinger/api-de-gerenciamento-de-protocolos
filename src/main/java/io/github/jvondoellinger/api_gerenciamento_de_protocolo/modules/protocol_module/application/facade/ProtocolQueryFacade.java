package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.facade;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.mounter.ProtocolMounterHelper;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.application.query.ProtocolQueryByProtocolNumberRequest;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Interaction;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.InteractionHistory;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Protocolo;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.Queue;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.InteractionsReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.ProtocolReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.QueuesReadRepository;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.contracts.persistence.filters.ProtocolNumberFilter;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.DomainId;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.valueObjects.ProtocolNumber;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.annotation.HasPermissionToQuery;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.checker.PublishPermissionChecker;
import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.infrastructure.permission.enums.ReadPermissions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

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

    @HasPermissionToQuery
    public Mono<Protocolo> query(ProtocolQueryByProtocolNumberRequest request) {
        // Filters
        var pn = ProtocolNumber.parse(request.protocolNumber());
        var filter = ProtocolNumberFilter.create(pn);

        // Monos
        var protocolMono = protocolReadRepository.query(filter).cache(Duration.ofSeconds(15));  // TTL (time to live) para evitar de caching infinito
        var interactionsMono = interactionsReadRepository.query(filter).collectList();
        var queueMono = protocolMono.flatMap(x -> queuesReadRepository.query(x.getQueue().getId()));

        // Mounting and returning complete protocol
        return mounterHelper.mount(protocolMono, queueMono, interactionsMono);
    }
}
