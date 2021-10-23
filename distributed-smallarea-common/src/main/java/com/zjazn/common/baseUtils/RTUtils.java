package com.zjazn.common.baseUtils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class RTUtils {


    private String url;
    private HttpHeaders httpHeaders;
    private String httpBody;
    private Map<String,String> cookie;
    public RTUtils(String url, String httpBody, HttpHeaders httpHeaders,Map<String,String>  cookie) {
        this.url = url;
        this.httpHeaders = httpHeaders;
        this.httpBody = httpBody;
        this.cookie = cookie;

    }
    //no cookie
    public RTUtils(String url, String httpBody, HttpHeaders httpHeaders) {
        this(url,httpBody,httpHeaders,null);
    }
    //no head cookie
    public RTUtils(String url, String httpBody) {
        this(url,httpBody, new HttpHeaders() ,null);
    }
    //no body head head
    public RTUtils(String url) {
        this(url,"",new HttpHeaders(),null);
    }


    public  String post() {
        //        HttpHeaders httpHeaders = new HttpHeaders();
        //        httpHeaders.add("Content-Type","application/json;charset=UTF-8");
        //        httpHeaders.add("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjE5MDYwMjAxMTExIiwiTmFtZSI6IuW6hOadsCIsIlR5cGUiOiIwIiwiUm9sZSI6IiIsIlJvbGVOYW1lIjoiIiwiQ29kZSI6IiIsIm5iZiI6MTYyNTM2OTk5MSwiZXhwIjoxNjI1Mzc3MTkxLCJpc3MiOiJkYXZpZCIsImF1ZCI6IkF1ZGllbmNlIn0.Z_jH__lW5TVselx5AXPRRmr7MsHTO2kTWIG9CMdIKyw");
        //        String body = new RTUtils("https://aic.gdkmxy.cn/yqtj/api/v1/QuestionnaireResult?isAbnormal=false", new JsonTemplate("19060201"+i,"广东省清远市清城区环城东路|[23.748886,113.084724]").gen(), httpHeaders,null).post();


        //如果传入了head且cookie也传入,就会把cookie加入head中
        if(this.httpHeaders != null && this.cookie != null) {
            this.httpHeaders.add("Cookie",RTUtils.CookieListToString(this.cookie));
        }

        //将body与head加入请求容器中，发送请求
        HttpEntity<String> requestEntity = new HttpEntity<String>(this.httpBody, this.httpHeaders);
        ResponseEntity<String> result = new RestTemplate().postForEntity(this.url, requestEntity,String.class);
        //获取请求体
        return result.getBody();

    }



    public String get() {
        //https://aic.gdkmxy.cn/yqtj/api/v1/SSO?type=0&code=19060201111&sign=7ead479de9cf7c7840795747bbc5085d&timestamp=871971
        ResponseEntity<String> result = new RestTemplate().getForEntity(this.url,String.class);
        return result.getBody();
    }


    public static String  CookieListToString(Map<String,String> map) {
        String result = "";
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            String key_i = (String) iterator.next();
            String val_i = map.get(key_i);
            if(iterator.hasNext()) {
                result+=(key_i+"="+val_i+";");
            }else  {
                result+=(key_i+"="+val_i);
            }

        }

        return result;
    }

}
