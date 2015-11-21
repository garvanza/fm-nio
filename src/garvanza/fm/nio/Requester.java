package garvanza.fm.nio;

import org.bson.Document;

public class Requester {

    /*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    
    /*@Basic*/ private String name="publico";
    
    /*@Basic*/ private String consummer="publico";
    
    /*@Transient*/ private Client client=new Client();
    
	public Requester(String name) {
		super();
		this.name = name;
	}

	public Requester(String name,Client client) {
		super();
		this.name = name;
		consummer=client.getConsummer();
	}
    
    public Requester(Document json) {
		id=json.getLong("id");
		name=json.getString("name");
		consummer=json.getString("consummer");
	}

	public String getName() {
		return name;
	}
    
    public String getConsummer(){
    	return consummer;
    }
    
    public Long getId() {
		return id;
	}
    
    public String toJson(){
    	return "{\"id\":\""+id+"\",\"name\":\""+name+"\",\"consummer\":\""+consummer+"\"}";
    }

}
