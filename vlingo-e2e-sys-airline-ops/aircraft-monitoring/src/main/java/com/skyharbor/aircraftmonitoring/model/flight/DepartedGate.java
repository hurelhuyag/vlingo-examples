// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package com.skyharbor.aircraftmonitoring.model.flight;

import io.vlingo.common.version.SemanticVersion;
import io.vlingo.lattice.model.IdentifiedDomainEvent;

import java.util.UUID;

public final class DepartedGate extends IdentifiedDomainEvent {

  private final UUID eventId;
  public final String id;
  public final Aircraft aircraft;
  public final ActualDeparture actualDeparture;

  public DepartedGate(final FlightState state) {
    super(SemanticVersion.from("1.0.0").toValue());
    this.id = state.id;
    this.aircraft = state.aircraft;
    this.actualDeparture = state.actualDeparture;
    this.eventId = UUID.randomUUID(); //TODO: Define the event id
  }

  @Override
  public String identity() {
    return eventId.toString();
  }
}
