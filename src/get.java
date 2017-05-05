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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class get extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		response.setContentType("application/json;charset=utf-8");
		HttpSession session = request.getSession();
		String cookie = (String) session.getAttribute("cookie");
		
//		Cookie[] cookies = request.getCookies();
//		for (Cookie cookie : cookies) {
//			System.out.println(cookie);
//		}

		HttpClient http = new DefaultHttpClient();
	
		
//		String url1 = "http://card.gdcp.cn/getpasswdPhoto.action";
//		String url1 = "http://card.gdcp.cn/pages/card/homeLogin.action";
		HttpGet get = new HttpGet("http://card.gdcp.cn/pages/card/getCheckpic.action?rand=2011.7839089697563");

		get.setHeader("Cookie", cookie);

		String url1 = "http://card.gdcp.cn/pages/card/loginstudent.action";
		http.execute(get);
		get.releaseConnection();
//		HttpClient http = new DefaultHttpClient();
		HttpPost post = new HttpPost(url1);
		post.setHeader("Accept","image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, */*");
		post.setHeader("Accept-Encoding","gzip, deflate");
		post.setHeader("Accept-Language","zh-CN");
		post.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0)");
		post.setHeader("Referer","http://card.gdcp.cn/pages/card/homeLogin.action");
		post.setHeader("Content-Type","application/x-www-form-urlencoded");
		Map<String, String> map = new HashMap<String, String>();
		map.put("userType", "1");
		map.put("rand", "2011");
		map.put("passwd", request.getParameter("password"));
		map.put("name", request.getParameter("username"));
		map.put("loginType", "2");
		map.put("imageField.y", "10");
		map.put("imageField.x", "40");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for(String key : map.keySet()){
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		post.setHeader("Cookie", cookie);
//				}
//		for (NameValuePair cookie : nameValuePairs) {
//			System.out.println(cookie);
//		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"GB2312"));
		HttpResponse resp = http.execute(post);
		System.out.println("------------------------------------------------");

//		InputStream is = resp.getEntity().getContent();
		String str = EntityUtils.toString(resp.getEntity());
		System.out.println(str);
		PrintWriter writer = response.getWriter();
		String st="";
		if(str.contains("√‹¬Î¥ÌŒÛ")){
//			status = -1;
			st = "-1";
		}else{
//			status = 1;
			st = "1";
		}
		post.releaseConnection();
		writer.write(st);
//		BufferedInputStream bis = new BufferedInputStream(is);
//		File f = new File("c:\\a.jpg");
//		FileOutputStream fos = new FileOutputStream(f);
//		byte[] b = new byte[1024];
//		int len = 0;
//		while((len = bis.read()) != -1){
//			fos.write(b,0,len);
//		}
//		is.close();
//		fos.flush();
//		fos.close();
		
//		print.write()
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request,response);
	}
}
