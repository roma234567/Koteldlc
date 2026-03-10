package com.koteldlc.client.event;

public class CancellableEvent {
    private boolean cancelled;
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}
