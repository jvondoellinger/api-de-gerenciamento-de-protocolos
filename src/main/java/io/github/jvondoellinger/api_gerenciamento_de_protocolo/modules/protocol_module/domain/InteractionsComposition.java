package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InteractionsComposition {
    private final List<Interaction> interactions;

    public InteractionsComposition(List<Interaction> interactions) {
        this.interactions = interactions;
    }
    public InteractionsComposition() {
        this.interactions = new ArrayList<>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    public List<Interaction> getInteractions() {
        interactions.sort(Comparator.comparing(Interaction::getInteractedOn)); // Sort the interaction collection
        return List.copyOf(interactions); // Returns a immutable collection
    }
}
