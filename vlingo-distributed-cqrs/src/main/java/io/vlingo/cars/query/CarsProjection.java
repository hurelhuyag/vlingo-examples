package io.vlingo.cars.query;

import io.vlingo.lattice.model.DomainEvent;
import io.vlingo.lattice.model.IdentifiedDomainEvent;
import io.vlingo.lattice.model.projection.Projectable;
import io.vlingo.lattice.model.projection.StateStoreProjectionActor;
import io.vlingo.cars.model.CarEvents;
import io.vlingo.cars.query.view.CarViewType;
import io.vlingo.cars.query.view.CarsView;
import io.vlingo.symbio.Entry;
import io.vlingo.symbio.store.state.StateStore;

import java.util.ArrayList;
import java.util.List;

public class CarsProjection extends StateStoreProjectionActor<CarsView> {
    private final List<IdentifiedDomainEvent> events;

    public CarsProjection(StateStore stateStore) {
        super(stateStore);

        this.events = new ArrayList<>(2);
    }

    @Override
    protected CarsView currentDataFor(Projectable projectable) {
        return CarsView.empty();
    }

    @Override
    protected String dataIdFor(Projectable projectable) {
        return CarsView.Id;
    }

    @Override
    protected boolean alwaysWrite() {
        return false;
    }

    @Override
    protected CarsView merge(CarsView previousData, int previousVersion, CarsView currentData, int currentVersion) {
        return previousData == null
                ? mergeEventsInto(currentData)
                : mergeEventsInto(previousData);
    }

    @Override
    protected void prepareForMergeWith(Projectable projectable) {
        events.clear();

        for (Entry<?> entry : projectable.entries()) {
            events.add(entryAdapter().anyTypeFromEntry(entry));
        }
    }

    private CarsView mergeEventsInto(CarsView initialData) {
        CarsView mergedData = initialData;

        for (DomainEvent event : events) {
            switch (CarViewType.match(event)) {
                case CarDefined:
                    CarEvents.CarDefined defined = typed(event);
                    mergedData = mergedData.add(CarsView.CarItem.of(defined.carId, defined.type, defined.model, defined.registrationNumber));
                    break;
                case Unmatched:
                    logger().warn("Event of type " + event.typeName() + " was not matched.");
                    break;
            }
        }

        logger().info("PROJECTED: " + mergedData);

        return mergedData;
    }
}
