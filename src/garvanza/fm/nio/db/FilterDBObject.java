package garvanza.fm.nio.db;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;

public class FilterDBObject {

	public static Document keep(String[] keys, Document doc){
		Document basicDBObject= new Document();
		for(String st : keys){
			basicDBObject.put(st, doc.get(st));
		}
		return basicDBObject;
	}
	
	public static List<Document> keep(String[] keys, List<Document> list){
		List<Document> list2= new LinkedList<Document>();
		for(Document dbObject : list){
			list2.add(keep(keys, dbObject));
		}
		return list2;
	}
	
	public static List<Document> keep(String[] keys, FindIterable<Document> list){
		List<Document> list2= new LinkedList<Document>();
		for(Document dbObject : list){
			list2.add(keep(keys, dbObject));
		}
		return list2;
	}
}
