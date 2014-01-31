package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-8
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorldMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException{
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        DBCollection collection = database.getCollection("hello");

        DBObject document = collection.findOne();
        System.out.println(document);

    }
}
