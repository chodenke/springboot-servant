package io.github.chodenke.springboot.servant.wrapper.base;

import io.github.chodenke.springboot.servant.annotation.common.ResponseSegment;

/**
 * <h2>返回结果枚举的基础接口</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public interface IResultEnumBase {

    /**
     * 获取返回结果枚举名称
     *
     * @return 返回结果枚举名称
     */
    String name();

    /**
     * 获取返回结果描述
     *
     * @return 结果描述
     */
    String getDesc();

    /**
     * 获取返回信息分段
     * <br/>
     * 这里进行了默认实现，从实现该接口的枚举类中获取由 @ResponseSegment注解 配置的返回信息分段值。
     * 如果实现该接口的枚举类并没有配置 @ResponseSegment 注解，则认为是 COMMON 分段。
     */
    default String getResponseSegmentCode() {
        if (getClass().isAnnotationPresent(ResponseSegment.class)) {
            return getClass().getAnnotation(ResponseSegment.class).value();
        } else {
            return "COMMON";
        }
    }

}
