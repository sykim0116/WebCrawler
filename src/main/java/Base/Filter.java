package Base;

import java.util.ArrayList;
import java.util.Arrays;

import static Base.RecursiveTest.hrefList;
import static Base.RecursiveTest.seed_urlList;

public class Filter {
    public static boolean whiteFilter;
    public static boolean blackFilter;

    public static ArrayList<String> whiteList = new ArrayList<>(Arrays.asList(
            "neo",
            "smartpen",
            "lamy"
    ));
    public static ArrayList<String> blackList = new ArrayList<>(Arrays.asList(
            "instagram",
            "naver",
            "google",
            "youtube",
            "facebook",
            "amazon"
    ));

    public static boolean filtering(String url) {
        if (seed_urlList.contains(whiteList) && !seed_urlList.contains(blackList)) {
            whiteFilter = true;
            blackFilter = false;
            System.out.println(whiteFilter);

            return true;
        } else if (!seed_urlList.contains(whiteList) && seed_urlList.contains(blackList)) {
            whiteFilter = false;
            blackFilter = true;

            return false;
        } else if (seed_urlList.contains(whiteList) && seed_urlList.contains(blackList)) {
            whiteFilter = true;
            blackFilter = false;

            return true;
        } else {
            whiteFilter = false;
            blackFilter = false;

            return false;
        }
    }
}
