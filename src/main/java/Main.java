import data.Constants;
import data.JsonConverter;
import data.api.Error;
import data.api.Summoner;
import data.api.matches.Match;
import data.api.matches.MatchList;
import okhttp3.HttpUrl;
import sending.GetRequestBuilder;
import sending.RequestSender;
import sending.Requester;

import java.util.concurrent.ExecutionException;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        //Sending request async

        Requester.getApiData(
                Requester.Endpoint.SUMMONER,
                "byourside",
                null).thenAccept(jsonConverter -> {

                    if (jsonConverter instanceof Summoner) {
                        Requester.getApiData(Requester.Endpoint.MATCHLIST,
                                ((Summoner) jsonConverter).getAccountID(),
                                null).thenAccept(jsonConverter1 -> {
                            MatchList matchList = (MatchList) jsonConverter1;
                            matchList.getMatches().forEach(matchReference -> System.out.println(matchReference.getLane()));
                        });
                    } else {
                        System.out.println(((Error) jsonConverter).getErrorMessage());
                    }
        });





        System.out.println("Going to sleep");
        Thread.sleep(10000);
        RequestSender.shutdown();

    }
}
