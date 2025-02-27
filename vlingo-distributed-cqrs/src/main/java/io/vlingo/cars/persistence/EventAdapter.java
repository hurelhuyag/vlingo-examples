package io.vlingo.cars.persistence;

import io.vlingo.common.serialization.JsonSerialization;
import io.vlingo.symbio.BaseEntry;
import io.vlingo.symbio.EntryAdapter;
import io.vlingo.symbio.Metadata;
import io.vlingo.symbio.Source;

public class EventAdapter<T extends Source<?>> implements EntryAdapter<T, BaseEntry.TextEntry> {
    private final Class<T> type;
    private final int eventVersion;

    public EventAdapter(final Class<T> type) {
        this.type = type;
        this.eventVersion = 1;
    }

    @Override
    public T fromEntry(final BaseEntry.TextEntry entry) {
        return JsonSerialization.deserialized(entry.entryData(), type);
    }

    @Override
    public BaseEntry.TextEntry toEntry(final T object) {
        final String serialization = JsonSerialization.serialized(object);
        return new BaseEntry.TextEntry(type, eventVersion, serialization, Metadata.nullMetadata());
    }

    @Override
    public BaseEntry.TextEntry toEntry(T object, Metadata metadata) {
        final String serialization = JsonSerialization.serialized(object);
        return new BaseEntry.TextEntry(type, eventVersion, serialization, metadata);
    }

    @Override
    public BaseEntry.TextEntry toEntry(T object, int version, String id, Metadata metadata) {
        final String serialization = JsonSerialization.serialized(object);
        return new BaseEntry.TextEntry(id, type, eventVersion, serialization, version, Metadata.nullMetadata());
    }
}
