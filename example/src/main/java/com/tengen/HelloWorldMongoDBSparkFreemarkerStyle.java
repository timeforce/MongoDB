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
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-8
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(
                HelloWorldFreemarkerStyle.class, "/");
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");

        Spark.get(new Route("/") {
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    DBObject document = collection.findOne();


                    helloTemplate.process(document, writer);

                    System.out.println(writer);

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }

        });
    }
}
