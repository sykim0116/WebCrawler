package Base;

import java.util.ArrayList;
import java.util.Arrays;

import static Base.RecursiveTest.hrefList;

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

    public static boolean filtering() {
        if (hrefList.contains(whiteList)) {
            whiteFilter = true;
            blackFilter = false;

            return true;
        } else if (hrefList.contains(blackList)) {
            whiteFilter = false;
            blackFilter = true;

            return false;
        } else if (hrefList.contains(whiteList) && hrefList.contains(blackList)) {
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
