package com.cstins.front.web.server.handler;

import com.cstins.front.web.server.meta.BlockedHandler;
import com.cstins.front.web.server.meta.GlobalHolder;
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
public class ArticleHandler{

    private static Vertx vertx = GlobalHolder.instance().getVertx();
    private static ThymeleafTemplateEngine templateEngine = GlobalHolder.instance().getTemplateEngine();
    private static WebClient webClient = WebClient.create(vertx);

    public static void showArticles(RoutingContext route) {
        webClient.getAbs("http://localhost:8087/manager/articles").send(res -> {
            if (res.succeeded()) {
                route.response().putHeader("Content-Type", "application/json").end(res.result().body());
            } else {
                route.fail(res.cause());
            }
        });
    }

    public static void delArticle(RoutingContext route) {

    }
}
