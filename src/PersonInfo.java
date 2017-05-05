import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import com.google.gson.Gson;




public class PersonInfo extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
//		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cookie = (String) session.getAttribute("cookie");
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://card.gdcp.cn/accountcardUser.action");
		get.setHeader("Cookie",cookie);
		HttpResponse resp = client.execute(get);
		String str = EntityUtils.toString(resp.getEntity());
		System.out.println(str);
		String regex = "Ãû.*?\"left\">(.*?)</.*?\"left\">(.*?)</" +
				".*²¿ÃÅ.*?\"left\">(.*?)</.*?Ì¬.*?\"left\">(.*?)</" +
				".*?Ì¬.*?\"left\">(.*?)</.*?¶î.*?\"neiwen\">(.*?)£¨" +
				".*?Ì¬.*?;(.*?)</.*?Ì¬.*?;(.*?)</";
		Pattern pattern =  Pattern.compile(regex,Pattern.DOTALL);
		Matcher matcher = pattern.matcher(str);
		matcher.find();
		String name = matcher.group(1);
		String number = matcher.group(2);
		String department = matcher.group(3);
		String cardStatus = matcher.group(4);
		String freezeStatus = matcher.group(5);
		String balance = matcher.group(6);
		String checkStatus = matcher.group(7);
		String lossStatus = matcher.group(8);
		session.setAttribute("number", number);
		Bean b = new Bean();
		b.setName(name);
		b.setNumber(number);
		b.setDepartment(department);
		b.setCardStatus(cardStatus);
		b.setFreezeStatus(freezeStatus);
		b.setBalance(balance);
		b.setCheckStatus(checkStatus);
		b.setLossStatus(lossStatus);
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.println(gson.toJson(b));
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 doGet(request,response);
	}
}
