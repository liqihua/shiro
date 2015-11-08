<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>用户列表</title>  
  </head>  
  <body>  
    <h1>${message }</h1>  
    <h1>用户列表--<a href="/user/add">添加用户</a>---<a href="/logout">退出登录</a>    </h1>  
    <h2>权限列表</h2>  
    <shiro:authenticated>用户已经登录显示此内容</shiro:authenticated><br>
    <shiro:hasRole name="manager">manager角色登录显示此内容</shiro:hasRole><br>
    <shiro:hasRole name="admin">admin角色登录显示此内容</shiro:hasRole><br>
    <shiro:hasRole name="normal">normal角色登录显示此内容</shiro:hasRole><br>
      
    <shiro:hasAnyRoles name="manager,admin">manager , admin 角色用户登录显示此内容</shiro:hasAnyRoles><br>
    <br>
    <shiro:principal/>-----显示当前登录用户名  
    <br>
    <shiro:hasPermission name="add">add权限用户显示此内容</shiro:hasPermission><br>
    <shiro:hasPermission name="query">query权限用户显示此内容</shiro:hasPermission><br>
    <shiro:hasPermission name="del">del权限用户显示此内容</shiro:hasPermission><br>
    <shiro:hasPermission name="update">update权限用户显示此内容</shiro:hasPermission><br>
    <shiro:lacksPermission name="update"> 不具有update权限的用户显示此内容 </shiro:lacksPermission><br>
    
    
    
    <br><hr>
    <h6>分别点击下列4个链接</h6>
    <a href="${pageContext.request.contextPath }/add/index.jsp">add</a><br>
    <a href="${pageContext.request.contextPath }/del/index.jsp">del</a><br>
    <a href="${pageContext.request.contextPath }/update/index.jsp">update</a><br>
    <a href="${pageContext.request.contextPath }/query/index.jsp">query</a><br>
    <br><hr>
    <font>发现，query、del能跳转；update、add不能跳转。是因为applicationContext.xml中配置如下：</font>
    <br>
    <img src="${pageContext.request.contextPath }/images/1.png"/>
    
    
    
    
    
    
    <ul>  
        <c:forEach items="${userList }" var="user">  
            <li>用户名：${user.username }----密码：${user.password }----<a href="/user/edit/${user.id}">修改用户</a>----<a href="javascript:;" class="del" ref="${user.id }">删除用户</a></li>  
        </c:forEach>  
    </ul>  
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.9.1/jquery.min.js"></script>  
    <script>  
        $(function(){  
            $(".del").click(function(){  
                var id=$(this).attr("ref");  
                $.ajax({  
                    type:"delete",  
                    url:"/user/del/"+id,  
                    success:function(e){  
                          
                    }  
                });  
            });  
        });  
    </script>  
  </body>  
</html>