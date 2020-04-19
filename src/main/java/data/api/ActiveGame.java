package data.api;

import data.JsonConverter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ActiveGame extends JsonConverter<ActiveGame> {

    private long gameId;
    private String gameType;
    private long gameStartTime;
    private long mapId;
    private long gameLength;
    private String platformId;
    private String gameMode;
    private List<BannedChampion> bannedChampions;
    private long gameQueueConfigId;
    private Observer observers;
    private List<CurrentGameParticipant> participants;

    public ActiveGame(){
        bannedChampions = new ArrayList<>();
        participants = new ArrayList<>();
    }

    @Override
    public ActiveGame jsonToObject(JSONObject jsonObject) {
        gameId = jsonObject.getLong("gameId");
        gameType = jsonObject.getString("gameType");
        gameStartTime = jsonObject.getLong("gameStartTime");
        mapId = jsonObject.getLong("mapId");
        gameLength = jsonObject.getLong("gameLength");
        platformId = jsonObject.getString("platformId");
        gameMode = jsonObject.getString("gameMode");
        jsonObject.getJSONArray("BannedChampion").forEach(o ->
                bannedChampions.add(new BannedChampion().jsonToObject(new JSONObject(o.toString()))));
        gameQueueConfigId = jsonObject.getLong("gameQueueConfigId");
        observers = new Observer(jsonObject.getJSONObject("observers").toString());
        jsonObject.getJSONArray("participants").forEach(oo ->
                participants.add(new CurrentGameParticipant().jsonToObject(new JSONObject(oo.toString()))));
        return this;
    }







    public class BannedChampion extends JsonConverter<BannedChampion> {
        private int pickTurn;
        private long championId;
        private long teamId;

        public int getPickTurn(){return pickTurn;}
        public long getChampionID(){return championId;}
        public long getTeamID(){return teamId;}

        @Override
        public BannedChampion jsonToObject(JSONObject jsonObject) {
            pickTurn = jsonObject.getInt("pickTurn");
            championId = jsonObject.getLong("championId");
            teamId = jsonObject.getLong("teamId");
            return this;
        }
    }

    public class Observer extends JsonConverter<Observer>{
        private String encryptionKey;

        public Observer(String observers) {
            super(observers);
        }

        public String getEncryptionKey(){return encryptionKey;}

        @Override
        public Observer jsonToObject(JSONObject jsonObject) {
            encryptionKey = jsonObject.getString("encryptionKey");
            return this;
        }
    }

    public class CurrentGameParticipant extends JsonConverter<CurrentGameParticipant>{
        private long championId;
        private Perks perks;
        private long profileIconId;
        private boolean bot;
        private long teamId;
        private String summonerName;
        private String summonerId;
        private long spell1Id;
        private long spell2Id;
        private List<GameCustomizationObject> gameCustomizationObjects;

        public CurrentGameParticipant(){
            gameCustomizationObjects = new ArrayList<>();
        }

        public long getChampionID() { return championId; }
        public Perks getPerks() { return perks; }
        public long getProfileIconId() { return profileIconId; }
        public boolean isBot() { return bot; }public long getTeamId() { return teamId; }
        public String getSummonerName() { return summonerName; }
        public String getSummonerId() { return summonerId; }public long getSpell1Id() { return spell1Id; }
        public long getSpell2Id() { return spell2Id; }
        public List<GameCustomizationObject> getGameCustomizationObjects() { return gameCustomizationObjects; }

        @Override
        public CurrentGameParticipant jsonToObject(JSONObject jsonObject) {
            championId = jsonObject.getLong("championId");
            perks = new Perks().jsonToObject(jsonObject.getJSONObject("perks"));
            profileIconId = jsonObject.getLong("profileIconId");
            bot = jsonObject.getBoolean("bot");
            teamId = jsonObject.getLong("teamId");
            summonerName = jsonObject.getString("summonerName");
            summonerId = jsonObject.getString("summonerId");
            spell1Id = jsonObject.getLong("spell1Id");
            spell2Id = jsonObject.getLong("spell2Id");
            jsonObject.getJSONArray("gameCustomizationObject").forEach(o ->
                    gameCustomizationObjects.add(new GameCustomizationObject().jsonToObject(o.toString())));
            return this;
        }

        public class Perks extends JsonConverter<Perks>{
            private List<Long> perkIds;
            private long perkStyle;
            private long perkSubStyle;

            public Perks(){
                perkIds = new ArrayList<>();
            }

            public List<Long> getPerkIds() {return perkIds;}
            public long getPerkStyle() {return perkStyle;}
            public long getPerkSubStyle() {return perkSubStyle;}

            @Override
            public Perks jsonToObject(JSONObject jsonObject) {
                jsonObject.getJSONArray("perkIds").forEach(o ->
                        perkIds.add(Long.getLong(o.toString())));
                perkStyle = jsonObject.getLong("perkStyle");
                perkSubStyle = jsonObject.getLong("perkSubStyle");
                return this;
            }
        }
        public class GameCustomizationObject extends JsonConverter<GameCustomizationObject>{
            private String category;
            private String content;

            public String getCategory(){return category;}
            public String getContent(){return content;}

            @Override
            public GameCustomizationObject jsonToObject(JSONObject jsonObject) {
                category = jsonObject.getString("category");
                content = jsonObject.getString("content");
                return this;
            }
        }
    }
}
