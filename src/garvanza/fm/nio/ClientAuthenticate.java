package garvanza.fm.nio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.google.gson.Gson;

import garvanza.fm.nio.OnlineClient;
import garvanza.fm.nio.db.Mongoi;

public class ClientAuthenticate extends HttpServlet{

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		System.out.println("AUTHENTICATING");
		int clientReference=new Integer(req.getParameter("clientReference"));
		System.out.println("clientReference:"+clientReference);
		String password=req.getParameter("password");
		//System.out.println("password:"+password);
		String login=req.getParameter("login");
		System.out.println("lg "+login);
		boolean lock=new Boolean(req.getParameter("lock"));
		System.out.println("lk "+lock);
		String newUserName=req.getParameter("newUserName");
		System.out.println("nu "+newUserName);
		String token=req.getParameter("token");
		System.out.println("tok :"+token);
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		if(login!=null){
			
			Document dbshopman=new Mongoi().doFindOne(Mongoi.SHOPMANS, "{ \"login\" : \""+login+"\" }");
			System.out.println("dbshopman "+new Gson().toJson(dbshopman));
			if(dbshopman!=null&&password!=null){
				if(dbshopman.get("password").toString().equals(MD5.get(password))&&
						dbshopman.get("login").toString().equals(login)){
					OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
					//System.out.println("pass&log passes");
					if(!token.equals(onlineClient.getToken())){
						resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
						resp.getWriter().write("No autorizado");
						System.out.println("token dismatch");
						return;
					}
					//System.out.println("token passes");
					Shopman shopman=new Gson().fromJson(new Gson().toJson(dbshopman), Shopman.class);
					System.out.println("shopman "+new Gson().toJson(shopman));
					onlineClient.setShopman(shopman);
					onlineClient.setLogged(true);
					onlineClient.setLocked(false);
					if(onlineClient.isAuthenticated(req)){
						//System.out.println("client authenticated passes");
						resp.getWriter().print("{ \"authenticated\": "+true+" , \"shopman\" : { \"name\" : \""+shopman.getName()+"\" , \"login\" : \""+shopman.getLogin()+"\" } }");
						return;
					}
					else{
						resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
						resp.getWriter().write("No autorizado pss!=");
						System.out.println("is not authenticated");
						return;
					}
				}
				else{
					resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
					resp.getWriter().write("pass dismatch");
					System.out.println("pass dismatch");
					return;
				}
			}
			else {
				resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write("No autorizado");
				System.out.println("login/password dismatch");
				return;
			}
		}
		else if(password!=null){
			OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
			Document dbshopman=new Mongoi().doFindOne(Mongoi.SHOPMANS, "{ \"login\" : \""+onlineClient.getShopman().getLogin()+"\" }");
			if(dbshopman.get("password").toString().equals(MD5.get(password))){
				if(!token.equals(onlineClient.getToken())){
					resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
					resp.getWriter().write("No autorizado tk!=");
					System.out.println("token dismatch");
					return;
				}

				onlineClient.setLocked(false);
				if(onlineClient.isAuthenticated(req)){
					resp.getWriter().print("{ \"authenticated\": "+true+" }");
					return;
				}
				else{
					resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
					resp.getWriter().write("No autorizado ocIsA=f");	
					System.out.println("is not authenticated");
					return;
				}
			}
			else{
				resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write("No autorizado intCode=pss!=");
				System.out.println("pass dismatch!!!");
				return;
			}
		}
		else if(lock){
			OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
			if(!token.equals(onlineClient.getToken())){
				//HttpServletResponse.SC_UNAUTHORIZED
				resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write("No autorizado");
				return;
			}
			onlineClient.setLocked(true);
			resp.getWriter().print("{ \"authenticated\": "+false+" }");
			return;
		}
		else if(newUserName!=null){
			OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
			if(!onlineClient.hasAccess(AccessPermission.SHOPMAN_CREATE)){
				resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write("No autorizado");
				System.out.println("no perm to create");
				return;
			}
			String newUserLogin=req.getParameter("newUserLogin");
			String newUserPassword=req.getParameter("newUserPassword");
			String reNewUserPassword=req.getParameter("reNewUserPassword");
			if(!newUserPassword.equals(reNewUserPassword)){
				resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
				resp.getWriter().write("passwords dismatch");
				System.out.println("passwords dismatch");
				return;
			}
			Shopman shopman= new Shopman(newUserName,newUserLogin, newUserPassword,new ArrayList<AccessPermission>(Arrays.asList(
					AccessPermission.BASIC
			)));
			shopman.persist();
			System.out.println("creado "+newUserName);
			return;
		}
		else{
			resp.setStatus( HttpServletResponse.SC_UNAUTHORIZED);
			resp.getWriter().write("No autorizado");
			return;
		}

	}
	
	public static boolean hasAccess(OnlineClient onlineClient, List<AccessPermission> permissions){
		int size=permissions.size();
		List<AccessPermission> clientPermissions=onlineClient.getShopman().getPermissions();
		int csize=clientPermissions.size();
		System.out.println(new Gson().toJson(onlineClient));
		for(int j=0;j<csize;j++){
			System.out.println("perm "+clientPermissions.get(j));
			if(clientPermissions.get(j).equals(AccessPermission.ADMIN)){
				return true;
			}
		}
		for(int i=0;i<size;i++){
			AccessPermission permission=permissions.get(i);
			boolean matches=false;
			for(int j=0;j<csize;j++){	
				if(permission.equals(clientPermissions.get(j))){
					matches=true;
					break;
				}
			}
			if(!matches)return false;
		}
		return true;
	}
	
	public static boolean hasAccess(OnlineClient onlineClient, AccessPermission[] permissions){
		return hasAccess(onlineClient, Arrays.asList(permissions));
	}
	
	public static boolean hasAccess(OnlineClient onlineClient, AccessPermission permission){
		return hasAccess(
				onlineClient,
				new ArrayList<AccessPermission>(Arrays.asList(permission))
		);
	}
	
	public static void main(String[] args) {
		OnlineClient onlineClient= new OnlineClient(1, "ipAddress", "sessionId");
		String json=new Gson().toJson(onlineClient);
		OnlineClient oc=new Gson().fromJson(json, OnlineClient.class);
		System.out.println(new Gson().toJson(oc));
	}
	
}
