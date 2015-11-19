package garvanza.fm.nio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import garvanza.fm.nio.db.Mongoi;

public class GetClientByCode extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int clientReference=new Integer(request.getParameter("clientReference"));
		String where=request.getParameter("where");
		OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
		if(!onlineClient.isAuthenticated(request)&&!(
				(where.equals(Mongoi.AGENTS)&&onlineClient.hasAccess(AccessPermission.AGENT_READ))||
				(where.equals(Mongoi.CLIENTS)&&onlineClient.hasAccess(AccessPermission.CONSUMMER_READ))||
				onlineClient.hasAccess(AccessPermission.BASIC)||
				onlineClient.hasAccess(AccessPermission.ADMIN)
				)){
			response.sendError(response.SC_UNAUTHORIZED,"acceso denegado");return;
		}
		String hash=request.getParameter("hash");
		
		Document dbObject=new Mongoi().doFindOne(where, "{\"code\" : \""+hash+"\"}");
		System.out.println("where="+where+"\ncode="+hash+"\nagent="+dbObject.toJson());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		if(dbObject!=null)response.getWriter().print(dbObject.toJson());
		else {
			response.sendError(response.SC_NOT_FOUND,"not found");
			return;
		}
		
	}

}
