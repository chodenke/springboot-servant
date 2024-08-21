package io.github.chodenke.springboot.servant.annotation;

import io.github.chodenke.springboot.servant.config.error.CustomizeErrorAttributes;
import io.github.chodenke.springboot.servant.config.error.CustomizeResponseEntityExceptionHandler;
import io.github.chodenke.springboot.servant.util.json.JsonUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <h2>Annotation to enable SpringBoot Servant</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>暂无</li>
 * </ul>
 *
 * <p>datetime: 2023/3/13 16:52</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CustomizeErrorAttributes.class, CustomizeResponseEntityExceptionHandler.class, JsonUtil.class})
public @interface EnableSpringBootServant {
}
