package com.zhuang.httpclientserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zhuang.httpclientpojo.User;

import java.util.List;

@Controller
public class TestController {
    //使用请求体传递参数
    @RequestMapping(value = "/bodyParams",produces = {"application/jason;charset=UTF-8"})
    @ResponseBody
    public String bodyParams(@RequestBody List<User> users) {

        System.out.println(users);
        return users.toString();
    }

    @RequestMapping(value = "/params",produces = {"application/jason;charset=UTF-8"})
    @ResponseBody
    public String params(String name, String password){
        System.out.println("name - " + name + " ; password - " + password);
        return "{\"msg\":\"登录成功\", \"user\":{\"name\":\""+ name +"\"password\" :\""+password +"\"}}";
    }

    @RequestMapping(value = "/test",produces = {"application/jason;charset=UTF-8"})
    @ResponseBody
    public String test(){
        return "{\"msg\":\"处理返回\"}";
    }
}
