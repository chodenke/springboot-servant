package io.github.chodenke.springboot.servant.wrapper.base;

import io.github.chodenke.springboot.servant.annotation.common.ExSegment;

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
     * 获取异常分担名称
     * <br/>
     * 这里进行了默认实现，从实现该接口的枚举类中获取由 @ExSegment注解 配置的异常分段信息，从中提取分段名称。
     * 如果实现该接口的枚举类并没有配置 @ExSegment注解，则认为是 COMMON 分段。
     */
    default String getExSegmentCode() {
        if (getClass().isAnnotationPresent(ExSegment.class)) {
            return getClass().getAnnotation(ExSegment.class).value();
        } else {
            return "COMMON";
        }
    }

}
