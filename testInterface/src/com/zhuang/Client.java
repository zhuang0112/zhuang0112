package com.zhuang;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {
    public static void main(String[] args) throws NamingException {
        String url = "rmi://127.0.0.1/";
        try {
            Context namingContext = new InitialContext();
            IService service02 = (IService) namingContext.lookup(url +
                    "service02");
            Class stubClass = service02.getClass();
            System.out.println(service02 + " is " + stubClass.getName());
            //com.sun.proxy.$Proxy0
            Class[] interfaces = stubClass.getInterfaces();
            for (Class c : interfaces) {
                System.out.println("implement" + c.getName() + " interface");
            }
            System.out.println(service02.service("hello"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

