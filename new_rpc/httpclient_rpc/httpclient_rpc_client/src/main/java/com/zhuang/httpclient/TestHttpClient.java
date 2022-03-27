package com.zhuang.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class TestHttpClient {
    public static void main(String[] args) throws IOException, URISyntaxException {
        testGetNoParams();
        testGetParams();

    }
    /**
    * 无参数Get请求
    *使用浏览器，访问网站的过程：
     * 1.打开浏览器
     * 2.输入网址
     * 3.访问
     * 4，看结果
     * 使用HttpClient,访问web服务的过程
     * 1.创建客户端，相当于打开浏览器
     * 2.创建请求地址，相当于输入网址
     * 3.发起请求，相当于访问
     * 4。处理响应结果，相当于浏览器响应结果
     * */
    public static  void testGetNoParams() throws IOException {
        //创建客户端对象
        HttpClient client = HttpClients.createDefault();
        //创建请求地址
        HttpGet get = new HttpGet("http://localhost:90/test");
        //发起请求
        HttpResponse reponse =  client.execute(get);
        //获得响应,响应数据是一个基于Http协议标准字符串封装的对象
        //所以响应体和响应头，都是封装Http协议数据。直接使用可能有乱码获解析错误
        HttpEntity entity = reponse.getEntity();

        //转换响应数据
        String responseString = EntityUtils.toString(entity);

        System.out.println("服务器响应数据是 - [" + responseString+ "]");

        //回收资源
        client = null;
    }

    public static  void testGetParams() throws IOException, URISyntaxException {
        //创建客户端对象
        HttpClient client = HttpClients.createDefault();
        //创建请求地址
        URIBuilder builder = new URIBuilder("http://localhost:90/params");
//        //基于单参数传递
//        builder.addParameter("name","zhuangrui");
//        builder.addParameter("password","123456");
//        URI uri = builder.build();
        //基于多参数请求，构建地址
        ArrayList<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("name","zhuangrui"));
        list.add(new BasicNameValuePair("password","1234"));
        builder.addParameters(list);
        URI uri = builder.build();
        System.out.println(uri.toASCIIString());

        //发起请求
        HttpResponse reponse =  client.execute(new HttpGet(uri));
        //获得响应,响应数据是一个基于Http协议标准字符串封装的对象
        //所以响应体和响应头，都是封装Http协议数据。直接使用可能有乱码获解析错误
        HttpEntity entity = reponse.getEntity();

        //转换响应数据
        String responseString = EntityUtils.toString(entity);

        System.out.println("服务器响应数据是 - [" + responseString+ "]");

        //回收资源
        client = null;
    }
}
