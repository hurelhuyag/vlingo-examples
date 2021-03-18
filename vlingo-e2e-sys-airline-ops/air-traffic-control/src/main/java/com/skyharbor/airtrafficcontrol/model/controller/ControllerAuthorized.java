// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package com.skyharbor.airtrafficcontrol.model.controller;

import io.vlingo.common.version.SemanticVersion;
import io.vlingo.lattice.model.IdentifiedDomainEvent;

import java.util.UUID;

public final class ControllerAuthorized extends IdentifiedDomainEvent {

  private final UUID eventId;
  public final String id;
  public final String name;

  public ControllerAuthorized(final ControllerState state) {
    super(SemanticVersion.from("0.0.1").toValue());
    this.id = state.id;
    this.name = state.name;
    this.eventId = UUID.randomUUID(); //TODO: Define the event id
  }

  @Override
  public String identity() {
    return eventId.toString();
  }
}
