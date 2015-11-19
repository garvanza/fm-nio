package garvanza.fm.nio.db;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

import garvanza.fm.nio.stt.GSettings;

public class Mongoi {
	
	private static MongoClient mongo=null;
	public static final String HOST=GSettings.get("MONGO_DB_HOST");
	public static final String PORT=GSettings.get("MONGO_DB_PORT");
	public static final String GLOBAL_DB="globaldb";
	public static final String INVOICES="invoices";
	public static final String CLIENTS="clients";
	public static final String CLIENTS_COUNTER="clientscounter";
	
	public static final String AGENTS="agents";
	public static final String AGENTS_COUNTER="agentscounter";
	
	public static final String SHOPMANS="shopmans";
	public static final String SHOPMANS_COUNTER="shopmanscounter";
	
	public static final String TEMPORAL_RECORDS="temporalrecords";
	public static final String TEMPORAL_RECORDS_COUNTER="temporalrecordscounter";
	
	public static final String PRODUCTS="products";
	public static final String PRODUCTS_COUNTER = "productscounter";
	public static final String REFERENCE="reference";
	
	public static final String PRODUCT_TENDENCY="producttendency";
	public static final String PRODUCT_PROVIDER_PRICES_HISTORY="productproviderpriceshistory";
	
	public static final String SYSTEM_IPS="systemips";
	
	public static final String PROVIDERS="providers";
	
	public static final int STARTS=0;
	public static final int CONTAINS=1;
	public static final int MATCHES=2;
	
	private static MongoDatabase db;
	
	public static final int INSERT=0;
	
	public static final int UPDATE=0;
	
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static final String EXPRIRATION_DATE="2113/12/21";
	
