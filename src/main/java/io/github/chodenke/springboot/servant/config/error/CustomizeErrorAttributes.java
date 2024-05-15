package io.github.chodenke.springboot.servant.config.error;

import io.github.chodenke.springboot.servant.wrapper.response.FailedResponseWrapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * <h2>自定义的 解析与向外提供 请求中的错误属性信息的 处理类</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>spring boot 默认的错误处理控制器为 AbstractErrorController，其默认实现为 BasicErrorController</li>
 *     <li>在 AbstractErrorController 处理异常时，需要借助 ErrorAttributes 接口的实现类 解析并提供请求中的错误信息</li>
 * </ul>
 *
 * <p>datetime: 2023/3/13 17:02</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@Component
public class CustomizeErrorAttributes extends DefaultErrorAttributes {

    /** 日志记录器 */
    private final Log logger = LogFactory.getLog(getClass());

    /**
     * 从请求中解析 错误信息 并返回
     *
     * @param webRequest 请求
     * @param options    错误信息选项
     * @return 封装了错误信息的 Map 实例
     */
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, options);
        Throwable throwable = this.getError(webRequest);
        logger.debug("CustomizeErrorAttributes provide an errorAttributesMap about a " + throwable);
        FailedResponseWrapper failureResponseWrapper = new FailedResponseWrapper(defaultErrorAttributes.get("status").toString(), defaultErrorAttributes.get("error").toString());
        Map<String, Object> result = failureResponseWrapper.toMap();
        logger.debug("The errorAttributesMap to be provided in the customizeErrorAttributes is " + result);
        return result;
    }
}
