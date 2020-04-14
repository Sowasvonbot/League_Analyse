package data.api.matches;

import data.JsonConverter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MatchList extends JsonConverter<MatchList> {



    private int startIndex;
    private int totalGames;
    private int endIndex;
    private List<MatchReference> matches;

    public MatchList() {
        startIndex = 0;
        totalGames = 0;
        endIndex = 0;
        matches = new ArrayList<>();
    }

    @Override
    public MatchList jsonToObject(JSONObject jsonObject) {
        startIndex = jsonObject.getInt("startIndex");
        totalGames = jsonObject.getInt("totalGames");
        endIndex = jsonObject.getInt("endIndex");

        jsonObject.getJSONArray("matches").forEach(o ->
                matches.add(new MatchReference().jsonToObject(new JSONObject(o.toString()))));
        return this;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public List<MatchReference> getMatches() {
        return matches;
    }

    public class MatchReference extends JsonConverter<MatchReference>{
        private long gameID;
        private String role;
        private int season;
        private String platformID;
        private int champion;
        private int queue;
        private String lane;
        private long timestamp;

        public long getGameID() {
            return gameID;
        }

        public String getRole() {
            return role;
        }

        public int getSeason() {
            return season;
        }

        public String getPlatformID() {
            return platformID;
        }

        public int getChampion() {
            return champion;
        }

        public int getQueue() {
            return queue;
        }

        public String getLane() {
            return lane;
        }

        public long getTimestamp() {
            return timestamp;
        }

        @Override
        public MatchReference jsonToObject(JSONObject jsonObject) {
            gameID = jsonObject.getInt("gameId");
            role = jsonObject.getString("role");
            season = jsonObject.getInt("season");
            platformID = jsonObject.getString("platformId");
            champion = jsonObject.getInt("champion");
            queue = jsonObject.getInt("queue");
            lane = jsonObject.getString("lane");
            timestamp = jsonObject.getLong("timestamp");
            return this;
        }
    }
}
