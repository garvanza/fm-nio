package garvanza.fm.nio.tests;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import garvanza.fm.nio.Wishing;

public class TestWhishing {

	@Test
	public void test() {
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        String clientParam="{\"code\":\"bbd4d562e38810087c9fa9b56e566e76\",\"consummer\":\"RAYNMUNE\",\"consummerType\":\"1\",\"address\":\"CERRADA DEL FUNDILLO #36 COL. SANBRIAGO\",\"interiorNumber\":\"\",\"exteriorNumber\":\"\",\"suburb\":\"\",\"locality\":\"\",\"city\":\"MORELIA, MICH.\",\"country\":\"\",\"state\":\"\",\"email\":\"\",\"cp\":\"\",\"rfc\":\"PARA FACTURAR\",\"tel\":\"\",\"payment\":0,\"reference\":\"\",\"aditionalReference\":\"\"}";

        String listParam="[{\"_id\":{\"$oid\":\"560edac7e4b06a8e24a1eed5\"},\"id\":63656,\"hash\":\"19fe14fb0475f563396e40a05570e905\",\"code\":\"70713-CFN\",\"mark\":\"CIFUNSA\",\"unitPrice\":6.170000076293945,\"unit\":\"PZA\",\"description\":\"CODO 1/2X90° GALVANIZADO CIFUNSA\",\"stored\":0,\"collecting\":0,\"sending\":0,\"requested\":0,\"missed\":0,\"productPriceKind\":1,\"calls\":0,\"disabled\":false,\"edited\":false,\"firstTimeInventored\":false,\"quantity\":1}]";
        String sellerParam="{\"id\":-1,\"code\":\"none\"}";

        String agentParam="{\"_id\":{\"$oid\":\"506e3f2924acf6407516582a\"},\"active\":true,\"address\":\"CERRADA DEL ANO AY QUE RICO\",\"city\":\"MORELIA\",\"code\":\"5da6094e3871a2385248d4b0144b9cf3\",\"consummer\":\"RAYNMUNE\",\"consummerType\":2,\"country\":\"MEXICO\",\"cp\":\".\",\"email\":\"\",\"id\":1035,\"payment\":15,\"rfc\":\".\",\"state\":\"MICHOACAN\",\"tel\":\".\"}";

        String requesterParam="{\"id\":-1,\"name\":\"publico\",\"consummer\":\"publico\"}";
        String shopmanParam="{\"name\":\"god of gods - root\",\"login\":\"root\"}";
        String destinyParam="{\"id\":-1,\"address\":\"mostrador\"}";
        String argsParam="";
        String commandParam="@ic";
        
        when(request.getParameter("clientParam")).thenReturn(clientParam);
        when(request.getParameter("listParam")).thenReturn(listParam);
        when(request.getParameter("sellerParam")).thenReturn(sellerParam);
        when(request.getParameter("agentParam")).thenReturn(agentParam);
        when(request.getParameter("requesterParam")).thenReturn(requesterParam);
        when(request.getParameter("shopmanParam")).thenReturn(shopmanParam);
        when(request.getParameter("destinyParam")).thenReturn(destinyParam);
        when(request.getParameter("argsParam")).thenReturn(argsParam);
        when(request.getParameter("commandParam")).thenReturn(commandParam);
        
        OutputStream out=new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(out);
        try {
			when(response.getWriter()).thenReturn(writer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new Wishing().doPost(request, response);
        
		fail("Not yet implemented");
	}

}
