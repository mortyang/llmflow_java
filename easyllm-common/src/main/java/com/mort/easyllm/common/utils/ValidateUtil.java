package com.mort.easyllm.common.utils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author Mort
 */
public class ValidateUtil {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();


    private ValidateUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }


    /**
     * 验证对象并返回校验结果
     *
     * @param object 需要校验的对象
     * @param <T>    对象的类型
     * @return 校验结果的集合
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return VALIDATOR_FACTORY.getValidator().validate(object);
    }


    /**
     * 验证对象并抛出异常（如果有校验错误）
     *
     * @param object 需要校验的对象
     * @param <T>    对象的类型
     * @throws ValidationException 如果校验失败
     */
    public static <T> void validateAndThrow(T object) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validate(object);
        if (!violations.isEmpty()) {
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                message.append(violation.getMessage()).append("\n");
            }
            throw new ValidationException(message.toString());
        }
    }

}
