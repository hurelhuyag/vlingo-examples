// Copyright © 2012-2021 VLINGO LABS. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package com.skyharbor.airtrafficcontrol.infrastructure.persistence;

import io.vlingo.xoom.annotation.persistence.Persistence;
import io.vlingo.xoom.annotation.persistence.Persistence.StorageType;
import io.vlingo.xoom.annotation.persistence.Projections;
import io.vlingo.xoom.annotation.persistence.Projection;
import io.vlingo.xoom.annotation.persistence.Adapters;
import io.vlingo.xoom.annotation.persistence.EnableQueries;
import io.vlingo.xoom.annotation.persistence.QueriesEntry;
import io.vlingo.xoom.annotation.persistence.DataObjects;
import com.skyharbor.airtrafficcontrol.infrastructure.ControllerData;
import com.skyharbor.airtrafficcontrol.model.controller.ControllerState;
import com.skyharbor.airtrafficcontrol.model.controller.ControllerAuthorized;
import com.skyharbor.airtrafficcontrol.model.flight.OutboundTaxingInitiated;
import com.skyharbor.airtrafficcontrol.model.flight.FlightClearedForLanding;
import com.skyharbor.airtrafficcontrol.model.flight.FlightLanded;
import com.skyharbor.airtrafficcontrol.model.flight.EnteredFlightLine;
import com.skyharbor.airtrafficcontrol.model.flight.FlightState;
import com.skyharbor.airtrafficcontrol.infrastructure.FlightData;
import com.skyharbor.airtrafficcontrol.model.flight.FlightClearedForTakeOff;
import com.skyharbor.airtrafficcontrol.model.flight.FlightTookOff;
import com.skyharbor.airtrafficcontrol.model.flight.FlightDepartedGate;

@Persistence(basePackage = "com.skyharbor.airtrafficcontrol", storageType = StorageType.STATE_STORE, cqrs = true)
@Projections({
  @Projection(actor = FlightProjectionActor.class, becauseOf = {FlightTookOff.class, FlightClearedForLanding.class, FlightLanded.class, OutboundTaxingInitiated.class, FlightClearedForTakeOff.class, EnteredFlightLine.class, FlightDepartedGate.class}),
  @Projection(actor = ControllerProjectionActor.class, becauseOf = {ControllerAuthorized.class})
})
@Adapters({
  FlightState.class,
  ControllerState.class
})
@EnableQueries({
  @QueriesEntry(protocol = FlightQueries.class, actor = FlightQueriesActor.class),
  @QueriesEntry(protocol = ControllerQueries.class, actor = ControllerQueriesActor.class),
})
@DataObjects({ControllerData.class, FlightData.class})
public class PersistenceSetup {


}