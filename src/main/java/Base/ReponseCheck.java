package Base;

import java.util.ArrayList;

import static Base.RecursiveTest.*;

public class ReponseCheck {

    public static void errorCodeCheck() {
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
}
