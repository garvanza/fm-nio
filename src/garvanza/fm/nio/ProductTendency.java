package garvanza.fm.nio;

import java.util.Date;
import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.FindIterable;

import garvanza.fm.nio.db.Mongoi;

public class ProductTendency {

	private Long id;
	
	private double quantity;
	
	private long time;
	
	private String login;

	public ProductTendency(Long id, double quantity, long time, String login) {
		this.id = id;
		this.quantity = quantity;
		this.time = time;
		this.login=login;
	}
	
	public void persist(){
		String js=new Gson().toJson(this);
		new Mongoi().doInsert(Mongoi.PRODUCT_TENDENCY,js);
	}
	
	public static DBCursor tendency(Long id,int days){
		FindIterable<Document> cursor=new Mongoi().doFindGreaterThan(Mongoi.PRODUCT_TENDENCY, "time", 
				new Date().getTime()-days*1000*60*60*24L).sort(new BasicDBObject("time",1));
		
		for(int i=0;i<days;i++){
			for(Document dbObject : cursor){
				
				
			}
		}
		//TODO
		return null;
	}
	
}
