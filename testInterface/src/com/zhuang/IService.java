package com.zhuang;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IService extends Remote {
    String service(String content) throws RemoteException;
}
