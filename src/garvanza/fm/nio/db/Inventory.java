package garvanza.fm.nio.db;

import org.bson.Document;

import garvanza.fm.nio.InvoiceItem;
import garvanza.fm.nio.Product;

public abstract class Inventory {
	
	public static void incrementStored(Product product,float amount){
		incrementStored(product.getId(), amount);
	}
	
	public static void incrementStored(long id,float amount){
		new Mongoi().doIncrement(Mongoi.PRODUCTS, "{ \"id\" : "+id+" }", "stored", amount);
	}
	
	public static void decrementStored(long id,float amount){
		new Mongoi().doIncrement(Mongoi.PRODUCTS, "{ \"id\" : "+id+" }", "stored", -amount);
	}
	
	public static void incrementStored(InvoiceItem item){
		incrementStored(item.getId(),item.getQuantity());
	}
	
	public static void decrementStored(InvoiceItem item){
		decrementStored(item.getId(),item.getQuantity());
	}
	
	public static boolean exists(long id){
		Document dbObject=new Mongoi().doFindOne(Mongoi.PRODUCTS, "{ \"id\" : "+id+" }");
		if(dbObject!=null)return true;
		return false;
	}
	
	public static boolean exists(String code){
		Document dbObject=new Mongoi().doFindOne(Mongoi.PRODUCTS, "{ \"code\" : \""+code+"\" }");
		if(dbObject!=null)return true;
		return false;
	}
	
	public static boolean exists(InvoiceItem item){
		if(exists(item.getId()))return true;
		else if(exists(item.getCode().replace(".", "")))return true;
		return false;
	}
	
}
