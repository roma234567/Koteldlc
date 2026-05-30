package Kotel.core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventManager {
    private final List<Consumer<Event>> listeners = new ArrayList<>();

    public void subscribe(Consumer<Event> listener) { listeners.add(listener); }
    public void unsubscribe(Consumer<Event> listener) { listeners.remove(listener); }
    public void post(Event event) {
        for (Consumer<Event> listener : List.copyOf(listeners)) {
            if (!event.isCancelled()) listener.accept(event);
        }
    }
}
