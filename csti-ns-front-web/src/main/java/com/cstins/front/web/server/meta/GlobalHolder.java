package com.cstins.front.web.server.meta;

import io.vertx.core.Vertx;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import org.springframework.stereotype.Component;

/**
 * @program: csti-ns
 * @description: 全局变量
 * @author: 杨云龙
 **/

@Component
public class GlobalHolder {

    private static GlobalHolder globalHolder = null;

    private Vertx vertx = Vertx.vertx();
    private ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create();

    private GlobalHolder() {
    }

    public static GlobalHolder instance() {
        if (globalHolder == null) {
            synchronized (GlobalHolder.class) {
                if (globalHolder == null) {
                    return new GlobalHolder();
                }
            }
        }
        return globalHolder;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public ThymeleafTemplateEngine getTemplateEngine() {
        return templateEngine;
    }

}
