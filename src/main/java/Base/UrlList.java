package Base;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class UrlList {
    public static int setErrorCount;
    public static int setUrlCount;
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
                    "https://neosmartpen.us",
                    "https://neolab.net/opensource_penmanager_windows/",
                    "https://neolab.net/opensource_penmanager_mac/"
            ));

    public static void href_crawler(int x){
        try {
            for (int k = x; k < set_urlList.size(); k++) {
                setErrorCount++;
                setUrlCount++;
                Connection connection = Jsoup.connect(set_urlList.get(k)).ignoreHttpErrors(true);
                Connection.Response setResponse = Jsoup.connect(set_urlList.get(k)).ignoreHttpErrors(true).timeout(5000).execute();
                System.out.println("* " + (k + 1) + "번째 링크 : " + set_urlList.get(k) + " : " + setResponse.statusCode());
                Document document = connection.get();
                hrefs = document.select("a[href]");
                if (hrefs.isEmpty() == true) {
                    hrefList.clear();
                    System.out.println(" ㄴ 하이퍼링크 없음");
                    //하이퍼링크 없는 url 체크
                } else if (hrefs.isEmpty() == false && setResponse.statusCode() <= 400) {
                    hrefList.clear();
                    mergeList();
                    responseCheck(0);
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    //하이퍼링크가 1개라도 있을 때에는 체크리스트 만들어 준 다음에 페이지 응답 확인 메소드 호출
                } else {
                    hrefList.clear();
                    if (!error_urlList.contains(set_urlList.get(k))) {
                        error_urlList.add(set_urlList.get(k));
                        respCode_str = Integer.toString(setResponse.statusCode());
                        errorCodeList();
                        //체크한 url의 응답 코드가 400 이상일 경우 > 에러 코드 확인 메소드 호출 후 에러 리스트에 추가
                    }
                }
            }
        } catch (Exception setException) {
            if (!error_urlList.contains(set_urlList.get(setErrorCount - 1))) {
                error_urlList.add(set_urlList.get(setErrorCount - 1));
                error_summary.add("페이지 인증서 만료");
                error_code.add(" ");
            }
            href_crawler(setErrorCount);
        }//체크한 url이 응답하지 않을 경우 (SSL 인증서 만료) > 에러 리스트에 바로 추가 후 다음 url 체크할 수 있도록 재귀 함수로 호출
    }

    public static void responseCheck(int x) {
        try {
            errorCount = 0;
            for (int i = x; i < checkList.size(); i++) {
                errorCount++;
                Connection.Response response = Jsoup.connect(checkList.get(i)).ignoreHttpErrors(true).timeout(5000).execute();
                System.out.println((i + 1) + " > " + checkList.get(i) + " : " + response.statusCode());
                //하이퍼링크 리스트 순서대로 호출
                if (response.statusCode() > 400) {
                    if (!error_urlList.contains(checkList.get(i))) {
                        error_urlList.add(checkList.get(i));
                        respCode_str = Integer.toString(response.statusCode());
                        errorCodeList();
                    }//하이퍼링크 호출 시 응답 코드가 400이 넘는 경우 응답 코드 체크 메소드 호출 후 에러 리스트에 추가
                }
            }
        } catch (Exception e) {
            if (!error_urlList.contains(checkList.get(errorCount - 1))) {
                error_urlList.add(checkList.get(errorCount - 1));
                error_summary.add("페이지 인증서 만료");
                error_code.add(" ");
            }
            responseCheck(errorCount);
        }//하이퍼링크 인증서 만료인 경우 에러 리스트에 추가 후 다음 리스트를 재귀 함수로 호출
    }

    public static void errorCodeList() {
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
        error_code.add(respCode_str);
    }//응답코드 400 넘었을 때 swtich문으로 체크해서 코드와 summary 추가

    public static void mergeList() {
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

        checkList = (ArrayList<String>) hrefList.clone();
        //hrefList는 url별로 나중에 정리하려고 checkList로 빼두고 처리

        if (hrefList_all.size() == 0) {
            for (int i = 0; i < hrefList.size(); i++) {
                hrefList_all.add(hrefList.get(i));
            }
            //첫번째 url 체크했을 때에는 중복 체크 안하고 바로 하이퍼링크 리스트 추가
        } else {
            for (int i = 0; i < hrefList_all.size(); i++) {
                if (checkList.contains(hrefList_all.get(i))) {
                    checkList.remove(hrefList_all.get(i));
                }
            }
            System.out.println("중복 제거 후 하이퍼링크 : " + checkList.size() + "개");
            //두번째 url부터는 중복 체크해서 리스트 반영되도록

//            for (int i = 0; i < hrefList.size(); i++) {
//                if (error_urlList.contains(hrefList.get(i))) {
//                    bb.add(set_urlList.get(setUrlCount));
//                }
//            }
//
//            HashSet<String> hsetErrorList = new HashSet<>();
//            for (String set_ErrorUrlList_str : bb) {
//                hsetErrorList.add(set_ErrorUrlList_str);
//            }
//            ArrayList<String> hsetErrorStr = new ArrayList<>(hsetErrorList);
//            error_setUrlList = (ArrayList<String>) hsetErrorStr.clone();


            for (int j = 0; j < checkList.size(); j++) {
                if (!hrefList_all.contains(checkList.get(j))) {
                    hrefList_all.add(checkList.get(j));
                }
                //중복 제외한 전체 하이퍼링크 리스트(참고용)
            }
        }
    }
}
