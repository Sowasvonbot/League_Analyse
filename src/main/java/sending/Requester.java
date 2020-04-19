package sending;

import data.Constants;
import data.JsonConverter;
import data.api.ActiveGame;
import data.api.Error;
import data.api.Summoner;
import data.api.matches.Match;
import data.api.matches.MatchList;
import data.api.matches.MatchTimeline;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public abstract class Requester {


    public static CompletableFuture<JsonConverter> getApiData(@NotNull Endpoint endpoint, @NotNull String pathData, @Nullable Map<String, ?> queryData){
        JsonConverter data;
        String url = Constants.leagueEndpoint;
        ApiAnswer answer;
        CompletableFuture<JsonConverter> future = new CompletableFuture<>();

        switch (endpoint) {
            case MATCH:
                data = new Match();
                break;
            case SUMMONER:
                data = new Summoner();
                url += Constants.apiSummonerByName + pathData;
                break;

            case MATCHLIST:
                data = new MatchList();
                url+= Constants.apiMatchListByEncryptedID + pathData;
                break;
            case MATCHTIMELINE:
                data = new MatchTimeline();
                url += Constants.apiMatchTimeLineByID + pathData;
                break;
            case SPECTATOR:
                data = new ActiveGame();
                url += Constants.apiSpectatorByID + pathData;
                break;
            default:
                future.complete(new Error("No correct enum given", 4));
                return future;

        }


        String finalUrl = url;
        System.out.println(url);

        future = CompletableFuture.supplyAsync(() -> RequestSender.sendRequest(GetRequestBuilder.getRequest(
                finalUrl,
                queryData))).thenApply(apiAnswerCompletableFuture -> {
                    return getJsonConverterFromAnswer(apiAnswerCompletableFuture, data);
                }
        );

        return future;
    }

    private static JsonConverter getJsonConverterFromAnswer(CompletableFuture<ApiAnswer> apiAnswerFuture, JsonConverter data){
        try {
            ApiAnswer apiAnswer = apiAnswerFuture.get();
            if (apiAnswer.isError()) return data = new Error(apiAnswer.getErrorMessage(), apiAnswer.getErrorCode());
            data.jsonToObject(apiAnswer.getData());
            return data;
        } catch (ExecutionException | InterruptedException e) {
            return new Error(e.getMessage(), 4);
        }
    }


    public enum Endpoint{
        MATCH, SUMMONER, MATCHLIST, MATCHTIMELINE, SPECTATOR
    }


}
