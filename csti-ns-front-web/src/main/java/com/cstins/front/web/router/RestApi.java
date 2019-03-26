package com.cstins.front.web.router;

import com.cstins.front.web.server.handler.ArticleHandler;
import io.vertx.ext.web.Router;

/**
 * @program: csti-ns
 * @description:
 * @author: 杨云龙
 **/
public class RestApi {

    public static void api(Router router) {
        router.get("/manager/articles").handler(ctx -> ArticleHandler.showArticles(ctx));
        router.delete("/manager/article/:aid").handler(ctx -> {ArticleHandler.delArticle(ctx);});
    }

}
