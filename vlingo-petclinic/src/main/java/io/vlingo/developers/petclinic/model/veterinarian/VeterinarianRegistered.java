package io.vlingo.developers.petclinic.model.veterinarian;

import io.vlingo.common.version.SemanticVersion;
import io.vlingo.lattice.model.IdentifiedDomainEvent;

import io.vlingo.developers.petclinic.model.ContactInformation;
import io.vlingo.developers.petclinic.model.client.Specialty;
import io.vlingo.developers.petclinic.model.Fullname;

/**
 * See
 * <a href="https://docs.vlingo.io/vlingo-lattice/entity-cqrs#commands-domain-events-and-identified-domain-events">
 *   Commands, Domain Events, and Identified Domain Events
 * </a>
 */
public final class VeterinarianRegistered extends IdentifiedDomainEvent {

  public final String id;
  public final Fullname name;
  public final ContactInformation contact;
  public final Specialty specialty;

  public VeterinarianRegistered(final String id,
                                final Fullname name,
                                final ContactInformation contact,
                                final Specialty specialty) {
    super(SemanticVersion.from("0.0.1").toValue());
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.specialty = specialty;
  }

  @Override
  public String identity() {
    return id;
  }
}
