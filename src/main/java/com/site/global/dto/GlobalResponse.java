package com.site.global.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse<T>{
    private int status;
    private String message;
    private T data;

    public GlobalResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static <T> GlobalResponse<T> success(T data) {
        return new GlobalResponse<>(HttpStatus.OK.value(), "OK", data);
    }

    public static <T> GlobalResponse<T> created(T data) {
        return new GlobalResponse<>(HttpStatus.CREATED.value(), "POST CREATED", data);
    }

    public static GlobalResponse<Void> noContent() {
        return new GlobalResponse<>(HttpStatus.NO_CONTENT.value(), "POST DELETED");
    }
}
