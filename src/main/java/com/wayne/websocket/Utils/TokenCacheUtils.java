package com.wayne.websocket.Utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//本地缓存=手机验证码300秒后失效=页面显示90跑秒
public class TokenCacheUtils {
	private static Logger logger = LoggerFactory.getLogger(TokenCacheUtils.class);
	
	// LRU算法--本地缓存
	private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000)
			.maximumSize(10000).expireAfterAccess(300, TimeUnit.SECONDS).build(new CacheLoader<String, String>() {
	// 默认的数据加载实现,当调用get取值的时候,如果key没有对应的值,就调用这个方法进行加载.
	public String load(String s) throws Exception {return "null";}});
	
	//设置缓存
	public static void setKey(String key, String value) {
		localCache.put(key, value);
	}
	//清楚缓存
	public static void  CleanRedis() {
		localCache.cleanUp();
	}
	
	//从缓存取值
	public static String getKey(String key) {
		String value = null;
		try {
			value = localCache.get(key);
			if ("null".equals(value)) {
				return null;
			}
			return value;
		} catch (Exception e) {
			logger.error("localCache get error", e);
		}
		return null;
	}
}