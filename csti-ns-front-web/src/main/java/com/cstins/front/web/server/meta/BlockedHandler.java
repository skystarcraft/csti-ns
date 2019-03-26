package com.cstins.front.web.server.meta;

import java.lang.annotation.*;

/**
 * 用于标识此Handler会有阻塞调用
 *
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BlockedHandler {
}