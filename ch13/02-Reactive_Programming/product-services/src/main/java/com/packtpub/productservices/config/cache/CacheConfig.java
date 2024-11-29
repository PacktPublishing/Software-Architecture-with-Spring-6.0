package com.packtpub.productservices.config.cache;

//import com.github.benmanes.caffeine.cache.Caffeine;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("products", "productsById");
//    }

//    @Bean
//    public CacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager("products", "productsById");
//        cacheManager.setCaffeine(Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES) // Cache expires after 10 minutes
//                .maximumSize(100)); // Maximum cache size
//        return cacheManager;
//    }


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        return Redisson.create(); // Loads default redisson.yaml from classpath
    }

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        return new RedissonSpringCacheManager(redissonClient);
    }
}
