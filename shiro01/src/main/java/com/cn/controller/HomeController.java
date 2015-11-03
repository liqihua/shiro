package com.cn.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cn.pojo.User;

@Controller
@RequestMapping("HomeController")
public class HomeController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET)  
    public String loginForm(Model model){
		System.out.println("--123");
        model.addAttribute("user", new User());  
        return "/login";  
    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)  
    public String login(@Valid User user,BindingResult bindingResult,RedirectAttributes redirectAttributes){  
        try {  
            if(bindingResult.hasErrors()){  
                return "/login";  
            }  
            //ʹ��Ȩ�޹��߽����û���¼����¼�ɹ�������shiro���õ�successUrl�У��������returnûʲô��ϵ��  
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));  
            return "redirect:/user";  
        } catch (AuthenticationException e) {  
            redirectAttributes.addFlashAttribute("message","�û������������");  
            return "redirect:/login";  
        }  
    }
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)    
    public String logout(RedirectAttributes redirectAttributes ){   
        //ʹ��Ȩ�޹����߽����û����˳���������¼��������ʾ��Ϣ  
        SecurityUtils.getSubject().logout();    
        redirectAttributes.addFlashAttribute("message", "���Ѱ�ȫ�˳�");    
        return "redirect:/login";  
    }   
	
	@RequestMapping("/403")  
    public String unauthorizedRole(){  
        return "/403";  
    }  
	
	
	
}
