import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class CurrentAccount extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String cookie = (String) session.getAttribute("cookie");
		String number = (String) session.getAttribute("number");
//		String url = "http://card.gdcp.cn/accounttodayTrjn.action";
		String url = "http://card.gdcp.cn/accounttodatTrjnObject.action";
		//account=68007&inputObject=all&Submit=+%C8%B7+%B6%A8+
		Map<String,String> map = new HashMap<String, String>();
		map.put("account", number);
		map.put("inputObject", "all");
		map.put("Submit", "+%C8%B7+%B6%A8+");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		post.setHeader("Cookie", cookie);
		post.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0)");
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"GB2312"));
		HttpResponse response = client.execute(post);
		String entity = EntityUtils.toString(response.getEntity());

		String regex = "<tr.*?list.*?>(.*?)</tr>";
		Pattern pattern = Pattern.compile(regex,Pattern.DOTALL);
		Matcher matcher = pattern.matcher(entity);
		String regex1 = "<td.*?>(.*?)</td>";
		Pattern pattern1 = Pattern.compile(regex1,Pattern.DOTALL);
		Matcher matcher1;
		ArrayList al = new ArrayList();
		while(matcher.find()){
			matcher1 = pattern1.matcher(matcher.group(1).toString());
			ArrayList list = new ArrayList<String>();
			while(matcher1.find()){
				list.add(matcher1.group(1));
//				System.out.print(matcher1.group(1)+"---");
			}
			AccountBean bean = new AccountBean();
			bean.setDate(list.get(0).toString());
			bean.setType(list.get(1).toString());
			bean.setName(list.get(2).toString());
			bean.setAccount(list.get(3).toString());
			bean.setMoney(list.get(4).toString());
			bean.setBalance(list.get(5).toString());
			bean.setTimes(list.get(6).toString());
			bean.setStatus(list.get(7).toString());
			al.add(bean);
		}
		regex = "¼°:(.*?);.*?Îª:(.*?)£¨";
		pattern = Pattern.compile(regex,Pattern.DOTALL);
		matcher = pattern.matcher(entity);
		matcher.find();
		System.out.println(matcher.group(1));
		System.out.println(matcher.group(2));
		
		JSONObject jo = new JSONObject();
		jo.put("allTimes", matcher.group(1).toString());
		jo.put("totalMoney", matcher.group(2).toString());
		al.add(jo);
		Gson gson = new Gson();
		PrintWriter pw = resp.getWriter();
		pw.println(gson.toJson(al));
//		System.out.println(gson.toJson(al));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	
}
