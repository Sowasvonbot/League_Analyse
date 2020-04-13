package sending;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.*;

public class RequestSender {

    private ExecutorService executorService;
    private static RequestSender instance;
    private OkHttpClient httpClient;


    private RequestSender(){
        executorService = Executors.newFixedThreadPool(3);
        httpClient = new OkHttpClient();
        instance = this;

    }

    public static CompletableFuture<ApiAnswer> sendRequest(Request request){
        if (instance == null) instance = new RequestSender();

        CompletableFuture<ApiAnswer> completableFuture
                = new CompletableFuture<>();

            instance.executorService.submit(() -> {
                try {
                    Response response = instance.httpClient.newCall(request).execute();
                    String responseBody = response.body().string();
                    response.close();
                    System.out.println(responseBody);
                    completableFuture.complete(new ApiAnswer(responseBody));

                } catch (IOException e) {
                    completableFuture.complete(new ApiAnswer("error"));
                }
            });

        return completableFuture;

    }

    public static void shutdown(){
        if (instance == null) return;
        instance.executorService.shutdown();
    }
}
