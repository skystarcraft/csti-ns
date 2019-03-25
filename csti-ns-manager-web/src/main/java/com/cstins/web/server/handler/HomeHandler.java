package com.cstins.web.server.handler;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import org.springframework.stereotype.Component;
/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Component
public class HomeHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext route) {
        route.response()
                .end("vertx-springboot");
    }
}
