package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static Base.UrlList.*;

public class CheckURL {
    private WebDriver element;
    public static WebDriver driver;
    static HttpURLConnection huc = null;
    public static int respCode = 0;
    public static int urlCount;
    public static int errorIndex;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "/Users/sykim/Desktop/chromedriver";

    public static void setBrowser() {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(hrefList.get(0));
        driver.manage().window().maximize();
    }
    //url 리스트의 0번째 인덱스 url부터 시작

    public static void url_check(int x) throws IOException {
        try {
            for (int i = x; i < hrefList.size(); i++) {

                huc = (HttpURLConnection) (new URL(hrefList.get(i)).openConnection());
                huc.connect();
                respCode = huc.getResponseCode();
                //url 연결 후 응답코드를 받는 부분

                error_check();
                //응답 코드 따라서 에러인지 아닌지 체크하는 메소드 실행

                if (i < (hrefList.size() - 1)) {
                    driver.navigate().to(hrefList.get(i + 1));
                    //url 리스트에서 다음 인덱스의 url 페이지로 이동
                    errorIndex++;
                    //에러 체크 인덱스에 쓸라고 넣었음
                    urlCount++;
                } else {
                }
                //마지막 인덱스까지만 돌도록 조건문 > 안그러면 갖고 있는 인덱스보다 체크할게 많아짐 는 오류남
            }
        } catch (Exception e2) {
            connect_exception();
        }
        //인증서 만료 > 예외 상황으로 빠지기 때문에 인증서 만료 체크하는 메소드 실행해줌
    }
    //url 체크하는 메소드

    public static void connect_exception() throws IOException {
        if (errorIndex <= hrefList.size()) {
            try {
//                if(driver.findElement(By.id("error-code")).isDisplayed()){
                System.out.println("페이지 인증서 만료 : " + hrefList.get(errorIndex));
                error_urlList.add(hrefList.get(errorIndex));
//                error_urlList.add(driver.getCurrentUrl());
                error_summary.add("페이지 인증서 만료");
                error_code.add(" ");
                //에러 url, 코드, 요약을 각각의 리스트에 넣어줌, 인증서 만료 페이지는 id로 코드 체크해서 넣었음
                errorIndex++;
                driver.navigate().to(hrefList.get(errorIndex));
                url_check(errorIndex);
                //에러 인덱스 다음 번호부터 체크할 수 있도록 함
            } catch (Exception e1) {
                url_check(errorIndex + 1);
                //이때 예외처리 다시 됐을 때는 다음 인덱스로 넘어가서 체크할 수 있도록(안그러면 url 연결 메소드(huc.connect()) 자체가 실행이 안돼서 죽어요)
            }
        }
    }
    //인증서 문제 있을 경우에 예외처리로 감 > 이때 인증서 만료로 체크하도록 하는 부분

    public static void error_check() {
        if (respCode >= 400) {
            //응답코드 400이 넘었을 때 조건문 실행
            String respCode_str = Integer.toString(respCode);
            error_urlList.add(driver.getCurrentUrl());
            //에러 url 리스트에 넣어줌 > 이게 나중에 메일로 들어가는 오류 페이지 리스트
            switch (respCode_str) {
                case "400":
                    error_summary.add("Bad Request");
                    break;
                case "401":
                    error_summary.add("Unauthorized");
                    break;
                case "403":
                    error_summary.add("Forbidden");
                    break;
                case "404":
                    error_summary.add("Not Found");
                    break;
                case "405":
                    error_summary.add("Method Not Allowed");
                    break;
                case "406":
                    error_summary.add("No Acceptable");
                    break;
                case "408":
                    error_summary.add("Request Timeout");
                    break;
                case "409":
                    error_summary.add("Conflict");
                    break;
                case "412":
                    error_summary.add("Precondition on Failed");
                    break;
                case "413":
                    error_summary.add("Payload Too Large");
                    break;
                case "429":
                    error_summary.add("Too many Request");
                    break;
                case "500":
                    error_summary.add("Internal Server Error");
                    break;
                case "501":
                    error_summary.add("Not Implemented");
                    break;
                case "502":
                    error_summary.add("Bad Gateway");
                    break;
                case "503":
                    error_summary.add("Service Unavailable");
                    break;
                case "504":
                    error_summary.add("Gateway Timeout");
                    break;
            }
            //각각 응답코드에 맞게 스위치문으로 조건 걸어주고 오류 요약 리스트 넣어줌
            error_code.add(respCode_str);
            //에러 코드도 넣어줘요
            System.out.println("HTML 에러 발생 : " + driver.getCurrentUrl() + " > 에러 코드 : " + respCode);
        } else {
            System.out.println("페이지 정상 진입 : " + driver.getCurrentUrl() + " > 응답 코드 : " + respCode);
        }
    }

    public static void urlCheck_run() throws IOException {
        setBrowser();
        try {
            url_check(0);
            //0번 인덱스부터 url 체크
        } catch (Exception e) {
            connect_exception();
            //예외처리 되었을 때 url 체크
        }
    }

}
