package garvanza.fm.nio;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
/*import org.json.JSONException;
import org.json.JSONObject;
*/
import com.google.gson.Gson;

public class Langed {

	private static Map<String,Document> json=new HashMap<String, Document>();
	
	private Langed(Class<Object> class_){
		
	}
	
	public static String get(String locale, String key, String... args){
		//int index=Thread.currentThread().getStackTrace().length-1;
		StackTraceElement traceElement=Thread.currentThread().getStackTrace()[2];
		/*for(int i=0;i< Thread.currentThread().getStackTrace().length;i++){
			StackTraceElement te =Thread.currentThread().getStackTrace()[i];
			System.out.println();
			System.out.println(te.getClassName());
			System.out.println(te.getFileName());
		}*/
		String name=traceElement.getClassName();
		return fromLang_(name, locale, key, args);
	}
	private static String fromLang_(String name, String locale, String key, String... args){
		if(json.containsKey(name)){
			Document jsono=json.get(name);
			if(jsono.containsKey(key)){
				Document theKey=(Document)jsono.get(key);
				if(theKey.containsKey(locale)){
					return getf((String)theKey.get(locale),args);
				}
				else {
					return getf((String)theKey.get("en"),args);
				}
			}
			else {
				return "[No doc found]";
			}
		}
		else {
			String resource="/"+name.replaceAll("\\.", "/")+".lang.json";
			System.out.println("resource "+resource);
			Class<?> clazz=null;
			try {
				clazz = Class.forName(name);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println(name+" "+locale+" "+key);
			InputStream is= clazz.getResourceAsStream(resource);
			
			String jsonStr=Utils.getStringFromInputStream(is);
			Document jsonObject=null;
			jsonObject = new Gson().fromJson(jsonStr,Document.class);
			json.put(name, jsonObject);
			return fromLang_(name, locale, key, args);
		}
	}
	
	private static String getf(String str,String... args){
		String ret=str.toString();
		for(int i=0;i<args.length;i++){
			ret=ret.replaceAll("\\$\\{"+i+"\\}", args[i]);
		}
		return ret;
	}
}
