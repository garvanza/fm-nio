package garvanza.fm.nio;

import java.util.Date;

import org.bson.Document;

import com.google.gson.Gson;

import garvanza.fm.nio.db.Mongoi;

public class TemporalRecord {
	
	private long id;
	
	private String text;
	
	private String login;
	
	private boolean todo=true;
	
	private long creationTime=new Date().getTime();
	
	TemporalRecord(){
		
	}

	public TemporalRecord(String text, String login) {
		this.text = text;
		this.login = login;
	}
	
	private long setId(){
		id=new Mongoi().doIncrement(Mongoi.TEMPORAL_RECORDS_COUNTER, "{\"unique\":\"unique\"}", "id");
		return id;
	}
	
	public boolean persist(){
		setId();
		new Mongoi().doInsert(Mongoi.TEMPORAL_RECORDS, this);
		Document dbObject=new Mongoi().doFindOne(Mongoi.TEMPORAL_RECORDS, "{ \"id\" : "+id+" }");
		if(dbObject!=null)return true;
		else return false;
	}
	
	public static TemporalRecord deactivate(long id){
		new Mongoi().doUpdate(Mongoi.TEMPORAL_RECORDS, "{ \"id\" : "+id+" }", "{ \"todo\" : "+false+" }");
		return (TemporalRecord)new Gson().fromJson(
				new Mongoi().doFindOne(Mongoi.TEMPORAL_RECORDS, "{ \"id\" : "+id+" }").toJson(), 
				TemporalRecord.class);
	}
	
	

}
