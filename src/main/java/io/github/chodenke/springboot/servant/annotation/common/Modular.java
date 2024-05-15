package io.github.chodenke.springboot.servant.annotation.common;

import java.lang.annotation.*;

/**
 * <h2>模块注解</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>用来在类上注解该类属于哪个业务模块，方便统一管理模块编码 主要用于各个模块的 FailureEnum 及 SuccessEnum 等</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:23</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Modular {

    String value() default "COMMON";

}
