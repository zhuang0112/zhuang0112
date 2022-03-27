package com.zhuang;

import javax.naming.Context;
import javax.naming.InitialContext;

public class Server {
    public static void main(String[] args) {
        try {
            IService service02 = new ServiceImpl("service02");
            Context namingContext = new InitialContext();
            namingContext.rebind("rmi://127.0.0.1/service02", service02);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("000000!"); }
}
