package com.lxd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Lxd
 * @date : 17:34 2021/4/5
 * 自定义异常 404
 */
@ResponseStatus(HttpStatus.NOT_FOUND)  //表示资源找不到的状态码 标识了状态码就不会被ControllerExceptionHandler拦截
public class NoFoundException extends RuntimeException {
    public NoFoundException(){

    }

    public NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundException(String message) {
        super(message);
    }
}
