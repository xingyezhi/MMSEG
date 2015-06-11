<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>NLP</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/css.css">

<script type="text/javascript">
$(function (){
$("#submit").click(function(){
var text=$("#text").val();
var select=$("#select").val();
$.ajax({
    type:"POST",
    url: "<%=basePath%>/servlet/SegServlet",
    data:{
    text:text,
    select:select
    },
    success:function(result){
   $("#result").val(result);
    }
    }); 
});

});
</script>
 </head>
  
 <body>
 <form action="" method="post" class="smart-green">
<h1>中文分词</h1>

<label>
<input  type="email"  placeholder="旷志鑫      2014E8018661108     信息工程研究所" readonly/>
</label>

<label>
<span>输入文本 :</span>
<textarea id="text"  placeholder="Please Input Here"></textarea>
</label>

<label>
<span>选择算法 :</span><select name="selection" id="select">
<option value="Complex">Complex</option>
<option value="Simple">Simple</option>
</select>
</label>

<label>
<span>&nbsp;</span>
<input type="button" class="button" value="提交文本" id="submit"/>
</label>

<label>
<span>分词结果 :</span>
<textarea id="result"></textarea>
</label>
</form>
  </body>
</html>
