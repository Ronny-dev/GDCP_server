<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="java.util.*" %>
    
    <%
    	String strSessionId = session.getId();
    	session.setMaxInactiveInterval(60);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
<img alt="wj" src="t2.jpg">
 -->
 <form action="get">
 用户名:<input type="text" name="username" value="1513157138"><br>
密码: <input type="text" name="password"><br>
 <input type="submit" value="submit">

 </form>

<script type="text/javascript">
/* function f(){
	
	/* var xmlHttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			var res = xmlHttp.responseText;
			alert(res);
		}
		var res = xmlHttp.responseText;
		alert(res);
		alert("111");
	}
	xmlHttp.open("POST","/test/d",true);
	xmlHttp.send(); */
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function(){
        if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
            var res = xmlHttp.responseText;
            alert(res);
        }

    }
    xmlHttp.open("GET","http://localhost:8080/test/d?phone=123",false);
    xmlHttp.send();
} */
	
</script>

</body>
</html>