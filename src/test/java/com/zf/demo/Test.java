package com.zf.demo;


import com.zf.demo.addressmanage.service.impl.AddressInfoServiceImpl;
import com.zf.demo.addressmanage.oradao.AddressInfoDao2;
import com.zf.demo.addressmanage.oradao.entity.AddressInfoEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Create by zengfei
 * Date 2019/12/19 15:19
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void aa(){
        String[] str={"7b9a5a41",      "d65a1c37",     "a6f137e6",     "8c45375e",     "a299e11e",     "6263f29d",
        "7323d72d",      "33a19c74",     "78d0fa71",    "2b0461c5" ,  "1c50c907" ,  "cd417323" ,  "e81c78d0","199eae81c","eebe38c4",
                "6dcc1a1e","dfb0c33a" ,   "7d6eebe3",
        "2761c2ad",   "480ab12c", "e23b3790",   "2a92761c",   "f482b046",    "e2b2a5a4",
        "acf05b5f",     "5b5f6263",    "d0fdcd41",    "cbe72dbf",    "72dbf7b9",    "665dfb0c",
        "9c74a299",   "51eafa71",    "5fe73afe",     "2adf137e",   "a20fdd72",    "cf0f2912"};
        for (int i=0;i<str.length;i++){
            for (int j=0;j<str.length;j++){
                if(str[i]!=str[j]){

                System.out.println(str[i]+str[j]);
                }
            }
        }

    }

    @org.junit.jupiter.api.Test
    public void cc(){

        String str="aaa";
        HttpURLConnection connection=null;
        URL url=null;
        DataOutputStream out = null;
        BufferedReader reader=null;
        String searchResult = "{}";
        try {
            url=new URL("http://localhost:9200/mydb4/");
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");
//            connection.setUseCaches(false);
////            connection.setInstanceFollowRedirects(true);
////            connection.setRequestProperty("Accept","*/*");
////            connection.setRequestProperty("Content-Type","application/json");
////            connection.setRequestProperty("Charset","UTF-8");
            connection.connect();
//            out = new DataOutputStream(connection.getOutputStream());
//            out.write(str.getBytes("utf-8"));
//            out.flush();
            if(connection.getResponseCode()>=400){
                System.out.println(connection.getResponseMessage());
            }else{

                reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
                String lines=null;
                StringBuffer buffer=new StringBuffer();
                while (null!=(lines=reader.readLine())){
                    lines=new String(lines.getBytes());
                    buffer.append(lines);
                }
                reader.close();
                connection.disconnect();
                System.out.println(buffer.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @org.junit.jupiter.api.Test
public void testFile(){
    PathUtil pathUtil = new PathUtil();
    pathUtil.getClassPath(Day.class);
}
@org.junit.jupiter.api.Test
    public void bb(){
        String str = " 1、b7380cac    2、772f0182    3、3988a6b    4、 9a9f7503      5、 cf067f09 " +
                "6、a20230b7    7、431809dc    8、612a9f5a   10、464dae01 " +
                " 12、d332a96c  13、d640c96c  14、00113a2c  15、a1cf1722e " +
                "16、e8072de6  17、b1b72c46 19、97fd488f  20、e61941b7 " +
                "21、0418883e  22、4e5d439b  23、b175bbad  24、4a54c3ba  25、0cac8014 " +
                "26、992a3910  27、b7c26cd6  28、f14d9421   30、784921c8 " +
                "31、cc8af1ea  32、256e8072  33、b7380cac  34、3714c0a9  35、7baded64 " +
                "36、7033402b  37、c8b858a6  39、874d0867 40、67992e1f " +
                "41、57a227cf  42、58514a3e  43、e128b544  44、9b34c166 45、edafc813 " +
                "46、016fa81b  47、2bf1eebc  48、dc717db2  49、14a3ed6d  50、46714d6e " +
                "51、66f517c6   53、84d2c700  54、f1230737  55、6ea1a98e " +
                "56、b49fedb2  57、a2b05d99  58、d322be0a  59、b7f6c446 60、c60d6b38 " +
                "61、e4757877  62、700661cd  63、3b9df799  64、4e5d439b  65、b5449a88b " +
                "66、960c00cd  68、952d92cd  69、cd9bfd3b 70、3ad6739a " +
                "71、589461f6  72、f61e77d2  73、cd2ed532  74、b450c360  75、3ede8ba1 " +
                "76、6cf5bbdd   78、5f93263d  79、1dfa9aca  80、8e330e1b " +
                "81、f46f1bdc  82、2e06ed9  83、030903ed 84、a9532633  85、742968e7 " +
                "  87、609e451e 88、f1230737  89、3714c0a9  90、bec2e47d " +
                "91、95b49a61  92、ff9cfbf2  93、bf28a8a9  94、9fef3b77  95、7a822ea9 " +
                "96、e7d9d6c7  97、61469fee  98、70627f8b  99、9b34c166  100、ec346c70 ";
        for(int i=1;i<=100;i++){
            if(str.contains(" "+i+"、")){
                if(i!=1){
                  str=str.replace(" "+i+"、", "、");

                }else{
                    str=str.substring(str.indexOf("、")+1);
                }
            }
        }
        System.out.println(str);
        String[] strArr=str.replaceAll(" ","").split("、");
        for(int i=0;i<strArr.length;i++){
//            System.out.println(i+":"+strArr[i]);
            for(int j=0;j<strArr.length;j++){
                if(strArr[i]!=strArr[j]){

                    System.out.println(strArr[i]+strArr[j]);
                }
            }
        }
    try {
        URL url=new URL("http://www.baidu.com");
        URLConnection connection=url.openConnection();

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }


}
    @org.junit.jupiter.api.Test
    public void testPost(){
        String url="http://localhost:8080/demo/generator/testPost";
        Map<String,String> map=new HashMap<>();
        map.put("userName","zf");
        map.put("userPass","zf123");
        String bodyData="这是报文体";
        String str=sendPost(url,map,bodyData);
        System.out.println(str);
    }
    public static String sendPost(String url, Map<String,String> headers, String bodyData){
        String res=null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        StringEntity stringEntity = new StringEntity(bodyData, ContentType.create("application/json","UTF-8"));
        httpPost.setEntity(stringEntity);

        Iterator iterator = headers.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry=(Map.Entry<String,String>)iterator.next();
            httpPost.addHeader(entry.getKey(),entry.getValue());
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);//发送post请求
            res=EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            httpClient.close();
            httpResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }



}
