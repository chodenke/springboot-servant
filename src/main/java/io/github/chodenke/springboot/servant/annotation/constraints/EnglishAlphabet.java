package io.github.chodenke.springboot.servant.annotation.constraints;

import io.github.chodenke.springboot.servant.util.regex.RegexPool;
import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraintvalidation.SupportedValidationTarget;
import jakarta.validation.constraintvalidation.ValidationTarget;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <h2>校验字符串为大小写英文字符且长度在范围之内</h2>
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
@Pattern(regexp = RegexPool.ENGLISH_ALPHABET)
@Length
@ReportAsSingleViolation
public @interface EnglishAlphabet {

    /**
     * minLength 参数，用以配置字符串最小长度值，配置后会写入 @Length 注解的 min 参数
     *
     * @return int 类型的字符串最小长度值
     */
    @OverridesAttribute(constraint = Length.class, name = "min") int minLength() default 0;

    /**
     * maxLength 参数，用以配置字符串最大长度值，配置后会写入 @Length 注解的 max 参数
     *
     * @return int 类型的字符串最大长度值
     */
    @OverridesAttribute(constraint = Length.class, name = "max") int maxLength() default Integer.MAX_VALUE;

    /**
     * message参数，用以配置校验不通过时的提示信息
     *
     * @return 字符串类型的提示信息
     */
    String message() default "must be a english alphabet string with a length between {minLength} and {maxLength}";

    /**
     * @return the groups the constraint belongs to
     */
    @SuppressWarnings("unused") Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint
     */
    @SuppressWarnings("unused") Class<? extends Payload>[] payload() default {};
}
