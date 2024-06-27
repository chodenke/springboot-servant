package io.github.chodenke.springboot.servant.annotation.common;

import java.lang.annotation.*;

/**
 * <h2>返回信息分段注解</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>用来在返回信息枚举（各个 FailureEnum 及 SuccessEnum）上注解该返回信息枚举属于哪个分段，使生成的返回信息code有分段区分</li>
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
public @interface ResponseSegment {

    String value() default "COMMON";

}
