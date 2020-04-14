package sending;


import data.riotToken;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GetRequestBuilder {

    public static Request getRequest(String url, Map<String, ?> queryData){
        StringBuilder queryParams = new StringBuilder();
        if (queryData != null && !queryData.isEmpty()){
            queryData.forEach((key, value) -> queryParams.append(
                    "?" + key + "=" + String.valueOf(value)));
            url += queryParams.toString();
        }
        HttpUrl completedUrl = HttpUrl.get(url);

        return new Request.Builder()
                .url(completedUrl)
                .addHeader("X-Riot-Token", riotToken.token)
                .get()
                .build();
    }

}
