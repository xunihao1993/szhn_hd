package com.caitb.library_manager.utils;

import com.caitb.library_manager.dto.R;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * 功能描述: 一些通用方法
 * <p>
 * 作者: July
 * 日期: 2019-05-11 16:27
 */
public class CommonUtils {

    /**
     * 产生code
     * @param prefix
     * @return
     */
    public static String generateCode(String prefix) {
        return (StringUtils.isBlank(prefix) ? "" : prefix) + UUID.randomUUID().toString().replace("-", "");
    }

    public static R generateOk() {
        R<Object> ok = R.ok();
        ok.setData(true);
        return ok;
    }

    public static R generateError(R r) {
        r.setData(false);
        return r;
    }

    /**
     * 将值放入ConcurrentMap，已经考虑第一次并发问题
     *
     * @param map   ConcurrentMap
     * @param key   关键字
     * @param value 值
     * @param <K>   关键字类型
     * @param <V>   值类型
     * @return 旧值
     */
    public static <K, V> V putToConcurrentMap(ConcurrentMap<K, V> map, K key, V value) {
        V old = map.putIfAbsent(key, value);
        return old != null ? old : value;
    }

    /**
     * 判断一个集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断一个集合是否为非空
     *
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * 判断一个Map是否为空
     *
     * @param map Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断一个Map是否为非空
     *
     * @param map Map
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Map map) {
        return map != null && !map.isEmpty();
    }

    /**
     * 判断一个Array是否为空
     *
     * @param array 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断一个Array是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 取数值
     *
     * @param num        数字
     * @param defaultInt 默认值
     * @param <T>        数字的子类
     * @return int
     */
    public static <T extends Number> T parseNum(T num, T defaultInt) {
        return num == null ? defaultInt : num;
    }

    /**
     * 字符串转数值
     *
     * @param num        数字
     * @param defaultInt 默认值
     * @return int
     */
    public static int parseInt(String num, int defaultInt) {
        if (num == null) {
            return defaultInt;
        } else {
            try {
                return Integer.parseInt(num);
            } catch (Exception e) {
                return defaultInt;
            }
        }
    }

    /**
     * String Long turn number.
     *
     * @param num         The number of strings.
     * @param defaultLong The default value
     * @return long
     */
    public static long parseLong(String num, long defaultLong) {
        if (num == null) {
            return defaultLong;
        } else {
            try {
                return Long.parseLong(num);
            } catch (Exception e) {
                return defaultLong;
            }
        }
    }

    /**
     * 字符串转布尔
     *
     * @param bool       数字
     * @param defaultVal 默认值
     * @return int
     */
    public static boolean parseBoolean(String bool, boolean defaultVal) {
        if (bool == null) {
            return defaultVal;
        } else {
            return "true".equalsIgnoreCase(bool) || "1".equalsIgnoreCase(bool);
        }
    }

    public static BigDecimal parseBigDecimal(String num, BigDecimal defaultVal) {
        if (num == null) {
            return defaultVal;
        } else {
            try {
                return new BigDecimal(num);
            } catch (Exception e) {
                return defaultVal;
            }
        }
    }

    /**
     * 字符串转值
     *
     * @param nums      多个数字
     * @param separator 分隔符
     * @return int[]
     */
    public static int[] parseInts(String nums, String separator) {
        String[] ss = StringUtils.split(nums, separator);
        int[] ints = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            ints[i] = Integer.parseInt(ss[i]);
        }
        return ints;
    }

    /**
     * 比较list元素是否一致，忽略顺序
     *
     * @param left  左边List
     * @param right 右边List
     * @param <T>   元素类型
     * @return 是否一致
     */
    public static <T> boolean listEquals(List<T> left, List<T> right) {
        if (left == null) {
            return right == null;
        } else {
            if (right == null) {
                return false;
            }
            if (left.size() != right.size()) {
                return false;
            }

            List<T> ltmp = new ArrayList<>(left);
            List<T> rtmp = new ArrayList<>(right);
            for (T t : ltmp) {
                rtmp.remove(t);
            }
            return rtmp.isEmpty();
        }
    }

    /**
     * 连接集合类为字符串
     *
     * @param collection 集合
     * @param separator  分隔符
     * @return 分隔符连接的字符串
     */
    public static String join(Collection<?> collection, String separator) {
        if (isEmpty(collection)) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Object object : collection) {
            if (object != null) {
                String string = object.toString();
                if (string != null) {
                    sb.append(string).append(separator);
                }
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - separator.length()) : StringUtils.EMPTY;
    }
}
