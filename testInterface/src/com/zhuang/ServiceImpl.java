package com.zhuang;

import java.rmi.RemoteException;

public class ServiceImpl implements IService {

    private String name;
    public ServiceImpl(String name) throws RemoteException {
        this.name = name;
    }
    @Override
    public String service(String content) {
        return "server >> " + content;
    }
}
