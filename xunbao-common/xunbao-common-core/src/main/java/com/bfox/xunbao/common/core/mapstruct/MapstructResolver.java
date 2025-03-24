package com.bfox.xunbao.common.core.mapstruct;

import io.github.linpeilie.Converter;
import io.github.linpeilie.mapstruct.SpringContextUtils;

import java.util.List;

/**
 * 数据转换器
 * 源对象与目标对象属性、名称最好都一致避免不必要的麻烦
 * @author eden
 * @date 2024/9/8 21:46:46
 */
public abstract class MapstructResolver {

    private static final Converter converter;

    static {
        converter = SpringContextUtils.getBean(Converter.class);
    }

    /**
     * 对象转换
     * @param source 源对象
     * @param targetType 目标类型
     * @return T
     */
    public static <S, T> T convert(S source, Class<T> targetType) {
        return converter.convert(source, targetType);
    }

    /**
     * 对象转换
     * @param source 源对象
     * @param target 目标对象
     * @return T
     */
    public static <S, T> T convert(S source, T target) {
        return converter.convert(source, target);
    }

    /**
     * List集合转换
     * @param source 源对象
     * @param targetType 目标类型
     * @return List<T>
     */
    public static <S, T> List<T> convert(List<S> source, Class<T> targetType) {
        return converter.convert(source, targetType);
    }
}
