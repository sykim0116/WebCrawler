package Base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static Base.RecursiveTest.*;

public class Filter {
    public static ArrayList<String> whiteList = new ArrayList<>(Arrays.asList(
            "neo",
            "Neo"
    ));
    public static ArrayList<String> blackList = new ArrayList<>(Arrays.asList(
            ".instagram",
            "naver",
            "google",
            "youtube",
            "facebook.com",
            "amazon",
            ".zip",
            ".png",
            ".pdf",
            ".exe",
            ".dmg",
            "mailto:"
    ));
    public static List<String> streamList(List<String> inputLists, String target) {
        return inputLists.stream()
                .filter(input -> input.contains(target))
                .collect(Collectors.toList());
    }

    public static List<String> streamList_f(List<String> inputLists, String target) {
        return inputLists.stream()
                .filter((input -> !input.contains(target)))
                .collect(Collectors.toList());
    }

    public static void whiteFilter(ArrayList<String> input){
        for(String a : whiteList){
            filteringList.addAll(streamList(input, a));
        }
    }

    public static void blackFilter(ArrayList<String> input){
        for(String a : blackList){
//            if(!sub_urlList.contains(a)){
                filteringList.addAll(streamList_f(input, a));
//            }
        }
    }

    public static void filtering(boolean b){
        if(b){
            whiteFilter(sub_urlList);
        }else{
            blackFilter(sub_urlList);
        }
    }
}
