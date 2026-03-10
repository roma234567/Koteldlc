package com.koteldlc.client.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    Priority value() default Priority.NORMAL;
}
