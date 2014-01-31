package com.tengen;


import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-8
 * Time: 下午5:11
 * To change this template use File | Settings | File Templates.
 */
public class Sparkformhandling {

    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                HelloWorldFreemarkerStyle.class, "/");
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");

        Spark.get(new Route("/") {
            public Object handle(final Request request, final Response response) {

                try {
                    Map<String, Object> fruitMap = new HashMap<String, Object>();
                    fruitMap.put("fruits",
                            Arrays.asList("apple", "orange", "banana", "peach"));

                    Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPickerTemplate.process(fruitMap, writer);
                    return writer;






                } catch (Exception e) {
                    halt(500);
                    return null;
                }

            }

        });

        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                final String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why don't you pick one?";
                }
                else{
                    return "Your favorite fruit is " + fruit;
                }

            }
        });
    }
}
