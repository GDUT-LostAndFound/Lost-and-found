package com.example.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.enums.CodeEnum;
import com.example.model.Result;
import com.example.util.ResultUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 全局异常处理程序
 *
 * @author DF.
 * @date 2023-05-20 21:31:55
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获所有异常
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = Exception.class)
    public <T> Result<T> allException(Exception exception) {
        logRequest();
        log.error("未知异常：", exception);
        return ResultUtil.error(CodeEnum.SERVER_ERROR.getCode(), CodeEnum.SERVER_ERROR.getMsg());
    }

    /**
     * http消息请求参数异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public <T> Result<T> httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        logRequest();
        log.error("HTTP请求参数异常", exception);
        return ResultUtil.error(CodeEnum.CLIENT_ERROR.getCode(), "HTTP请求参数异常");
    }

    /**
     * 方法参数校验异常处理程序
     *
     * @param e e
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> Result<T> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        logRequest();
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.error(objectError.getDefaultMessage());
        return ResultUtil.error(CodeEnum.ERROR.getCode(), objectError.getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public <T> Result<T> constraintViolationExceptionHandler(ConstraintViolationException e) {
        logRequest();
        return ResultUtil.error(CodeEnum.ERROR.getCode(), e.getMessage());
    }

    /**
     * 用户未登录异常处理程序
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = TokenException.class)
    public <T> Result<T> tokenException(TokenException exception) {
        logRequest();
        log.error("用户未登录", exception);
        return ResultUtil.error(CodeEnum.UNAUTHORIZED.getCode(), exception.getMessage());
    }

    /**
     * json转换异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = JsonProcessingException.class)
    public <T> Result<T> jsonProcessingException(JsonProcessingException exception) {
        logRequest();
        log.error("json转换异常", exception);
        return ResultUtil.error(CodeEnum.SERVER_ERROR.getCode(), exception.getMessage());
    }

    /**
     * 空指针异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = NullPointerException.class)
    public <T> Result<T> nullPointerException(NullPointerException exception) {
        logRequest();
        log.error("空指针异常", exception);
        return ResultUtil.error(CodeEnum.NULL_POINT.getCode(), CodeEnum.NULL_POINT.getMsg());
    }

    /**
     * 线程池异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = ExecutionException.class)
    public <T> Result<T> executionException(ExecutionException exception) {
        logRequest();
        log.error("线程池异常", exception);
        return ResultUtil.error(CodeEnum.SERVER_ERROR.getCode(), CodeEnum.SERVER_ERROR.getMsg());
    }


    /**
     * 线程打断异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = InterruptedException.class)
    public <T> Result<T> interruptedException(InterruptedException exception) {
        logRequest();
        log.error("线程打断异常", exception);
        return ResultUtil.error(CodeEnum.SERVER_ERROR.getCode(), CodeEnum.SERVER_ERROR.getMsg());
    }

    /**
     * servlet请求参数丢失异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public <T> Result<T> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        logRequest();
        log.error("请求参数丢失异常", exception);
        return ResultUtil.error(CodeEnum.NULL_POINT.getCode(), CodeEnum.NULL_POINT.getMsg());
    }

    /**
     * token签名验证异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = SignatureVerificationException.class)
    public <T> Result<T> signatureVerificationException(SignatureVerificationException exception) {
        logRequest();
        log.error("Token签名异常", exception);
        return ResultUtil.error(CodeEnum.CLIENT_ERROR.getCode(), "Token签名错误");
    }

    /**
     * token令牌过期异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = TokenExpiredException.class)
    public <T> Result<T> tokenExpiredException(TokenExpiredException exception) {
        logRequest();
        log.error("Token过期异常", exception);
        return ResultUtil.error(CodeEnum.UNAUTHORIZED.getCode(), "用户登录信息已过期,请重新登录!");
    }

    /**
     * token算法不匹配异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = AlgorithmMismatchException.class)
    public <T> Result<T> algorithmMismatchException(AlgorithmMismatchException exception) {
        logRequest();
        log.error("Token算法异常", exception);
        return ResultUtil.error(CodeEnum.CLIENT_ERROR.getCode(), "Token算法错误");
    }

    /**
     * token解码异常
     *
     * @param exception 异常
     * @return {@link Result}<{@link T}>
     */
    @ExceptionHandler(value = JWTDecodeException.class)
    public <T> Result<T> jwtDecodeException(JWTDecodeException exception) {
        logRequest();
        log.error("Token解析异常", exception);
        return ResultUtil.error(CodeEnum.CLIENT_ERROR.getCode(), "Token解析异常");
    }

    /**
     * sql语法异常
     *
     * @param exception 异常
     * @return {@link Object}
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Object badSqlGrammarException(BadSqlGrammarException exception) {
        logRequest();
        log.error("sql语法错误", exception);
        return ResultUtil.error(CodeEnum.SERVER_ERROR.getCode(), "SQL语法错误");
    }

    @ExceptionHandler(value = NotLoginException.class)
    public Object notLoginException(NotLoginException exception) {
        logRequest();
        log.error("登录认证失败", exception);
        return ResultUtil.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = NotPermissionException.class)
    public Object notPermissionException(NotPermissionException exception) {
        return ResultUtil.error(exception.getCode(), exception.getMessage());
    }

    /**
     * 日志请求体
     */
    private void logRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求的内容，getSignature方法获取封装了方法信息的签名对象，可以获取方法所在类的类名，传入的参数，方法的名称等
        //request.getMethod()是获取请求的类型，get什么的，getSignature().getName()才是获取方法的名称
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.error("请求的url:" + url + "，请求的方法：" + method);
    }

}
