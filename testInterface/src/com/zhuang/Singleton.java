package com.zhuang;

//饥饿式单例模式
public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}

//懒汉式单例模式(线程安全)
class Singleton1{

    private static Singleton1 instance1;

    private Singleton1(){}

    public synchronized static Singleton1 getInstance1(){

        if (instance1 == null){
            return instance1 = new Singleton1();
        }
        return  instance1;
    }
}
class ss {
    public static void main(String[] args) {
        String s = "001";
        System.out.println(getNewString(s));
        System.out.println("38".compareTo("226"));
    }
    public static String getNewString(String s){
        while (s.length() > 1 && s.charAt(0) == '0'){
            s = s.substring(1);
        }
        return s;
    }
}