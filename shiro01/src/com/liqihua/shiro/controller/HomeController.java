package com.liqihua.shiro.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liqihua.shiro.bean.User;
import com.liqihua.shiro.service.UserService;


@Controller
@RequestMapping("HomeController")
public class HomeController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="userList")
	public String userList(){
		List<User> list = userService.list();
		for(User u : list){
			System.out.println("----- : "+u.toString());
		}
		return "forward:/index.jsp";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)  
    public String loginForm(HttpServletRequest request){
		System.out.println("---123");
        return "/login.jsp";  
    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(HttpServletRequest request,RedirectAttributes redirectAttributes){  
        try {  
        	String username = request.getParameter("username");
        	String password = request.getParameter("password");
        	System.out.println("username : "+username+" , password : "+password);
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！  
            SecurityUtils.getSubject().login(new UsernamePasswordToken(request.getParameter("username"), request.getParameter("password")));  
            return "redirect:/user.jsp";  
        } catch (AuthenticationException e) {
        	request.setAttribute("message", "用户名或密码错误");
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");  
            return "forward:/login.jsp";  
        }  
    }
	
	/*@RequestMapping(value="/logout",method=RequestMethod.GET)    
    public String logout(RedirectAttributes redirectAttributes ){   
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "您已安全退出");    
        return "redirect:/login.jsp";  
    }   
	
	@RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "/403.jsp";  
    } */
	
	
}
