package Base;

import java.util.ArrayList;
import java.util.Arrays;

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
        for (String a : whiteList) {
            if (url.contains(a)) {
                whiteFilter = true;
            }else{
                whiteFilter = false;
            }
        }
        return whiteFilter;
    }
}
