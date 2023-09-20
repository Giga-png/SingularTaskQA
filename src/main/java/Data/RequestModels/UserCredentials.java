package Data.RequestModels;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCredentials {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
