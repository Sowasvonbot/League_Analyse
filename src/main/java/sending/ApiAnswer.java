package sending;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiAnswer {


    private String data;
    private String errorMessage;
    private int errorCode;
    private boolean error;


    public ApiAnswer(String data){
        JSONObject jsonObject;
        JSONObject errorObject;
        try{
            jsonObject = new JSONObject(data);

        } catch (JSONException e){
            error = true;
            errorCode = -1;
            errorMessage = "invalid JSONObject";
            this.data = "none";
            return;
        }

        try{
            errorObject =  jsonObject.getJSONObject("status");
            error = true;
        } catch (JSONException e){
            this.data = data;
            errorCode = 200;
            errorMessage = "none";
            error = false;
            return;
        }

        try{
            errorCode = errorObject.getInt("status_code");
            errorMessage = errorObject.getString("message");
        } catch (JSONException e){
            errorCode = -1;
            errorMessage = "invalid JSONObject";
            this.data = "none";
        }

    }

    public String getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public boolean isError() {
        return error;
    }
}
