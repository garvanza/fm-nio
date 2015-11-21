package garvanza.fm.nio;

import java.util.LinkedList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;

import garvanza.fm.nio.db.Mongoi;

/*@Entity*/
public class Shopman {
	
    /*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Long id;
    
    private String name;
    
    /*@Basic*/ private String login;
    
    /*@Basic*/ private String password=null;
    
    private List<AccessPermission> permissions=new LinkedList<AccessPermission>();

    public List<AccessPermission> getPermissions(){
    	return permissions;
    }
    
    public Shopman(String name, String login,String password, List<AccessPermission> permissions) {
    	this.login=login;
    	this.permissions=permissions;
    	this.password=MD5.get(password);
    	this.name=name;
	}
    
    public Document persist(){
    	Document dbObject= new Mongoi().doFindOne(Mongoi.SHOPMANS, "{ \"login\" : \""+login+"\" }");
    	if(dbObject!=null)return null;
    	else{
    		this.id=(long)new Mongoi().doIncrement(Mongoi.SHOPMANS_COUNTER, "{ \"unique\" : \"unique\" }", "id");
    		new Mongoi().doInsert(Mongoi.SHOPMANS, new Gson().toJson(this));
    		return new Mongoi().doFindOne(Mongoi.SHOPMANS, "{ \"login\" : \""+login+"\" }");
    	}
    }
    
    public Shopman(Document json) {
		id=json.getLong("id");
		if(id==null)id=-1l;
		login=json.getString("login");
		if(login==null)login="noauth";
	}

	public String getLogin() {
		return login;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
    
    public void setPassword(String password) {
    	if(password!=null)
    		this.password = MD5.get(password);
    	else this.password=null;
	}
    
    public boolean isPassword(String password){
    	if(MD5.get(password).equals(this.password))return true;
    	else return false;
    }
    
    
    
    public static Document getShopman(String login, String password){
    	Document dbObject= new Mongoi().doFindOne(Mongoi.SHOPMANS, "{ \"login\" : \""+login+"\" }");
    	if(dbObject!=null){
    		String pass=dbObject.get("password").toString();
    		if(pass.equals(MD5.get(password)))return dbObject;
    		else return null;
    	}
    	else return null;
    }

    public static void main(String[] args) {
		/*Shopman shopman= new Shopman("root","root", "rand",new ArrayList<AccessPermission>(Arrays.asList(
				AccessPermission.ADMIN
		)));
		*/
    	//shopman.persist();
    	//new Mongoi().doUpdate(Mongoi.SHOPMANS, "{\"login\":\"root\"}", "{\"name\":\"ADMIN\"}");
    	new Mongoi().doUpdate(Mongoi.SHOPMANS, "{\"login\":\"root\"}", "{\"permissions\":[\"ADMIN\"]}");
    	Document dbshopman= new Mongoi().doFindOne(Mongoi.SHOPMANS, "{\"login\":\"root\"}");
    	Shopman shopman=(Shopman)new Gson().fromJson(dbshopman.toJson(), Shopman.class);
    	System.out.println(dbshopman.toJson()+"\n"+new Gson().toJson(shopman));
	}
    
}
