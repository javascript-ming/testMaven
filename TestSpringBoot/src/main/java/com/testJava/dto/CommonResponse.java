package com.testJava.dto;

public class CommonResponse<T> {
    private int code; // 业务码
    private T data; // 业务数据
    private String message; // 消息

    // 全参数构造器
    public CommonResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // 静态方法，用于快速创建成功的响应体
    public static <T> com.testJava.dto.CommonResponse<T> success(T data, String message) {
        return new com.testJava.dto.CommonResponse<>(1, data, message); // code应该是业务码
    }

    // 静态方法，用于快速创建失败的响应体
    public static <T> com.testJava.dto.CommonResponse<T> fail(int code, String message) {
        return new com.testJava.dto.CommonResponse<>(code, null, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // toString 方法可以帮助调试和日志记录
    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}

