import data.Constants;
import data.api.Summoner;
import okhttp3.HttpUrl;
import sending.GetRequestBuilder;
import sending.RequestSender;

import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        //Sending request async
        Summoner summoner = new Summoner();
        RequestSender
                .sendRequest(GetRequestBuilder.getRequest(
                                HttpUrl.parse(Constants.leagueEndpoint +
                                        Constants.apiSummonerByName +
                                        "Sowasvonbaf")))


                //When response is ready, cast it into a summoner object, then print the lvl of the summoner
                .thenAcceptAsync(apiAnswer -> {
                    if(!apiAnswer.isError()) summoner.jsonToObject(apiAnswer.getData());
                })
                .thenRun(() -> System.out.println("Summoner Level: " + summoner.getSummonerLevel()));


        RequestSender.shutdown();


        System.out.println("Going to sleep");
        Thread.sleep(10000);

    }
}
