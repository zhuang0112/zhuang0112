package com.zhuang;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public interface TestInterface {
    default void add(){};
    public static void add1(){};
    public abstract  void add2();
    final int i = 0;
    static int j =0;
    static final int m = 0;
}
class T {

    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
    }
    
}
