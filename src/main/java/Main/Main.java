package Main;

import Base.*;
import static Base.UrlList.*;

public class Main {
    public static void main(String[] args){
        try {
            UrlList.href_crawler(0);
            if (error_urlList.size() > 0) {
                HtmlJavaSend.sendEmail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
