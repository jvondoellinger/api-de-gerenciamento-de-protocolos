package io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain;

import io.github.jvondoellinger.api_gerenciamento_de_protocolo.modules.protocol_module.domain.annotiation.ImplementsAfter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InteractionHistory {
    private final List<Interaction> interactions;

    public InteractionHistory(List<Interaction> interactions) {
        this.interactions = interactions;
    }
    public InteractionHistory() {
        this.interactions = new ArrayList<>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.add(interaction);
    }
    public void addInteraction(Interaction... interaction) {
        interactions.addAll(List.of(interaction));
    }

    @ImplementsAfter
    public List<Interaction> getInteractions() {
        var comparator = Comparator.comparing(Interaction::getInteractedOn);
        interactions.sort(comparator); // Sort the interaction collection / Dando erro
        return List.copyOf(interactions); // Returns a immutable collection
    }

    public static InteractionHistory from(List<Interaction> interactions) {
        return new InteractionHistory(interactions);
    }
}