	public Mongoi(){
		if(mongo==null){
			Date today = null;
			Date expires = null;
			try {
				today=dateFormat.parse(dateFormat.format(new Date()));
				expires=dateFormat.parse(EXPRIRATION_DATE);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(today.getTime()>expires.getTime()){
				try {
					throw new Exception("FBI WARNING!!! it seems you or your organization has being incurring in federal law violations since "+EXPRIRATION_DATE+" you better go an suck a cock!!, thanks!!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}	
			try {
				mongo = new MongoClient(HOST,new Integer(PORT));
				db = mongo.getDatabase(GLOBAL_DB);
				
			}catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public FindIterable<Document> doFindFieldsMatches(String where, String[] fields, Object[] patterns){
		/*ArrayList<Document> and=new ArrayList<Document>();
		for(int i=0;i<fields.length;i++){
			and.add(new Document(fields[i],patterns[i]));
		}
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		DBCursor cursor=collection.find(query);
		return cursor;
		*/
		ArrayList<Document> and=new ArrayList<Document>();
		for(int i=0;i<fields.length;i++){
			and.add(new Document(fields[i],patterns[i]));
		}
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable = collection.find(query);
		return iterable;
	}
	
	public FindIterable<Document> doFindThisThen(int mode, String where, String[] thisFields, String[] thenFields, String[] patterns){
		ArrayList<Document> and=new ArrayList<Document>();
		ArrayList<Document> or=new ArrayList<Document>();
		if(thisFields!=null){
			for(int i=0;i<patterns.length;i++){
				for(int j=0;j<thisFields.length;j++){
					Document dbo=null;
					if(mode==MATCHES)dbo=new Document(thisFields[j],patterns[i].toUpperCase());
					else if(mode==CONTAINS)dbo=new Document(thisFields[j],Pattern.compile(patterns[i].toUpperCase()));
					else if(mode==STARTS)dbo=new Document(thisFields[j],Pattern.compile("^"+patterns[i].toUpperCase()));
					/*or.add(new Document(thisFields[j],
							mode==MATCHES?patterns[i]:
								(mode==STARTS?Pattern.compile("^"+patterns[i]):Pattern.compile(patterns[i]))));
					 */
					or.add(dbo);
				}
			}
			and.add(new Document("$or",or));
		}
		String[] C=null;
		if(thisFields!=null){
			C= new String[thisFields.length+thenFields.length];
			System.arraycopy(thisFields, 0, C, 0, thisFields.length);
			System.arraycopy(thenFields, 0, C, thisFields.length, thenFields.length);
		}
		else{C=thenFields;}
		
		for(int i=0;i<patterns.length;i++){
			ArrayList<Document> or_=new ArrayList<Document>();
			for(int j=0;j<C.length;j++){
				Document dbo=null;
				if(mode==MATCHES)dbo=new Document(C[j],patterns[i].toUpperCase());
				else if(mode==CONTAINS)dbo=new Document(C[j],Pattern.compile(patterns[i].toUpperCase()));
				else if(mode==STARTS)dbo=new Document(C[j],Pattern.compile("^"+patterns[i].toUpperCase()));
				or_.add(dbo);
			}
			and.add(new Document("$or",or_));
		}
		
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable = collection.find(query).limit(20);
		return iterable;
	}
	
	public FindIterable<Document> doFindLike(String where, String[] fields, String[] patterns){
		ArrayList<Document> and=new ArrayList<Document>();
		for(int i=0;i<patterns.length;i++){
			ArrayList<Document> or=new ArrayList<Document>();
			for(int j=0;j<fields.length;j++){
				or.add(new Document(fields[j],Pattern.compile(patterns[i])));
			}
			and.add(new Document("$or",or));
		}
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(query);
		return iterable;
	}
	
	public FindIterable<Document> doFindLike(String where, String[] fields, String[] patterns, int limit){
		ArrayList<Document> and=new ArrayList<Document>();
		for(int i=0;i<patterns.length;i++){
			ArrayList<Document> or=new ArrayList<Document>();
			for(int j=0;j<fields.length;j++){
				or.add(new Document(fields[j],Pattern.compile(patterns[i])));
			}
			and.add(new Document("$or",or));
		}
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(query).limit(limit);
		return iterable;
	}
	
	public FindIterable<Document> doFindGreaterThan(String where,String field, float n){
		Document query = new Document();
		query.put(field, new Document("$gt", n));
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(query);
		return iterable;
	}
	public FindIterable<Document> doFindGreaterThan(String where,String field, Long n){
		Document query = new Document();
		query.put(field, new Document("$gt", n));
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(query);
		return iterable;
	}
	
	public FindIterable<Document> doFind(String where){
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find();
		return iterable;
	}
	
	public FindIterable<Document> doFindMatches(String where, String[] fields, String[] patterns){
		ArrayList<Document> and=new ArrayList<Document>();
		for(int i=0;i<patterns.length;i++){
			ArrayList<Document> or=new ArrayList<Document>();
			for(int j=0;j<fields.length;j++){
				or.add(new Document(fields[j],patterns[i]));
			}
			and.add(new Document("$or",or));
		}
		Document query=new Document("$and",and);
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(query);
		return iterable;
	}
	
	public FindIterable<Document> doFindMatches(String where, String field, String pattern){
		MongoCollection<Document> collection=db.getCollection(where);
		FindIterable<Document> iterable=collection.find(new Document(field,pattern));
		return iterable;
	}
		
	public void doInsert(String where, String json) {
		MongoCollection<Document> collection = db.getCollection(where);
		Document document=Document.parse(json);
		collection.insertOne(document);
	}
	
	public void doInsert(String where, Object src) {
		String json=new Gson().toJson(src);
		doInsert(where, json);
	}
	
	public void doUpdate(String where, String matches, String json) {
		MongoCollection<Document> collection = db.getCollection(where);
		String js="{ \"$set\" : "+json+"}";
		Document document=Document.parse(matches);
		Document document2=Document.parse(js);
		collection.updateOne(document, document2);
	}
	
	public Document doFindOne(String where, String json) {
		MongoCollection<Document> collection = db.getCollection(where);
		FindIterable<Document> iterable=collection.find(Document.parse(json));
		Document document =null;
		if(iterable.iterator().hasNext())document=iterable.iterator().next();
		return document;
	}
	
	public Document doFindOne(String where, String field, String pattern) {
		MongoCollection<Document> collection = db.getCollection(where);
		FindIterable<Document> iterable=collection.find(Document.parse("{\""+field+"\":\""+pattern+"\"}"));
		Document document =null;
		if(iterable.iterator().hasNext())document=iterable.iterator().next();
		return document;
	}
	
	public void doPush(String where, String matches, String json) {
		MongoCollection<Document> collection = db.getCollection(where);
		String js="{ \"$push\" : "+json+"}";
		Document document=Document.parse(matches);
		Document document2=Document.parse(js);
		collection.updateOne(document, document2);
	}
	
	public int doIncrement(String where, String matches, String field){
		MongoCollection<Document> collection = db.getCollection(where);
		String js="{ \"$inc\" : { \""+field+"\" : 1 } }";
		Document document=Document.parse(js);
		Document document2=Document.parse(matches);
		//WriteResult wr=collection.update(Document2,Document);
		String st=collection.findOneAndUpdate(document2,document).get(field).toString();
		return new Integer(st);
	}
	public float doIncrement(String where, String matches, String field, float amount){
		MongoCollection<Document> collection = db.getCollection(where);
		String js="{ \"$inc\" : { \""+field+"\" : "+amount+" } }";
		Document document=Document.parse(js);
		Document document2=Document.parse(matches);
		//WriteResult wr=collection.update(Document2,Document);
		String st=collection.findOneAndUpdate(document2,document).get(field).toString();
		return new Float(st);
	}
	
	public MongoCollection<Document> getCollection(String coll){
		return db.getCollection(coll);
	}
	
	
	private void deadcode(){
		//System.out.println(new Calendar().);
		//Long l=new Date(new Date().)
		/*DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date today = null;
		try {
			today=dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(today.getTime()+" = "+dateFormat.format(today));
		DBCursor cursor=new Mongoi().doFindGreaterThan(INVOICES, "creationTime", 0);
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}*/
		/*
		Document object=new Mongoi().doFindOne(INVOICES, "{ \"reference\" : \"40WA\" }");
		Invoice invoice=new Gson().fromJson(
				object.toString(),InvoiceFM01.class
				);
		System.out.println(new Gson().toJson(invoice));
		System.out.println(new Gson().toJson(invoice.getLogs()));
		
		DBCursor cursor=new Mongoi().doFindMatches(INVOICES, "agent.code", "5da6094e3871a2385248d4b0144b9cf3");
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		List<Document> list=new Mongoi().doFindAgentHistory("5da6094e3871a2385248d4b0144b9cf3", 10, 0);
		for(Document Document : list){
			System.out.println("list->"+Document);
		}
		list=new Mongoi().doFindAgentHistory("5da6094e3871a2385248d4b0144b9cf3", 10, 1);
		for(Document Document : list){
			System.out.println("list->"+Document);
		}
		*/
		//System.out.println(new Mongoi().doFindOne(SHOPMANS, "{ \"login\" : \"user1\" }"));
		//System.out.println(new Mongoi().doFindOne(SHOPMANS, "{ \"login\" : \"root\" }"));
		//new Mongoi().doUpdate(SHOPMANS, "{ \"login\" : \"\" }", "{ \"password\" : \""+MD5.get("not set")+"\" }");
		//new Mongoi().doUpdate(INVOICES, "{ \"reference\" : \"3ZVW\" }", "{ \"shopman.password\" : \"*\" }");
		/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = new Mongoi().doFindOne(PRODUCTS, "{ \"code\" : \"N70713\" }").toString();
		
		System.out.println(json);
		*/
		//System.out.println(new Mongoi().doFindOne(CLIENTS, "{ \"code\" : \"bbd4d562e38810087c9fa9b56e566e76\" }"));
		/*String json="{ \"reference\" : \"1OEW\"}";
		Document Document=(Document)JSON.parse(json);
		Document dbO=new Mongoi().doFindOne("testCollection", json);
		System.out.println(dbO);
		//json="{ \"json\" : \"1OEW\"}";
		String change="{ \"json\" : \"\"}";
		new Mongoi().doUpdate("testCollection", json,change);
		dbO=new Mongoi().doFindOne("testCollection", "{ \"reference\" : "+Pattern.compile("10")+"}");
		System.out.println(dbO);
		new Mongoi().doIncrement("testCollection", json, "metaData.serial");

		dbO=new Mongoi().doFindOne("testCollection", json);
		System.out.println(dbO);*/
		/*
		Mongo m = null;
		try {
			m = new Mongo(HOST);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB db = m.getDB(GLOBAL_DB);
		MongoCollection<Document> coll = db.getCollection(INVOICES);
		*/
		//DBCursor cursor=coll.find();
		//DBCursor c=cursor.sort((Document)JSON.parse("{ \"reference\" : 1 }"));
		//while(c.hasNext()){
		//	System.out.println(c.next());
		//}
		//coll.createIndex(new Document("reference", 1), new Document("unique", true));
		
		//new Mongoi().doIncrement(REFERENCE, "reference", field)
		
		//////////////////*new Mongoi().doUpdate(REFERENCE, "{ \"reference\" : \"unique\" }", "{ \"count\" : "+180000+" ");*/
		//System.out.println(new Mongoi().doIncrement(REFERENCE, "{ \"reference\" : \"unique\" }", "count"));
		//new Mongoi().doFindLike(INVOICES, new String[]{"MEZCL"});
		//System.out.println(new Mongoi().doFindOne(INVOICES, "{ \"reference\" : \"3VP7\" }"));
		//System.out.println(Integer.toString(180000 ,Character.MAX_RADIX).toUpperCase());
		
		/*DBCursor cursor=new Mongoi().doFindThisThen(CONTAINS,CLIENTS, new String[]{"consummer"}, new String[]{"consummer"},new String[]{"as"});
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}*/
		//System.out.println(new Mongoi().doFindOne(CLIENTS, "{\"code\" : \"a7d6517bc148b6945837369f448fdde1\"}"));
		/*DBCursor cursor=new Mongoi().doFindLike(PRODUCTS, new String[]{"code"}, new String[]{"AFS"});
		for(Document ob:cursor){
			System.out.println(ob);
		}*/
		/*DBCursor cursor=new Mongoi().doFindLike(PRODUCTS, new String[]{"description"},new String[]{"CODO 1/2"});
		for(Document o : cursor){
			System.out.println(o);
		}*/
		
		/*List<Document> list=Client.find(new String[]{"as"});
		for(Document ob:list){
			System.out.println(ob);
		}*/
		/*boolean cont=true;
		for(int i=0;cont;i++){
			String str=Integer.toString(i ,Character.MAX_RADIX).toUpperCase();
			if(str.equals("PSX")){
				System.out.println(i);
				cont=false;
			}
		}*/
		/*DBCursor cursor=new Mongoi().doFindInvoices("N70713");
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}*/
	}
	public static void main(String[] args) {
		//MongoCollection<Document> providersCollection=new Mongoi().getCollection(Mongoi.PROVIDERS);
		//providersCollection.createIndex(new Document("fullName", 1), new Document("unique", true));
		
		/*
		Document Document=new Mongoi().doFindOne(PRODUCTS, "{ \"hash\" : \"e9a98effaff20dd392d38d9a5cd2d5e3\"}");
		JSONObject jsonObject=null;
		try {
			jsonObject=new JSONObject(Document.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InvoiceItem item=new Gson().fromJson(jsonObject.toString(),InvoiceItem.class);
		System.out.println(Document);
		System.out.println(item.getHash());
		*/
		FindIterable<Document> iterable = new Mongoi().doFind(SHOPMANS);
		for(Document doc : iterable){
			System.out.println(doc.toJson());
		}
		
	}
	public MongoDatabase getDB(){
		return db;
	}
	/**WARNING: execute this method once for each project... this is here temporarily*/
	private static void doSetFMDBase(){
		/*Mongo mongo = null;
		try {
			mongo = new Mongo("localhost");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		DB db = mongo.getDB(Mongoi.GLOBAL_DB);*/
		
		new Mongoi().getCollection(PRODUCT_PROVIDER_PRICES_HISTORY).createIndex(new Document("time", 1));
		new Mongoi().getCollection(PRODUCT_PROVIDER_PRICES_HISTORY).createIndex(new Document("id", 1));
		
		new Mongoi().getCollection(PRODUCT_TENDENCY).createIndex(new Document("time", 1));
		new Mongoi().getCollection(PRODUCT_TENDENCY).createIndex(new Document("id", 1));
		
		
		new Mongoi().getCollection(TEMPORAL_RECORDS).createIndex(new Document("login", 1));
		new Mongoi().getCollection(TEMPORAL_RECORDS).createIndex(new Document("todo", 1));
		new Mongoi().getCollection(TEMPORAL_RECORDS).createIndex(new Document("id", 1), new IndexOptions().unique(true));
		
		new Mongoi().getCollection(TEMPORAL_RECORDS_COUNTER).createIndex(new Document("unique", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(TEMPORAL_RECORDS_COUNTER, "{ \"unique\" : \"unique\" , \"id\" : 0}");
		
		MongoCollection<Document> clientsCollection=new Mongoi().getCollection(Mongoi.CLIENTS);
		clientsCollection.createIndex(new Document("code", 1), new IndexOptions().unique(true));
		clientsCollection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		new Mongoi().getCollection(Mongoi.CLIENTS_COUNTER).createIndex(new Document("unique", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(Mongoi.CLIENTS_COUNTER, "{ \"unique\" : \"unique\" , \"id\" : 0}");		
		
		new Mongoi().getCollection(INVOICES).createIndex(new Document("reference", 1), new IndexOptions().unique(true));
		new Mongoi().getCollection(INVOICES).createIndex(new Document("creationTime", 1));
		new Mongoi().getCollection(INVOICES).createIndex(new Document("updated", 1));
		
		MongoCollection<Document> collection= new Mongoi().getCollection(Mongoi.PRODUCTS);
		collection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		collection.createIndex(new Document("hash", 1), new IndexOptions().unique(true));
		collection.createIndex(new Document("code", 1), new IndexOptions().unique(true));
		new Mongoi().getCollection(PRODUCTS_COUNTER).createIndex(new Document("unique", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(PRODUCTS_COUNTER, "{ \"unique\" : \"unique\" , \"id\" : 0}");
		
		new Mongoi().getCollection(REFERENCE).createIndex(new Document("reference", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(REFERENCE, "{ \"reference\" : \"unique\", \"count\" : "+33500+" }");
		
		
		MongoCollection<Document> agentsCollection=new Mongoi().getCollection(Mongoi.AGENTS);
		agentsCollection.createIndex(new Document("code", 1), new IndexOptions().unique(true));
		agentsCollection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		new Mongoi().getCollection(Mongoi.AGENTS_COUNTER).createIndex(new Document("unique", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(Mongoi.AGENTS_COUNTER, "{ \"unique\" : \"unique\" , \"id\" : 0}");
		
		
		MongoCollection<Document> shopmansCollection=new Mongoi().getCollection(Mongoi.SHOPMANS);
		shopmansCollection.createIndex(new Document("login", 1), new IndexOptions().unique(true));
		shopmansCollection.createIndex(new Document("id", 1), new IndexOptions().unique(true));
		new Mongoi().getCollection(Mongoi.SHOPMANS_COUNTER).createIndex(new Document("unique", 1), new IndexOptions().unique(true));
		new Mongoi().doInsert(Mongoi.SHOPMANS_COUNTER, "{ \"unique\" : \"unique\" , \"id\" : 0}");
		
		MongoCollection<Document> providersCollection=new Mongoi().getCollection(Mongoi.PROVIDERS);
		providersCollection.createIndex(new Document("fullName", 1), new IndexOptions().unique(true));
		
	}
	
	public FindIterable<Document> doFindInvoices(String keys){
		return doFindInvoices(keys.split(" "));
	}
	
	public FindIterable<Document> doFindInvoices(String[] paths){
		
		List<String> list=new LinkedList<String>();
		for(String str:paths){
			if(str!=null)if(!str.equals(""))list.add(str);
		}
		String[] strl=new String[list.size()];
		for(int i=0;i<list.size();i++){
			strl[i]=list.get(i);
			System.out.println("'"+strl[i]+"'<-");
		}
		FindIterable<Document> iterable=doFindLike(INVOICES, new String[]{"reference","client.consummer","agent.consummer","client.address","agent.address","client.rfc","agent.rfc","items.code","items.mark","items.description"}, strl,10).sort(new Document("$natural",-1));
		//System.out.println(iterable.count()+" hallados");
		return iterable;
	}
	
	public List<Document> doFindAgentHistory(String code,int size, int page){
		return doFindHistory(code, "agent.code", size, page);
	}
	public List<Document> doFindClientHistory(String code,int size, int page){
		return doFindHistory(code, "client.code", size, page);
	}
	public List<Document> doFindHistory(String code,String where,int size, int page){
		MongoCursor<Document> cursor=doFindMatches(INVOICES, where, code).sort(new Document("reference",1)).iterator();
		//System.out.println("cursor="+new Gson().toJson(cursor));
		List<Document> list=new LinkedList<Document>();
		int i=0;
		while(cursor.hasNext()){
			if(i>=size*page&&i<(size*page+size))
				list.add(cursor.next());
			if(i>=(size*page+size))break;
			i++;
		}
		return list;
	}
}
