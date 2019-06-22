package com.gzcc.kindmanager.Controller;

import com.gzcc.kindmanager.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import java.util.Map;

@Controller
public class UserController {

@Autowired
    private LoginService loginService;
@RequestMapping(value="/login")
    @ResponseBody
    public Map login(HttpServlet request, String openId){
    Map map=loginService.login(openId);
    return  map;
}
    @RequestMapping(value="/regist")
    @ResponseBody
    public Map regist(String iv, String encryptedData, String code) {
        Map map = loginService.regist(iv, encryptedData, code);
        return map;
    }
}
