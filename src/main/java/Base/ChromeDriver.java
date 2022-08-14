package Base;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class ChromeDriver {
    public static int count;
    public static int urlCount;
    public static Elements hrefs;
    public static String href;
    public static String respCode_str;
    public static ArrayList<String> visitedList = new ArrayList<>();
    public static ArrayList<String> error_urlList = new ArrayList<>();
    public static ArrayList<String> error_code = new ArrayList<>();
    public static ArrayList<String> error_summary = new ArrayList<>();
    public static ArrayList<String> sub_urlList = new ArrayList<>();
    public static ArrayList<String> hrefList = new ArrayList<>();
    public static ArrayList<String> aaa = new ArrayList<>
            (Arrays.asList(
                    "https://google.com/"
            ));

    public static void run(ArrayList<String> input, int x) {
        ArrayList<String> lll = new ArrayList<>(input);
        try {
            System.out.println("---------------------------------------------------------level : " + x + "---------------------------------------------------------");
            for (String in : lll) {
                count++;
                Connection connection = Jsoup.connect(in).ignoreHttpErrors(true);
                Connection.Response setResponse = Jsoup.connect(in).ignoreHttpErrors(true).timeout(5000).execute();
                System.out.println(count + " >>>>>>>>>>>>>>>>> " + in + " : " + setResponse.statusCode());
                Document document = connection.get();
                hrefs = document.select("button");

                for (Element element : hrefs) {
                    System.out.println(element);
                    }
                }//href 태그 중 https://가 포함된 링크들만 리스트에 추가
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
