package io.github.chodenke.springboot.servant.annotation.constraints;

import io.github.chodenke.springboot.servant.util.regex.RegexPool;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>校验字符串内容为中国统一社会信用代码</h2>
 * <h3>note:</h3>
 * <ul>
 *     <li>接收 CharSequence</li>
 *     <li>空元素被认为是有效的</li>
 * </ul>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
@Documented
@Constraint(validatedBy = {})
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Pattern(regexp = RegexPool.CHINESE_USC_CODE)
public @interface ChineseUscCode {

    /**
     * message参数，用以配置校验不通过时的提示信息
     *
     * @return 字符串类型的提示信息
     */
    String message() default "must be a chinese usc code";

    /**
     * @return the groups the constraint belongs to
     */
    @SuppressWarnings("unused") Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint
     */
    @SuppressWarnings("unused") Class<? extends Payload>[] payload() default {};
}
