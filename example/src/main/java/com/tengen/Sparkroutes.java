package com.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-8
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class Sparkroutes {
    public static void main(String[] args){
        Spark.get(new Route("/") {
            public Object handle(final Request request, final Response response) {
                return "Hello World From Spark";
            }

        });

        Spark.get(new Route("/test") {
            public Object handle(final Request request, final Response response) {
                return "This is a test page\n";
            }

        });

        Spark.get(new Route("/echo/:thing") {
            public Object handle(final Request request, final Response response) {
                return request.params(":thing");
            }

        });
    }
}
