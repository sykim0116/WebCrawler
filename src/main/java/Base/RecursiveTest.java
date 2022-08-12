package Base;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class RecursiveTest {
    public static boolean whiteFilter = true;
    public static boolean blackFilter = false;
    public static int setErrorCount;
    public static int errorCount;
    public static Elements hrefs;
    public static String href;
    public static String respCode_str;
    public static ArrayList<String> hrefList_all = new ArrayList<>();
    public static ArrayList<String> checkList = new ArrayList<>();
    public static ArrayList<String> error_urlList = new ArrayList<>();
    public static ArrayList<String> error_code = new ArrayList<>();
    public static ArrayList<String> error_summary = new ArrayList<>();
    public static ArrayList<String> sub_urlList = new ArrayList<>();
    public static ArrayList<String> hrefList = new ArrayList<>();
    public static ArrayList<String> set_urlList = new ArrayList<>
            (Arrays.asList(
                    "https://store.neosmartpen.com",
                    "https://neolab.kr",
                    "https://neolab.net",
                    "https://neolab.co.jp",
                    "https://neosmartpen.com",
                    "https://neosmartpen.co.kr",
                    "https://neosmartpen.jp",
                    "https://neosmartpen.de",
                    "https://neosmartpen.io",
                    "https://neosmartpen.us"
            ));

    public static void set_List(int x) throws IOException {
//        try {
            for (int k = x; k < set_urlList.size(); k++) {
                setErrorCount++;
                Connection connection = Jsoup.connect(set_urlList.get(k)).ignoreHttpErrors(true);
                Connection.Response setResponse = Jsoup.connect(set_urlList.get(k)).ignoreHttpErrors(true).timeout(5000).execute();
                System.out.println("* " + (k + 1) + "번째 링크 : " + set_urlList.get(k) + " : " + setResponse.statusCode());
                Document document = connection.get();
                hrefs = document.select("a[href]");

                for (Element element : hrefs) {
                    href = element.attr("abs:href");
                    if (href.contains("https://")) {
                        sub_urlList.add(href);
                    }
                }//href 태그 중 https://가 포함된 링크들만 리스트에 추가
                HashSet<String> hsetList = new HashSet<>();
                for (String sub_urlList_str : sub_urlList) {
                    hsetList.add(sub_urlList_str);
                }

                ArrayList<String> hsetStr = new ArrayList<>(hsetList);
                hrefList = (ArrayList<String>) hsetStr.clone();
                hrefList.remove("#");
                //href 리스트 내 중복 방지하기 위해 hashset으로 추가해준 후 arraylist로 변환, #태그는 삭제

//                checkList = (ArrayList<String>) hrefList.clone();
//
//                if (hrefs.isEmpty() == true) {
//                    hrefList.clear();
//                    System.out.println(" ㄴ 하이퍼링크 없음");
//                    //하이퍼링크 없는 url 체크
//                } else if (hrefs.isEmpty() == false && setResponse.statusCode() <= 400) {
//                    hrefList.clear();
////                    mergeList();
////                    responseCheck(0);
//                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                    //하이퍼링크가 1개라도 있을 때에는 체크리스트 만들어 준 다음에 페이지 응답 확인 메소드 호출
//                } else {
//                    hrefList.clear();
//                    if (!error_urlList.contains(set_urlList.get(k))) {
//                        error_urlList.add(set_urlList.get(k));
//                        respCode_str = Integer.toString(setResponse.statusCode());
////                        errorCodeList();
//                        //체크한 url의 응답 코드가 400 이상일 경우 > 에러 코드 확인 메소드 호출 후 에러 리스트에 추가
//                    }
//                }
//            }
//        } catch (Exception setException) {
//            if (!error_urlList.contains(set_urlList.get(setErrorCount - 1))) {
//                error_urlList.add(set_urlList.get(setErrorCount - 1));
//                error_summary.add("페이지 인증서 만료");
//                error_code.add(" ");
//            }
//            href_crawler(setErrorCount);
        }//체크한 url이 응답하지 않을 경우 (SSL 인증서 만료) > 에러 리스트에 바로 추가 후 다음 url 체크할 수 있도록 재귀 함수로 호출
    }

    public static void recursive_url(){
        try {
            set_List(0);
            ArrayList<String> rec_List = new ArrayList<>();
            for (String a : hrefList) {
                if (Filter.filtering()) {

                } else {

                }
            }
        }catch (Exception e){

        }
    }
}
