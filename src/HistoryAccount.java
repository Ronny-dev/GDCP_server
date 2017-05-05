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

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.bootstrap.HttpServer;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class HistoryAccount extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String cookie = (String) session.getAttribute("cookie");
		String number = (String) session.getAttribute("number");
		
		String BasicUrl = "http://card.gdcp.cn";
		String url = "http://card.gdcp.cn/accounthisTrjn.action";
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		get.setHeader("Cookie",cookie);
		get.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0)");
		HttpResponse response = client.execute(get);
		String entity = EntityUtils.toString(response.getEntity());
		get.releaseConnection();
		String regex = "action=\"(.*?)\"";
		Pattern pattern = Pattern.compile(regex,Pattern.DOTALL);
		Matcher matcher = pattern.matcher(entity);
		matcher.find();
		String connectUrl = BasicUrl.concat(matcher.group(1).toString());
		//account=68007&inputObject=all&Submit=+%C8%B7+%B6%A8+
//		System.out.println(connectUrl);
		HttpPost post = new HttpPost(connectUrl);
		post.setHeader("Cookie",cookie);
		Map<String, String> map = new HashMap<String, String>();
		map.put("account", number);
		map.put("inputObject", "all");
		map.put("Submit", "+%C8%B7+%B6%A8+");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (String key : map.keySet()) {
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"GB2312"));
		response = client.execute(post);
		entity = EntityUtils.toString(response.getEntity());
//		System.out.println(entity);
		matcher = pattern.matcher(entity);
		post.releaseConnection();
		matcher.find();
		connectUrl = BasicUrl.concat(matcher.group(1).toString());
		System.out.println(connectUrl);
		post = new HttpPost(connectUrl);
		post.setHeader("Cookie",cookie);
		map = new HashMap<String, String>();
		//inputStartDate=20170426&inputEndDate=20170503
		//http://card.gdcp.cn/accounthisTrjn.action?__continue=fd8ffd792f1fe33dd59eaabf379b2037
		map.put("inputStartDate", "20170426");
		map.put("inputEndDate", "20170503");
		nameValuePairs = new ArrayList<NameValuePair>();
		for(String key : map.keySet()){
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"GB2312"));
		response = client.execute(post);
		entity = EntityUtils.toString(response.getEntity());
//		System.out.println(entity);
		post.releaseConnection();
		matcher = pattern.matcher(entity);
		matcher.find();
		connectUrl = BasicUrl.concat("/accounthisTrjn.action").concat(matcher.group(1).toString());
		get = new HttpGet(connectUrl);
		get.setHeader("Cookie",cookie);
		get.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.3; WOW64; Trident/7.0; .NET4.0E; .NET4.0C; .NET CLR 3.5.30729; .NET CLR 2.0.50727; .NET CLR 3.0.30729; Tablet PC 2.0)");
		response = client.execute(get);
		entity = EntityUtils.toString(response.getEntity());
		get.releaseConnection();
//		System.out.println(entity);
		
//		String regex_2 = "<tr.*?list.*?>(.*?)</tr>";
//		Pattern pattern_2 = Pattern.compile(regex_2,Pattern.DOTALL);
//		Matcher matcher_2 = pattern_2.matcher(entity);
//		String regex_3 = "<td.*?>(.*?)</td>";
//		Pattern pattern_3 = Pattern.compile(regex_3,Pattern.DOTALL);
//		Matcher matcher_3;
//		ArrayList al = new ArrayList();
//		while(matcher_2.find()){
//			ArrayList<String> list = new ArrayList<String>();
//			String str1 = matcher_2.group(1);
//			matcher_3 = pattern_3.matcher(str1);
//			while(matcher_3.find()){
//				list.add(matcher_3.group(1).toString());
//			}
//			AccountBean bean = new AccountBean();
//			bean.setDate(list.get(0).toString());
//			bean.setType(list.get(1).toString());
//			bean.setName(list.get(2).toString());
//			bean.setAccount(list.get(3).toString());
//			bean.setMoney(list.get(4).toString());
//			bean.setBalance(list.get(5).toString());
//			bean.setTimes(list.get(6).toString());
//			bean.setStatus(list.get(7).toString());
//			al.add(bean);
//		}
//		JSONObject jo = new JSONObject();
//		regex_2 = "及:(.*?);.*?为:(.*?)（.*共(\\d.*?)页.*第(\\d.*?)";//共2页&nbsp;&nbsp;当前第1页&nbsp
//		pattern = Pattern.compile(regex_2,Pattern.DOTALL);
//		matcher = pattern.matcher(entity);
//		matcher.find();
//		jo.put("allTimes", matcher.group(1).toString());
//		jo.put("totalMoney", matcher.group(2).toString());
//		jo.put("totalPage", matcher.group(3).toString());
//		jo.put("CurrentPage", matcher.group(4).toString());
//		al.add(jo);
		//http://card.gdcp.cn/accountconsubBrows.action
		//inputStartDate=20170403&inputEndDate=20170503&pageNum=2
		connectUrl = BasicUrl.concat("/accountconsubBrows.action");
		post = new HttpPost(connectUrl);
		post.setHeader("Cookie",cookie);
		map = new HashMap<String, String>();
		map.put("inputStartDate", "20170426");
		map.put("inputEndDate", "20170503");
		map.put("pageNum", "2");
		nameValuePairs = new ArrayList<NameValuePair>();
		for(String key : map.keySet()){
			nameValuePairs.add(new BasicNameValuePair(key, map.get(key)));
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs,"GB2312"));
		response = client.execute(post);
		entity = EntityUtils.toString(response.getEntity());
		
		ArrayList al = getList(entity);
		PrintWriter pw = resp.getWriter();
		pw.println(new Gson().toJson(al));
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}

	public ArrayList getList(String entity){
		String regex_2 = "<tr.*?list.*?>(.*?)</tr>";
		Pattern pattern_2 = Pattern.compile(regex_2,Pattern.DOTALL);
		Matcher matcher_2 = pattern_2.matcher(entity);
		String regex_3 = "<td.*?>(.*?)</td>";
		Pattern pattern_3 = Pattern.compile(regex_3,Pattern.DOTALL);
		Matcher matcher_3;
		ArrayList al = new ArrayList();
		while(matcher_2.find()){
			ArrayList<String> list = new ArrayList<String>();
			String str1 = matcher_2.group(1);
			matcher_3 = pattern_3.matcher(str1);
			while(matcher_3.find()){
				list.add(matcher_3.group(1).toString());
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
		JSONObject jo = new JSONObject();
		regex_2 = "及:(.*?);.*?为:(.*?)（.*共(\\d.*?)页.*第(\\d.*?)";//共2页&nbsp;&nbsp;当前第1页&nbsp
		pattern_2 = Pattern.compile(regex_2,Pattern.DOTALL);
		matcher_2 = pattern_2.matcher(entity);
		matcher_2.find();
		jo.put("allTimes", matcher_2.group(1).toString());
		jo.put("totalMoney", matcher_2.group(2).toString());
		jo.put("totalPage", matcher_2.group(3).toString());
		jo.put("CurrentPage", matcher_2.group(4).toString());
		al.add(jo);
		return al;
	}
	
}
