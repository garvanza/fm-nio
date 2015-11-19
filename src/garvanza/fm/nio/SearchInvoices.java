package garvanza.fm.nio;

import java.io.IOException;
import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.bson.Document;

import com.mongodb.client.MongoCursor;

import garvanza.fm.nio.db.Mongoi;

public class SearchInvoices extends HttpServlet{

	private static int MAX_RESULTS=20;
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		int clientReference=new Integer(req.getParameter("clientReference"));
		OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
		if(!(onlineClient.isAuthenticated(req)&&(
				onlineClient.hasAccess(AccessPermission.GLOBAL_SEARCH)||
				onlineClient.hasAccess(AccessPermission.BASIC)||
				onlineClient.hasAccess(AccessPermission.ADMIN)
				))){
			resp.sendError(resp.SC_UNAUTHORIZED,"acceso denegado");return;
		}
		
		
		String paths=URLDecoder.decode(req.getParameter("paths"),"utf-8").replace(".","").toUpperCase();
		System.out.println("searching: "+paths);
		
		
		
		List<String> matchList = new ArrayList<String>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(paths);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		}
		String[] patterns=matchList.toArray(new String[1]);
		
		
		MongoCursor<Document> cursor=new Mongoi().doFindInvoices(patterns).iterator();
		String json="{ \"invoices\" : [";
		
		int i=0;
		while(cursor.hasNext()){
			if(i<MAX_RESULTS){
				json+=(i!=0?",":"")+cursor.next().toJson();
			}
			else break;
			i++;
			//System.out.println(cursor.next());
		}
		json+="] } ";
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		System.out.println("result::: "+json);
		resp.getWriter().print(json);
		return;
	}
	
	public static void main(String[] args) {
		MongoCursor<Document> cursor=new Mongoi().doFindInvoices("N70713").iterator();
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}

}

