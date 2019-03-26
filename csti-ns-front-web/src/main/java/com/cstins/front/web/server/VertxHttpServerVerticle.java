package com.cstins.front.web.server;

import com.cstins.front.web.router.RestApi;
import com.cstins.front.web.server.meta.BlockedHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Slf4j
public class VertxHttpServerVerticle extends AbstractVerticle {
    public static ApplicationContext springContext;

    @Override
    public void start(Future<Void> startFuture) {
        VertxProps config = springContext.getBean(VertxProps.class);

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        addHandlers(router, config.getHandlerMappings());         //从yaml文件中读取router

        RestApi.api(router);                                      //路由列表

        server.requestHandler(router::accept)
                .listen(config.getServer().getPort(), res -> {
                    if (res.succeeded()) {
                        startFuture.complete();

                    } else {
                        startFuture.fail(res.cause());
                    }
                });
    }

    private void addHandlers(Router router, List<VertxProps.HandlerMapping> handlerMappings) {
        for (VertxProps.HandlerMapping hm : handlerMappings) {
            for (String beanName : hm.getBeanNames()) {
                Handler<RoutingContext> handler = (Handler<RoutingContext>) springContext.getBean(beanName);

                Route route = router.route(hm.getPath());
                if (isBlocked(handler)) {
                    route.blockingHandler(handler);
                } else {
                    route.handler(handler);
                }

            }
        }
    }

    private boolean isBlocked(Handler<?> handler) {
        BlockedHandler an = handler.getClass().getAnnotation(BlockedHandler.class);
        return an != null;
    }
}
