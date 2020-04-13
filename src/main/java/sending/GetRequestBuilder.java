package sending;


import data.riotToken;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.HashMap;

public class GetRequestBuilder {

    public static Request getRequest(HttpUrl url){
        return new Request.Builder()
                .url(url)
                .addHeader("X-Riot-Token", riotToken.token)
                .get()
                .build();
    }

}
