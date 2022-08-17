package Main;

import Base.*;

import static Base.RecursiveTest.*;

public class Main {
    public static void main(String[] args){
        try {
//            RecursiveTest.setBrowser();
            RecursiveTest.recursive_url(seed_urlList, 0);
//            if (error_urlList.size() > 0) {
//                HtmlJavaSend.sendEmail();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
