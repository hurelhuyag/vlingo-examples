package io.vlingo.developers.petclinic.model.veterinarian;

import io.vlingo.common.version.SemanticVersion;
import io.vlingo.lattice.model.IdentifiedDomainEvent;

import io.vlingo.developers.petclinic.model.client.Specialty;

/**
 * See
 * <a href="https://docs.vlingo.io/vlingo-lattice/entity-cqrs#commands-domain-events-and-identified-domain-events">
 *   Commands, Domain Events, and Identified Domain Events
 * </a>
 */
public final class VeterinarianSpecialtyChosen extends IdentifiedDomainEvent {

  private final String id;
  public final Specialty specialty;

  public VeterinarianSpecialtyChosen(final String id, final Specialty specialty) {
    super(SemanticVersion.from("0.0.1").toValue());
    this.specialty = specialty;
    this.id = id;
  }

  @Override
  public String identity() {
    return id;
  }
}
