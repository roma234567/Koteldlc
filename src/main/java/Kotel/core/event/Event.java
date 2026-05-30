package Kotel.core.event;

import java.time.Instant;

public class Event {
    private final Instant createdAt = Instant.now();
    private boolean cancelled;

    public Instant createdAt() { return createdAt; }
    public boolean isCancelled() { return cancelled; }
    public void cancel() { this.cancelled = true; }
}
