import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class Card extends HttpServlet{
	

public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		HttpSession session = request.getSession();
		
//		System.out.println("sessionId="+session.getId());
//		Cookie cookie = new Cookie("JSESSIONID",session.getId());
//		cookie.setMaxAge(60);
//		cookie.setPath("/test");
//		response.addCookie(cookie);
		HttpClient http = new DefaultHttpClient();
//		HttpClient http = null;
//		response.setCharacterEncoding("utf-8");
		
		
//		HttpClient http = new DefaultHttpClient();
		
		
		String url = "http://card.gdcp.cn/getpasswdPhoto.action";
		HttpGet httpget = new HttpGet(url);
		HttpResponse resp = http.execute(httpget);
		
		
//		CookieStore store =  new BasicCookieStore();
		String setCookie = resp.getFirstHeader("Set-Cookie").getValue();
		
		Header[] h= resp.getAllHeaders();
		for (Header header : h) {
			System.out.println(header);
		}
		System.out.println(setCookie);
		session.setAttribute("cookie", setCookie);
//		String JSESSIONID = setCookie.substring(0,setCookie.indexOf(";"));
//		System.out.println(setCookie);
		
		InputStream is = (InputStream) resp.getEntity().getContent();
		
//		File f = new File("c:\\b.jpg");
//		BufferedInputStream bis = new BufferedInputStream(is);
//		FileOutputStream fos = new FileOutputStream(f);
//		byte[] b = new byte[1024];
//		int len = 0;
//		while((len = bis.read()) != -1){
//			fos.write(b,0,len);
//		}
//		is.close();
//		fos.flush();
//		fos.close();
//		ServletOutputStream sos = response.getOutputStream();
//		sos.write(is);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int len=0;
		while((len = is.read(b,0,1024))> 0){
			baos.write(b,0,len);
		}
		byte[] b2 = baos.toByteArray();
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		out.write(b2);
		out.close();
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request,response);
	}

}
