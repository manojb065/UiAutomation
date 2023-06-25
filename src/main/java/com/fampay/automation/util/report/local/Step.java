package com.fampay.automation.util.report.local;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Repeatable(Steps.class)
public @interface Step {
    String step() default "";
}
