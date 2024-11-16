package com.example.exception;

import java.io.Serial;

/**
 * 自定义token令牌异常
 *
 * @author DF.
 * @date 2023-05-20 21:36:20
 */
public class TokenException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public TokenException(){
        super();
    }

    public TokenException(String errorMsg){
        super(errorMsg);
    }

}
