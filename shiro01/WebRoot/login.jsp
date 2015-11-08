<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<html>  
  <head>  
  </head>  
    
  <body>  
    <%-- <h1>登录页面----${message }</h1>  
    <img alt="" src="/static/img/1.jpg">  
    <form:form action="/login" commandName="user" method="post">  
          用户名：
    	<form:input path="username"/>
    	<form:errors path="user.username" cssClass="error"/><br/>  
          密 &nbsp;&nbsp;码：
        <form:password path="password"/>
    	<form:errors path="user.password" cssClass="error" /> <br/>  
    	<form:button name="button">submit</form:button>
    </form:form>  --%> 
    
    <h1>登录页面----${message }</h1>
    <form action="${pageContext.request.contextPath }/HomeController/login" method="post">
    	<input type="text" name="username"/><br>
    	<input type="password" name="password"/><br>
    	<input type="submit" value="提交"/>
    </form>
    
  </body>  
</html>
