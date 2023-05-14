package kr.co.hhjpetclinicstudy.infrastructure.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class DynamicQueryUtils {

    public static <T> BooleanExpression filterCondition(T condition,
                                                        Function<T, BooleanExpression> function) {

        T tempCondition = condition;

        if (tempCondition instanceof String s && !StringUtils.hasText(s)) {
            tempCondition = null;
        }

        if (tempCondition instanceof List c && CollectionUtils.isEmpty(c)) {
            tempCondition = null;
        }

        if (tempCondition instanceof Set c && CollectionUtils.isEmpty(c)) {
            tempCondition = null;
        }

        return Optional.ofNullable(tempCondition)
                .map(function)
                .orElse(null);
    }

    public static <T> BooleanExpression generateEq(T value,
                                                   Function<T, BooleanExpression> function) {

        return value == null ? null : function.apply(value);
    }
}
