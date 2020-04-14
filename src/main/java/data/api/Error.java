package data.api;

import data.JsonConverter;
import org.json.JSONObject;

public class Error extends JsonConverter<Error> {

    private String errorMessage;
    private int errorCode;



    public Error(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public Error jsonToObject(JSONObject jsonObject) {
        return null;
    }
}
