package Base;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

import static Base.ReponseCheck.*;

public class RecursiveTest {
    public static int count;
    public static int urlCount;
    public static Elements hrefs;
    public static String href;
    public static String respCode_str;
    public static ArrayList<String> visitedList = new ArrayList<>();
    public static List<String> filteringList = new ArrayList<>();
    public static ArrayList<String> error_urlList = new ArrayList<>();
    public static ArrayList<String> error_code = new ArrayList<>();
    public static ArrayList<String> error_summary = new ArrayList<>();
    public static ArrayList<String> sub_urlList = new ArrayList<>();
    public static ArrayList<String> hrefList = new ArrayList<>();
    public static ArrayList<String> seed_urlList = new ArrayList<>
            (Arrays.asList(
                    "https://neolab.kr"
            ));

    public static void recursive_url(ArrayList<String> input, int x) {
        ArrayList<String> lll = new ArrayList<>(input);
        try {
            System.out.println("---------------------------------------------------------level : " + x + "---------------------------------------------------------");
            for (String in : lll) {
                count++;
                Connection connection = Jsoup.connect(in).ignoreHttpErrors(true);
                Connection.Response setResponse = Jsoup.connect(in).ignoreHttpErrors(true).timeout(5000).execute();
                System.out.println(count + " >>>>>>>>>>>>>>>>> " + in + " : " + setResponse.statusCode());
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

                Filter.filtering(true);
                //true면 화이트필터 적용(neo, Neo) 포함 링크만

                urlCount = 0;
                for (String aa : filteringList) {
                    if (!seed_urlList.contains(aa)) {
                        urlCount++;
                        responseCheck(aa);
                        seed_urlList.add(aa);
                    }
                }
                filteringList.clear();
                System.out.println("중복되지 않은 url 갯수 : " + urlCount);
            }
            if (x < 1) {
                recursive_url(seed_urlList, x + 1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void responseCheck(String inputURL) {
        try {
            Connection.Response response = Jsoup.connect(inputURL).ignoreHttpErrors(true).timeout(5000).execute();
            System.out.println(urlCount + " >"  + inputURL + " : " + response.statusCode());
            visitedList.add(inputURL);
            //하이퍼링크 리스트 순서대로 호출
            if (response.statusCode() > 400) {
                if (!error_urlList.contains(inputURL)) {
                    error_urlList.add(inputURL);
                    respCode_str = Integer.toString(response.statusCode());
                    errorCodeCheck();
                }//하이퍼링크 호출 시 응답 코드가 400이 넘는 경우 응답 코드 체크 메소드 호출 후 에러 리스트에 추가
            }
        } catch (Exception e) {
            if (!error_urlList.contains(inputURL)) {
                error_urlList.add(inputURL);
                error_summary.add("페이지 인증서 만료");
                error_code.add(" ");
            }
        }//하이퍼링크 인증서 만료인 경우 에러 리스트에 추가 후 다음 리스트를 재귀 함수로 호출
    }

}