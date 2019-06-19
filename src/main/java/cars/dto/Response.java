package cars.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode

public class Response {
    private Object content;
    private String message;
    private Integer code;
    private String timestamp;

    public Response() { }

    // *************Getters************************
    public Object getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // *************Setters************************
    public Response setContent(Object content) {
        this.content = content;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Response setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
