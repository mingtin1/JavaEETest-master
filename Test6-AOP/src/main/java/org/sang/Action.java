package org.sang;

import java.lang.annotation.*;

/**
 * 标签
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String name();
}
