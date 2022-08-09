package Main;

import Base.*;

import java.io.IOException;

import static Base.UrlList.*;

import static Base.CheckURL.*;
import static Base.UrlList.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            UrlList.href_crawler(0);
//            UrlList.run();
//            UrlList.mergeList();
//            CheckURL.urlCheck_run();
            if (error_urlList.size() > 0) {
                HtmlJavaSend.sendEmail();
            } else {
            }
        } catch (Exception e) {
//            connect_exception();
//            System.out.println(status);
            e.printStackTrace();
        } finally {
//            driver.close();
        }
    }
}
