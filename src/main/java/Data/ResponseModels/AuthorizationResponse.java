package Data.ResponseModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorizationResponse {
    @JsonIgnore
    @JsonProperty("code")
    private int code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("token")
    private String token;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
