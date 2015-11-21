package garvanza.fm.nio;

import org.bson.Document;

/*import org.json.JSONException;
import org.json.JSONObject;
*/
public class Destiny {
	/*
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    */
	private Long id;
    
    /*@Basic */
	private String address="mostrador";
    
	public Destiny(String address) {
		super();
		this.address = address;
	}
    
    public Destiny(Document json) {
		id=json.getLong("id");
		address=json.getString("address");
	}

	public String getAddress() {
		return address;
	}
    
    public String toJson(){
    	return "{\"id\":\""+id+"\",\"address\":\""+address+"\"}";
    }

}

