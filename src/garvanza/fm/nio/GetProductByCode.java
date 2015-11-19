package garvanza.fm.nio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;

import garvanza.fm.nio.db.Mongoi;

public class GetProductByCode  extends HttpServlet{

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		int clientReference=new Integer(req.getParameter("clientReference"));
		OnlineClient onlineClient=OnlineClients.instance().get(clientReference);
		if(!(onlineClient.isAuthenticated(req)&&(
				onlineClient.hasAccess(AccessPermission.PRODUCT_READ)||
				onlineClient.hasAccess(AccessPermission.BASIC)||
				onlineClient.hasAccess(AccessPermission.ADMIN)
				))){
			resp.sendError(resp.SC_UNAUTHORIZED,"acceso denegado");return;
		}
		
		String code=req.getParameter("code").toUpperCase();
		int requestNumber=new Integer(req.getParameter("requestNumber"));
		int consummerType=new Integer(req.getParameter("consummerType"));
		Document product=new Mongoi().doFindOne(Mongoi.PRODUCTS, "{ \"code\" : \""+code+"\" }");
		if(product==null){
			System.out.println("404: product not found for "+code);
			resp.setStatus(204);
			return;
		}
		
		int productPriceKind=new Integer(product.get("productPriceKind").toString());
		float unitPrice=new Float(product.get("unitPrice").toString());
		if(consummerType==Client.TYPE_1){
			//unitPrice=product.getUnitPrice();
		}
		else if(consummerType==Client.TYPE_2){
			if(productPriceKind==Product.KIND_1)unitPrice*=Product.FACTOR_1;
			else if(productPriceKind==Product.KIND_2)unitPrice*=Product.FACTOR_2;
		}
		else if(consummerType==Client.TYPE_3){
			if(productPriceKind==Product.KIND_1)unitPrice*=Product.FACTOR_3;
			else if(productPriceKind==Product.KIND_2)unitPrice*=Product.FACTOR_4;
		}
		product.put("unitPrice", unitPrice);
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		
		String json="{\"product\":"+product.toJson()+","+
			"\"requestNumber\":"+requestNumber+"}";
		System.out.println("getProductByCode::: "+json);
		resp.getWriter().print(json);
	}

}
