package com.example.uploadingfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.uploadingfiles.LoginService.ILoginService;
import com.example.uploadingfiles.data.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/toLogin", method = RequestMethod.POST)
    public String toLogin(@RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password, Map<String, Object> map, HttpSession session) {
        if (!loginService.checkUser(username)){
            map.put("msg", "当前用户不存在,请您先注册");
            return "index";
        }else{
            if (loginService.checkUserAndPasswd(username,password)){
                session.setAttribute("loginUser", username);
                String loginTime = null;
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                loginTime = formatter.format( new Date(System.currentTimeMillis()));
                //只有登录时候的状态是1 登出或注册都是0
                boolean isLogin = true;
                UserEntity user = new UserEntity();
                user.setLoginIp(request.getRemoteAddr());
                user.setIsLogin(isLogin);
                user.setLoginTime(loginTime);
                user.setUserName(username);
                loginService.update(user);
                return "test";
            } else {
                map.put("msg", "用户名或密码错误");
                return "index";
            }
        }
    }

    @RequestMapping(value = "/toRegister", method = RequestMethod.POST)
    public String toRegister(HttpServletRequest request,RedirectAttributes redirectAttributes) {
        String email = request.getParameter("email");
        String userName = request.getParameter("username");
        String passWd = request.getParameter("password2");
        String ip = request.getRemoteAddr();
        String userId  = UUID.randomUUID().toString().replace("-","");
        Date loginOutTime = null;
       /* 
        //System.out.println(formatter.format(date));
        System.out.println("用户是"+email+userName+passWd+ip+userId+loginOutTime);*/
        String loginTime = null;
        boolean isLogin = false;
        UserEntity user = new UserEntity();
        user.setLoginIp(ip);
        user.setLoginOutTime(loginOutTime);
        user.setUserId(userId);
        user.setUserEmail(email);
        user.setLoginTime(loginTime);
        user.setUserName(userName);
        user.setUserPasswd(passWd);
        user.setIsLogin(isLogin);
        loginService.add(user);
        return "redirect:/login";

    }
    @GetMapping(value="/login")
    public String login(Model model) {
        model.addAttribute("msg", "登陆后可上传私密文件");
        return "index";
    }
    @GetMapping(value="/register")
    public String register(Model model) {
        model.addAttribute("msg", "注册后享专属文件系统");
        return "register";
    }
    
}