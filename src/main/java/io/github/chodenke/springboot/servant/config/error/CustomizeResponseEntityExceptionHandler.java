package io.github.chodenke.springboot.servant.config.error;

import io.github.chodenke.springboot.servant.wrapper.exception.BizException;
import io.github.chodenke.springboot.servant.wrapper.response.FailedResponseWrapper;
import jakarta.validation.ConstraintViolationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <h2>自定义的 控制器异常返回处理器</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>当控制器中抛出异常时，被该类处理 该类方法上的注解 @ExceptionHandler 配置了该方法会拦截处理的异常类型， 大多数异常都被 ResponseEntityExceptionHandler 类的 handleException(Exception ex, WebRequest request) 方法拦截</li>
 * </ul>
 *
 * <p>datetime: 2023/3/13 17:06</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@ControllerAdvice
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /** 日志记录器 */
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * @param request 请求
     * @param ex      异常
     * @return 响应实体实例
     * @throws Throwable 未处理的异常
     */
    @ExceptionHandler({BizException.class, ConstraintViolationException.class})
    public ResponseEntity<?> customizeHandleException(Exception ex, WebRequest request) throws Throwable {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof BizException) {
            HttpStatus status = HttpStatus.OK;
            return handleBizException((BizException) ex, headers, status);
        } else if (ex instanceof ConstraintViolationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleConstraintViolationException((ConstraintViolationException) ex, headers, status, request);
        }
        throw ex;
    }


    /**
     * 处理 BizException 的方法
     *
     * @param ex      BizException 实例
     * @param headers 响应头
     * @param status  响应的 http 状态码封装实例
     * @return 响应实体实例
     */
    protected ResponseEntity<Object> handleBizException(BizException ex, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<>(new FailedResponseWrapper(ex.getCode(), ex.getMessage()), headers, status);
    }

    /**
     * 处理 ConstraintViolationException 的方法
     *
     * @param ex      ConstraintViolationException 实例
     * @param headers 响应头
     * @param status  响应的 http 状态码封装实例
     * @param request 请求
     * @return 响应实体实例
     */
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, null, headers, status, request);
    }


    /**
     * 重写父类的 处理内部异常的方法
     * note: 在父类中，handleException(Exception ex, WebRequest request) 方法捕捉到的异常经过初步处理后最终都交由了这个方法处理，
     * 默认的响应实体实例中 body 是空的，本项目重写了这个方法，填充一下 body，记录了一下日志
     *
     * @param ex      异常实例
     * @param body    响应实体的 body
     * @param headers 响应头
     * @param statusCode  响应的 http 状态码封装实例
     * @param request 请求
     * @return 响应实体实例
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
            logger.debug("CustomizeResponseEntityExceptionHandler handle exception:" + ex);
            if (body == null) {
                body = new FailedResponseWrapper(String.valueOf(statusCode.value()), HttpStatus.valueOf(statusCode.value()).getReasonPhrase());
            }
            logger.debug("The response body to be returned in the CustomizeResponseEntityExceptionHandler is " + body);
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
