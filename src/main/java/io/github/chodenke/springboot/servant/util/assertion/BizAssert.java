package io.github.chodenke.springboot.servant.util.assertion;

import io.github.chodenke.springboot.servant.wrapper.exception.BizException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * <h2>业务断言工具类</h2>
 *
 * <p>datetime: 2024/5/15 10:10</p>
 *
 * @author chodenke
 * @since JDK 17
 */
public class BizAssert {

    /**
     * 断言提供的表达式为真，如果非真，则使用参数中的异常提供器抛出异常
     *
     * @param expression        boolean 表达式
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void isTrue(boolean expression, Supplier<? extends X> exceptionSupplier) throws X {
        if (!expression) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的表达式为假，如果非假，则使用参数中的异常提供器抛出异常
     *
     * @param expression        boolean 表达式
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void isFalse(boolean expression, Supplier<? extends X> exceptionSupplier) throws X {
        if (expression) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的 object 为 null，如果不为 null，则使用参数中的异常提供器抛出异常
     *
     * @param object            对象
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void isNull(Object object, Supplier<? extends X> exceptionSupplier) {
        if (object != null) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的 object 不为 null，如果为 null，则使用参数中的异常提供器抛出异常
     *
     * @param object            对象
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void notNull(Object object, Supplier<? extends X> exceptionSupplier) {
        if (object == null) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的 字符串文本 有长度，如果没有长度，则使用参数中的异常提供器抛出异常
     *
     * @param text              字符串文本
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void hasLength(String text, Supplier<? extends X> exceptionSupplier) {
        if (!StringUtils.hasLength(text)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的 集合 有元素，如果没有元素，则使用参数中的异常提供器抛出异常
     *
     * @param collection        集合
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void notEmpty(Collection<?> collection, Supplier<? extends X> exceptionSupplier) {
        if (CollectionUtils.isEmpty(collection)) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 断言提供的 集合 没有元素，如果有元素，则使用参数中的异常提供器抛出异常
     *
     * @param collection        集合
     * @param exceptionSupplier 异常提供器
     * @param <X>               异常类型
     * @throws X 异常
     */
    public static <X extends BizException> void isEmpty(Collection<?> collection, Supplier<? extends X> exceptionSupplier) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw exceptionSupplier.get();
        }
    }

}
