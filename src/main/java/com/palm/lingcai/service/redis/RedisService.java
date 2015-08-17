package com.palm.lingcai.service.redis;

/**
 *
 * Redis操作服务类
 *
 * Created by jianhe on 14-5-8.
 */

import java.util.Collection;
import java.util.Set;

/**
 * redis 的服务接口
 */
public interface RedisService {
    /**
     * <pre>
     * 通过key删除
     *
     * @param keys
     * @return 被删除的记录数
     * </pre>
     */
    public long delete(String... keys);

    /**
     * <pre>
     * 通过keys删除
     *
     * @param keys
     * @return 被删除的记录数
     * </pre>
     */
    public long delete(Collection<String> keys);

    /**
     * <pre>
     *  @param key
     *  @param value
     *  @param activeTime 秒
     *  @return 添加key value 并且设置存活时间
     * </pre>
     */
    public boolean set(byte[] key, byte[] value, long activeTime);

    /**
     * <pre>
     *
     * @param key
     * @param value
     * @param activeTime 秒
     * @return 添加key value 并且设置存活时间
     * </pre>
     */
    public boolean set(String key, String value, long activeTime);

    /**
     * <pre>
     *  @param key
     *  @param value
     *  @return 添加key value
     * </pre>
     */
    public boolean set(String key, String value);

    /**
     * <pre>
     *  @param key
     *  @param value
     *  @return 添加key value
     * </pre>
     */
    public boolean set(byte[] key, byte[] value);

    /**
     * <pre>
     *
     * @param key
     * @return 获得value
     * </pre>
     */
    public String get(String key);

    /**
     * <pre>
     *
     * @param pattern
     * @return 通过正则匹配keys
     * </pre>
     */
    public Set<String> matchKeys(String pattern);

    /**
     * <pre>
     *
     * @param key
     * @return 检查key是否已经存在
     * </pre>
     */
    public boolean exists(String key);

    /**
     * <pre>
     *
     * @return 清空所有数据
     * </pre>
     */
    public boolean flushDB();

    /**
     * 加1
     *
     * @param key
     * @return
     */
    public Long incr(String key);

    /**
     * 减1
     *
     * @param key
     * @return
     */
    public Long decr(String key);

    /**
     * Hashset结构的set
     *
     * @param name
     * @param key
     * @param value
     * @return
     */
    public boolean setHash(String name, String key, String value);

    /**
     * Hashset结构的get
     *
     * @param name
     * @param key
     * @return
     */
    public String getHash(String name, String key);

    /**
     * Hashset结构的长度
     *
     * @param name
     * @return
     */
    public Long hashLen(String name);

    /**
     * 获取Hashset结构的所有Key
     *
     * @param name
     * @return
     */
    public Set<byte[]> getHashKeys(String name);


}

