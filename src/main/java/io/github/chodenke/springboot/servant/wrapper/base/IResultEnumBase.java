package io.github.chodenke.springboot.servant.wrapper.base;

import io.github.chodenke.springboot.servant.annotation.common.Modular;

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
     * 获取模块名称
     * <br/>
     * 这里进行了默认实现，从实现该接口的枚举类中获取由 @Modular注解 配置的模块信息，从中提取模块名称。
     * 如果实现该接口的枚举类并没有配置 @Modular注解，则认为是 COMMON 模块。
     */
    default String getModularCode() {
        if (getClass().isAnnotationPresent(Modular.class)) {
            return getClass().getAnnotation(Modular.class).value();
        } else {
            return "COMMON";
        }
    }

}
