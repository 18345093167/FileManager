package com.example.uploadingfiles;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/toLogin")
    public String toLogin(@PathParam(value = "username") String username,
            @PathParam(value = "password") String password,
            Map<String, Object> map, HttpSession session) {
        
        if(!StringUtils.isEmpty(username)&& username.equals("burning")&&"123456".equals(password)){
            session.setAttribute("loginUser", username);
            return "redirect:/";
        } else {
            map.put("msg", "用户名或密码错误");
            return "index";
        }
    }
    @GetMapping(value="/login")
    public String login(Model model) {
        model.addAttribute("msg", "登陆后可上传私密文件");
        return "index";
    }
    
}