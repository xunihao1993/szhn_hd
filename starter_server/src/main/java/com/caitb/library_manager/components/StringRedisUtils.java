package com.caitb.library_manager.components;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.caitb.library_manager.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 功能描述: Redis 工具类 只支持文本类型的 key 和 value
 * <p>
 */
@Component
public class StringRedisUtils {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations<String, String> operations;

    private HashOperations<String, String, String> hashOperations;

    private SetOperations<String, String> setOperations;

    public ValueOperations<String, String> getOperations() {
        if (operations == null) {
            operations = stringRedisTemplate.opsForValue();
        }
        return operations;
    }

    public HashOperations<String, String, String> getHashOperations() {
        if (hashOperations == null) {
            hashOperations = stringRedisTemplate.opsForHash();
        }
        return hashOperations;
    }

    public SetOperations<String, String> getSetOperations() {
        if (setOperations == null) {
            setOperations = stringRedisTemplate.opsForSet();
        }
        return setOperations;
    }

    public long increment(String key) {
        return increment(key, 1);
    }

    public long increment(String key, long delta) {
        Long results = getOperations().increment(key, delta);
        return results != null ? results : 0L;
    }

    /**
     * 批量删除对应的value
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     */
    public void removePattern(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            stringRedisTemplate.delete(keys);
        }
    }

    /**
     * 删除对应的value
     */
    public void remove(String key) {
        if (exists(key)) {
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     */
    public boolean exists(String key) {
        Boolean results = stringRedisTemplate.hasKey(key);
        return results != null ? results : false;
    }

    public boolean ZSetExists(String key, String value) {
        return ZSetScore(key, value) != null;
    }

    public boolean hasKey(String key) {
        Boolean results = stringRedisTemplate.hasKey(key);
        return results != null ? results : false;
    }

    /**
     * 读取缓存
     */
    public String get(String key) {
        return getOperations().get(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        String val = get(key);
        return StringUtils.isNotBlank(val) ? JSON.parseObject(val, new TypeReference<T>(){}) : null;
    }

    public int getIntValue(String key, int defaultValue) {
        return parseInt(get(key), defaultValue);
    }

    public long getLongValue(String key, long defaultValue) {
        return parseLong(get(key), defaultValue);
    }

    public BigDecimal getBigDecimalValue(String key, BigDecimal defaultValue) {
        return CommonUtils.parseBigDecimal(get(key), defaultValue);
    }

    /**
     * 写入缓存
     */
    public boolean set(String key, String value) {
        try {
            getOperations().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存
     */
    public boolean set(String key, Map<String, String> map) {
        try {
            getHashOperations().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存
     */
    public boolean set(String key, String value, Long timeout) {
        return set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 写入缓存
     */
    public boolean set(String key, String value, Long timeout, TimeUnit unit) {
        try {
            getOperations().set(key, value, timeout, unit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 写入缓存
     */
    public boolean set(String key, String field, String value) {
        try {
            getHashOperations().put(key, field, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        Boolean results = stringRedisTemplate.expire(key, timeout, timeUnit);
        return results != null ? results : false;
    }

    public long getExpire(String key) {
        Long results = stringRedisTemplate.getExpire(key);
        return results != null ? results : 0L;
    }

    /**
     * Redis hash操作
     */
    public String getHashValue(String key, String hashKey) {
        return getHashOperations().get(key, hashKey);
    }

    public boolean putHashValue(String key, String hashKey, String value) {
        try {
            getHashOperations().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean putHashValues(String key, Map<String, String> map) {
        try {
            getHashOperations().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasHashKey(String key, String hashKey) {
        return getHashOperations().hasKey(key, hashKey);
    }

    public void deleteHashValue(String key, String hashKey) {
        getHashOperations().delete(key, hashKey);
    }

    public boolean delete(String key) {
        Boolean results = stringRedisTemplate.delete(key);
        return results != null && results;
    }

    public long delete(Collection<String> keys) {
        Long results = stringRedisTemplate.delete(keys);
        return results == null ? 0L : results;
    }

    public int getHashKeyIntValue(String key, String field) {
        return getHashKeyIntValue(key, field, 0);
    }

    public int getHashKeyIntValue(String key, String field, int defaultValue) {
        return parseInt(getHashKeyValue(key, field), defaultValue);
    }

    public long getHashKeyLongValue(String key, String field) {
        return getHashKeyLongValue(key, field, 0);
    }

    public long getHashKeyLongValue(String key, String field, long defaultValue) {
        return parseLong(getHashKeyValue(key, field), defaultValue);
    }

    public BigDecimal getHashKeyBigDecimalValue(String key, String field) {
        return getHashKeyBigDecimalValue(key, field, BigDecimal.ZERO);
    }

    public BigDecimal getHashKeyBigDecimalValue(String key, String field, BigDecimal defaultValue) {
        return CommonUtils.parseBigDecimal(getHashKeyValue(key, field), defaultValue);
    }

    public String getHashKeyValue(String key, String field) {
        return getHashKeyValue(key, field, null);
    }

    public String getHashKeyValue(String key, String field, String defaultValue) {
        String value = getHashValue(key, field);
        return value != null ? value : defaultValue;
    }

    public void setHashKeyValue(String key, String field, int data) {
        setHashKeyValue(key, field, String.valueOf(data));
    }

    public void setHashKeyValue(String key, String field, String data) {
        long seconds = getExpire(key);
        set(key, field, data);
        if (seconds > 0) {
            expire(key, seconds, TimeUnit.SECONDS);
        }
    }

    public void setHashKeyValue(String key, String field, String data, long timeout, TimeUnit timeUnit) {
        // 设置值
        set(key, field, data);
        // 判断是否需要过期
        if (timeout > 0 && timeUnit != null) {
            // 获取过期时间
            long expire = getExpire(key);
            if (expire == -1) {
                // 设置过期时间
                expire(key, timeout, timeUnit);
            }
        }
    }

    private int parseInt(String value, int defaultValue) {
        try {
            return value != null ? Integer.parseInt(value) : defaultValue;
        } catch (Exception e) {
            System.out.println("parse int string exception: " + e.getMessage());
        }
        return defaultValue;
    }

    private static long parseLong(String value, long defaultValue) {
        try {
            return value != null ? Long.parseLong(value) : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public int getSetSize(String key) {
        Long results = getSetOperations().size(key);
        return results != null ? results.intValue() : 0;
    }

    /**
     * 将数据放入set缓存
     *
     * @param key   键
     * @param value 值
     */
    public void setAdd(String key, long value) {
        getSetOperations().add(key, String.valueOf(value));
    }

    public void setAdd(String key, String value) {
        getSetOperations().add(key, value);
    }

    public void setAdd(String key, long value, long timeout, TimeUnit timeUnit) {
        setAdd(key, String.valueOf(value), timeout, timeUnit);
    }

    public void setAdd(String key, String value, long timeout, TimeUnit timeUnit) {
        // 如果存在 直接返回
        if (isSetMember(key, value)) return;

        // 添加到Set集合中
        setAdd(key, value);

        // 判断是否需要过期
        if (timeout > 0 && timeUnit != null) {
            // 获取过期时间
            long expire = getExpire(key);
            if (expire == -1) {
                // 设置过期时间
                expire(key, timeout, timeUnit);
            }
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean isSetMember(String key, long value) {
        return isSetMember(key, String.valueOf(value));
    }

    public boolean isSetMember(String key, String value) {
        Boolean isMember = getSetOperations().isMember(key, value);
        return isMember != null && isMember;
    }

    /**
     * 获取符合条件的key
     *
     * @param pattern 表达式
     * @return 符合条件的KEY列表
     */
    public Set<String> doScanByConsumer(String pattern, final long count) {
        if (StringUtils.isBlank(pattern) || count <= 0) {
            return Collections.emptySet();
        }
        Set<String> keys = new HashSet<>();
        scanByConsumer(pattern, item -> {
            // 符合条件的key
            String key = new String(item, StandardCharsets.UTF_8);
            keys.add(key);
        }, count);
        return keys;
    }

    /**
     * scan 实现
     *
     * @param pattern  表达式
     * @param consumer 对迭代到的key进行操作
     */
    public void scanByConsumer(String pattern, Consumer<byte[]> consumer, final long count) {
        stringRedisTemplate.execute((RedisConnection connection) -> {
            ScanOptions scanOptions = ScanOptions.scanOptions().count(count).match(pattern.endsWith("*") ? pattern : pattern + "*").build();
            try (Cursor<byte[]> cursor = connection.scan(scanOptions)) {
                cursor.forEachRemaining(consumer);
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Set<String> doScan(final String pattern, final long count) {
        if (StringUtils.isBlank(pattern) || count <= 0) {
            return Collections.emptySet();
        }
        return stringRedisTemplate.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> keys = new HashSet<>();
            // 扫描的参数
            ScanOptions scanOptions = ScanOptions.scanOptions().count(count).match(pattern.endsWith("*") ? pattern : pattern + "*").build();
            // 放在try中自动释放cursor
            try (Cursor<byte[]> cursor = connection.scan(scanOptions)) {
                while (cursor.hasNext()) {
                    keys.add(new String(cursor.next()));
                }
            } catch (IOException ignored) {
            }
            return keys;
        });
    }

    public long doScanAndDel(final String pattern, final long count) {
        if (StringUtils.isBlank(pattern) || count <= 0) {
            return 0L;
        }
        Set<String> keys = doScan(pattern, count);
        if (keys.isEmpty()) {
            return 0L;
        }
        List<String> items = new ArrayList<>(keys);
        int maxSize = keys.size();
        int pageSize = 3000;
        int loopNum = maxSize / pageSize + ((maxSize % pageSize == 0) ? 0 : 1);
        long deleteNum = 0;
        for (int i = 0; i < loopNum; i++) {
            int fromIndex = i * pageSize;
            int toIndex = (i + 1) * pageSize;
            if (toIndex > maxSize) {
                toIndex = maxSize;
            }
            deleteNum += delete(items.subList(fromIndex, toIndex));
        }
        return deleteNum;
    }

    /**
     * 添加一个元素, zset与set最大的区别就是每个元素都有一个score，因此有个排序的辅助功能;  zadd
     *
     * @param key
     * @param value
     * @param score
     */
    public void ZSetAdd(String key, String value, double score) {
        stringRedisTemplate.opsForZSet().add(key, value, score);
    }

    public void ZSetAdd(String key, Set<ZSetOperations.TypedTuple<String>> tuples) {
        stringRedisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * 删除元素 zrem
     *
     * @param key
     * @param value
     */
    public void ZSetRemove(String key, String value) {
        stringRedisTemplate.opsForZSet().remove(key, value);
    }

    public void ZSetRemoveRange(String key, long start, long end) {
        stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }

    /**
     * score的增加or减少 zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public Double ZSetIncrScore(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 查询value对应的score   zscore
     *
     * @param key
     * @param value
     * @return
     */
    public Double ZSetScore(String key, String value) {
        return stringRedisTemplate.opsForZSet().score(key, value);
    }

    /**
     * 判断value在zset中的排名  zrank
     * <p>
     * 积分小的在前面
     *
     * @param key
     * @param value
     * @return
     */
    public Long ZSetRank(String key, String value) {
        return stringRedisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     * <p>
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> ZSetRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值
     * <p>
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> ZSetRevRange(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> ZSetRangeWithScore(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> ZSetRevRangeWithScore(String key, long start, long end) {
        return stringRedisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    /**
     * 根据score的值，来获取满足条件的集合
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> ZSetSortRange(String key, long min, long max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 返回集合的长度
     *
     * @param key
     * @return
     */
    public Long ZSetSize(String key) {
        Long val = stringRedisTemplate.opsForZSet().zCard(key);
        return val == null ? 0 : val;
    }

    public Map<String, Double> ZSetBatchScore(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.execute((RedisCallback<Map<String, Double>>) redisConnection -> {
            StringRedisConnection stringRedisConnection = (StringRedisConnection) redisConnection;
            Map<String, Double> resultMap = new ConcurrentHashMap<>();
            for (String item : otherKeys) {
                resultMap.put(item, stringRedisConnection.zScore(key, item));
            }
            return resultMap;
        });
    }
}
