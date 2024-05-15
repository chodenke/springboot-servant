package io.github.chodenke.springboot.servant.wrapper.exception;

/**
 * <h2>single 业务异常提供器</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 * <p>datetime: 2024/5/15 9:42</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@FunctionalInterface
public interface SingleBizExceptionSupplier {

    BizException get();
}
