package com.koteldlc.client.event;

import java.lang.reflect.Method;
import java.util.*;

public class EventBus {
    private final Map<Class<?>, List<Listener>> listenerMap = new HashMap<>();

    public void subscribe(Object obj) {
        for (Method method : obj.getClass().getDeclaredMethods()) {
            if (!method.isAnnotationPresent(EventHandler.class) || method.getParameterCount() != 1) continue;
            method.setAccessible(true);
            Class<?> eventType = method.getParameterTypes()[0];
            listenerMap.computeIfAbsent(eventType, k -> new ArrayList<>())
                    .add(new Listener(obj, method, method.getAnnotation(EventHandler.class).value()));
            listenerMap.get(eventType).sort(Comparator.comparing((Listener l) -> l.priority.ordinal()));
        }
    }

    public void post(Object event) {
        List<Listener> listeners = listenerMap.getOrDefault(event.getClass(), List.of());
        for (Listener listener : listeners) {
            listener.invoke(event);
            if (event instanceof CancellableEvent c && c.isCancelled()) break;
        }
    }

    private record Listener(Object instance, Method method, Priority priority) {
        void invoke(Object event) {
            try { method.invoke(instance, event); } catch (Exception ignored) { }
        }
    }
}
