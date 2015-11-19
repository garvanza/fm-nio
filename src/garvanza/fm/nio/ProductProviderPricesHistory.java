package garvanza.fm.nio;

import com.google.gson.Gson;

import garvanza.fm.nio.db.Mongoi;

public class ProductProviderPricesHistory {

	private Long id;
	
	private double quantity;
	
	private double price;
	
	private long time;

	private String login;
	
	public ProductProviderPricesHistory(long id, double quantity, double price, long time,String login) {
		this.id = id;
		this.quantity=quantity;
		this.price = price;
		this.time = time;
		this.login=login;
	}
	
	public void persist(){
		String js=new Gson().toJson(this);
		new Mongoi().doInsert(Mongoi.PRODUCT_PROVIDER_PRICES_HISTORY,js);
	}
	
}
