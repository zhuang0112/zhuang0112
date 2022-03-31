package com.zhuang.httpclient;


import com.sun.media.sound.JARSoundbankReader;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zhuang.httpclientpojo.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
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
        //testGetNoParams();
        //testGetParams();
        //testPost();
        testPostParams();

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
        //创建请求地址，默认端口号是80
        HttpGet get = new HttpGet("http://10.211.55.4:90/test");
        //发起请求
        HttpResponse reponse =  client.execute(get);
        //获得响应,响应数据是一个基于Http协议标准字符串封装的对象
        //所以响应体和响应头，都是封装Http协议数据。直接使用可能有乱码获解析错误
        HttpEntity entity = reponse.getEntity();

        //转换响应数据
        String responseString = EntityUtils.toString(entity);

        System.out.println("服务器响应数据是 " + responseString);

        //回收资源
        client = null;
    }

    public static  void testGetParams() throws IOException, URISyntaxException {
        //创建客户端对象
        HttpClient client = HttpClients.createDefault();
        //创建请求地址
        URIBuilder builder = new URIBuilder("http://10.211.55.4:90/params");
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
        //System.out.println(uri.toASCIIString());

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
    /*
    * 测试POST请求
    * */
    public static void testPost() throws IOException, URISyntaxException {

        //创建客户端
        CloseableHttpClient client = HttpClients.createDefault();
        //创建无参POST请求地址
        HttpPost post = new HttpPost("http://localhost:90/test");
        CloseableHttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String toString = EntityUtils.toString(entity);
        System.out.println("服务器响应数据是："+ toString);

    }

    public static void testPostParams() throws URISyntaxException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //创建有参POST请求
        //请求头传递参数和GET请求带参数的方式一致
       /* URIBuilder builder = new URIBuilder("http://localhost:90/params");
        builder.addParameter("name","张三");
        builder.addParameter("password","1234");
        URI uri = builder.build();
        System.out.println(uri);
        String toString1 = EntityUtils.toString(client.execute(new HttpPost(uri)).getEntity(),"UTF-8");
        System.out.println("有参数POST请求服务器：" + toString1);*/

        //请求体传递参数
        HttpPost bodyParampost = new HttpPost("http://localhost:90/bodyParams");
        //定义请求协议体，设置请求参数。使用请求体传递参数的时候，需要定义请求体格式，默认是表单格式。
        //使用URIBuilder构建的URI对象，就是请求体传递参数
        User user1 = new User();
        user1.setName("name1");
        user1.setPassword("123");
        User user2 = new User();
        user2.setName("name2");
        user2.setPassword("123123");
        //拼接JASON字符串表达式
        String paramsString = "[" + user1.toString() + "," + user2.toString() + "]";
        HttpEntity entity = new StringEntity(paramsString,"application/json","UTF-8");
        bodyParampost.setEntity(entity);
        CloseableHttpResponse response = client.execute(bodyParampost);
        HttpEntity entity1 = response.getEntity();
        String toString = EntityUtils.toString(entity1,"UTF-8");
        System.out.println("请求体传参，服务器响应：" + toString);

    }
}
