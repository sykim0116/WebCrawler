package Main;

import Base.*;

import static Base.ChromeDriver.aaa;
import static Base.RecursiveTest.error_urlList;
import static Base.UrlList.*;
import static Base.RecursiveTest.*;

public class Main {
    public static void main(String[] args){
        try {
            ChromeDriver.run(aaa, 0);
//            RecursiveTest.recursive_url(seed_urlList, 0);
//            RecursiveTest.recursive_url();
//            UrlList.href_crawler(0);
//            if (error_urlList.size() > 0) {
//                HtmlJavaSend.sendEmail();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
