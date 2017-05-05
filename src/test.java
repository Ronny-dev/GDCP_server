import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class test extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ClientProtocolException, IOException{
//		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
//		HttpGet get = new HttpGet("http://card.gdcp.cn/getpasswdPhoto.action");
//		HttpResponse httpResponse = closeableHttpClient.execute(get);
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cookie = (String) session.getAttribute("cookie");
		HttpClient http = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://card.gdcp.cn/accounttodayTrjn.action");
		get.setHeader("Cookie",cookie);
		HttpResponse resp = http.execute(get);
		String str = EntityUtils.toString(resp.getEntity());
		
		System.out.println(str);
		get.releaseConnection();
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
	}

}
