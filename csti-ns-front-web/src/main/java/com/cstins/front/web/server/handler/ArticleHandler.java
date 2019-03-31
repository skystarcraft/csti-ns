package com.cstins.front.web.server.handler;

import com.cstins.front.web.server.meta.BlockedHandler;
import com.cstins.front.web.server.meta.GlobalHolder;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("server.host")
    private static String host;

    public static void showArticles(RoutingContext route) {
        webClient.getAbs("http://" + host + ":8082/articles").send(res -> {
            if (res.succeeded()) {
                int statusCode = res.result().statusCode();
                JsonArray jsonArray = res.result().bodyAsJsonArray();
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("code", statusCode).put("articles", jsonArray);
                route.response().putHeader("Content-Type", "application/json").end(jsonObject.toString());
            } else {
                int statusCode = res.result().statusCode();
                Throwable cause = res.cause();
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("code", statusCode).put("msg", cause);
                route.response().putHeader("Content-Type", "application/json").end(jsonObject.toString());
            }
        });
    }

    public static void delArticle(RoutingContext route) {
        String param = route.request().getParam("aid");
        if (param != null && !"".equals(param)) {
            Integer aid = Integer.parseInt(param);
            webClient.deleteAbs("http://" + host + ":8087/articles").send(res -> {
                if (res.succeeded()) {
                    int statusCode = res.result().statusCode();
                    String result = res.result().bodyAsString();
                    String msg = "";
                    if ("true".equals(result)) {
                        msg = "删除成功！";
                    } else {
                        msg = "删除失败！";
                    }
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.put("code", statusCode).put("msg", msg);
                    route.response().putHeader("Content-Type", "application/json").end(jsonObject.toString());
                } else {
                    int statusCode = res.result().statusCode();
                    Throwable cause = res.cause();
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.put("code", statusCode).put("msg", cause);
                    route.response().putHeader("Content-Type", "application/json").end(jsonObject.toString());
                }
            });
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("code", 400).put("msg", "参数不能为空！");
            route.response().end(jsonObject.toString());
        }
    }

    public static void showArticle(RoutingContext route) {

    }
}
