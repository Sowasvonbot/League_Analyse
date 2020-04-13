package data;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class JsonConverter<T> {


    public abstract T jsonToObject(JSONObject jsonObject);

    public T jsonToObject(String jsonObject){
        try {
            JSONObject json = new JSONObject(jsonObject);
            return jsonToObject(json);
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
}
