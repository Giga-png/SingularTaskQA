package Data.ResponseModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoResponse {
    @JsonIgnore
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;

    @JsonIgnore
    @JsonProperty("data")
    private final Data data = new Data();

    public static class Data {
        private String name;
        private String surname;
        private String age;
        private Integer gender;
        private String language;
        private String status;
        private Boolean isBlocked;
    }

    public String getMessage() {
        return message;
    }

}
