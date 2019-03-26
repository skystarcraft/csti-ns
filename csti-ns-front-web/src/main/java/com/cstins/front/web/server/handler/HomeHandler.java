package com.cstins.front.web.server.handler;

import com.cstins.front.web.server.meta.BlockedHandler;
import com.cstins.front.web.server.meta.GlobalHolder;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import org.springframework.stereotype.Component;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/

@Component
@BlockedHandler
public class HomeHandler implements Handler<RoutingContext> {

    private static Vertx vertx = GlobalHolder.instance().getVertx();

    private static ThymeleafTemplateEngine templateEngine = GlobalHolder.instance().getTemplateEngine();

    private static WebClient webClient = WebClient.create(vertx);

    @Override
    public void handle(RoutingContext route) {
        webClient.getAbs("http://localhost:8087/manager/all").send(result -> {
            route.put("msg", result.result().body());
            templateEngine.render(route, "templates/index.html", res -> {
                if (res.succeeded()) {
                    route.response().putHeader("Content-Type", "text/html").end(res.result());
                } else {
                    route.fail(res.cause());
                }
            });
        });
    }
}
