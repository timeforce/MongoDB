import com.mongodb.*;

import java.lang.reflect.Array;
import java.net.UnknownHostException;

/**
 * Created by xiaogao on 14-1-23.
 */
public class RemoveRecords2 {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students");


        DBObject unwind = new BasicDBObject("$unwind", "$scores");
        DBObject match = new BasicDBObject("$match", new BasicDBObject("scores.type", "homework"));
        /*
        DBObject fields = new BasicDBObject("scores.type", 1);
        fields.put("scores.score", 1);
        fields.put("_id", 0);
        DBObject project = new BasicDBObject("$project", fields);
         */
        DBObject groupFields = new BasicDBObject("_id", "$_id");
        groupFields.put("minscore", new BasicDBObject("$min", "$scores.score"));
        DBObject group = new BasicDBObject("$group", groupFields);

        AggregationOutput results = collection.aggregate(unwind, match,  group);



        for (DBObject result: results.results()){
             //System.out.println(result);
             BasicDBObject searchQuery = new BasicDBObject().append("_id", result.get("_id"));
             BasicDBObject updateQuery = new BasicDBObject("scores", new BasicDBObject("score", result.get("minscore")) );
             //System.out.println(updateQuery);
             //System.out.println(searchQuery);
             collection.update(searchQuery, new BasicDBObject("$pull", updateQuery));
        }



    }


}
