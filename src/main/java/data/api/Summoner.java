package data.api;

import data.JsonConverter;
import org.json.JSONObject;

public class Summoner extends JsonConverter<Summoner> {



    private String accountID;
    private int profileIconId;
    private long revisionDate;
    private String name;
    private String id;
    private String puuid;
    private long summonerLevel;


    public Summoner(){
        accountID = "";
        profileIconId = 0;
        revisionDate = 0L;
        name = "";
        id = "";
        puuid = "";
        summonerLevel = 0L;
    }




    @Override
    public Summoner jsonToObject(JSONObject jsonObject) {
        accountID = jsonObject.getString("accountId");
        profileIconId = jsonObject.getInt("profileIconId");
        revisionDate = jsonObject.getLong("revisionDate");
        name = jsonObject.getString("name");
        id = jsonObject.getString("id");
        puuid = jsonObject.getString("puuid");
        summonerLevel = jsonObject.getLong("summonerLevel");



        return this;
    }


    public String getAccountID() {
        return accountID;
    }

    public int getProfileIconId() {
        return profileIconId;
    }

    public long getRevisionDate() {
        return revisionDate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPuuid() {
        return puuid;
    }

    public long getSummonerLevel() {
        return summonerLevel;
    }
}
