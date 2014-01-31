import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created with IntelliJ IDEA.
 * User: xiaogao
 * Date: 14-1-20
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public class RemoveRecords {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB database = client.getDB("students");

        DBCollection collection = database.getCollection("grades");

        DBCursor cur = collection.find(new BasicDBObject("type", "homework")).sort(new BasicDBObject("student_id", 1).append("score", 1));


        int counter = 0;
        
        while(cur.hasNext()){
        	DBObject record = cur.next();
        	if((counter % 2)== 0){
        		collection.remove(record);
        	}
        	counter++;
        }

        /*  method 2. need to create a BasicDBObject object and thus can use getInt(String key) method
        int student_id = -1;

        while(cur.hasNext()){
            BasicDBObject stu = (BasicDBObject) cur.next();
            if(student_id != stu.getInt("student_id")){
                student_id = stu.getInt("student_id");
                collection.remove(stu);
            }
        }

         */

    }
}
