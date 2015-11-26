package garvanza.fm.nio;

import org.bson.Document;

public class ClientFactory{
	
	private ClientFactory(){}
	
	public static Client create(Document json){
		System.out.println("creating client for "+json.toJson());
		Client client=null;
			String code="";
			if(json.containsKey("code")){
				code=json.getString("code").toUpperCase();
			}
			String consummer="";
			if(json.containsKey("consummer")){
				consummer=json.getString("consummer").toUpperCase();
			}
			int consummerType=1;
			if(json.containsKey("consummerType")){
				consummerType=new Integer(json.getDouble("consummerType").intValue());
			}
			String address="";
			if(json.containsKey("address")){
				address=json.getString("address").toUpperCase();
			}
			String interiorNumber="";
			if(json.containsKey("interiorNumber")){
				interiorNumber=json.getString("interiorNumber").toUpperCase();
			}
			String exteriorNumber="";
			if(json.containsKey("exteriorNumber")){
				exteriorNumber=json.getString("exteriorNumber").toUpperCase();
			}
			String suburb="";
			if(json.containsKey("suburb")){
				suburb=json.getString("suburb").toUpperCase();
			}
			String locality="";
			if(json.containsKey("locality")){
				locality=json.getString("locality").toUpperCase();
			}
			String city="";
			if(json.containsKey("city")){
				city=json.getString("city").toUpperCase();
			}
			String country="";
			if(json.containsKey("country")){
				country=json.getString("country").toUpperCase();
			}
			String state="";
			if(json.containsKey("state")){
				state=json.getString("state").toUpperCase();
			}
			String email="";
			if(json.containsKey("email")){
				email=json.getString("email").toUpperCase();
			}
			String cp="";
			if(json.containsKey("cp")){
				cp=json.getString("cp").toUpperCase();
			}
			String rfc="";
			if(json.containsKey("rfc")){
				rfc=json.getString("rfc").toUpperCase();
			}
			String tel="";
			if(json.containsKey("tel")){
				tel=json.getString("tel").toUpperCase();
			}
			int payment=0;
			if(json.containsKey("payment")){
				payment=new Integer(json.getDouble("payment").intValue());
			}
			String reference="";
			if(json.containsKey("reference")){
				reference=json.getString("reference").toUpperCase();
			}
			String aditionalReference="";
			if(json.containsKey("aditionalReference")){
				aditionalReference=json.getString("aditionalReference").toUpperCase();
			}
			client=new Client(code, consummer, consummerType, address, interiorNumber, exteriorNumber, suburb, locality, city, country, state, email, cp, rfc, tel, payment, reference, aditionalReference);
			client.setCode(client.getHash());
		
		System.out.println("client succesfuly created");
		return client;
		
	}

}
