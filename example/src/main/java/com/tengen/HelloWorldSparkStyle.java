package com.tengen;

import spark.Spark;
import spark.Route;
import spark.Request;
import spark.Response;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-8
 * Time: 上午11:15
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args){
        Spark.get(new Route("/"){
            public Object handle(final Request request, final Response response) {
                return "Hello World From Spark";
            }

        });
    }

}
