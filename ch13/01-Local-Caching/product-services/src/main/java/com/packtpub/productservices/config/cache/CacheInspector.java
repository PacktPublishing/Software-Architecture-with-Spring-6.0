package com.packtpub.productservices.config.cache;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheInspector implements CommandLineRunner {

    private final CacheManager cacheManager;

    public CacheInspector(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void run(String... args) throws Exception {
        Cache cache = cacheManager.getCache("products");
        if (cache != null) {
            System.out.println("Cache initialized successfully!");
        } else {
            System.out.println("Cache 'products' is not available!");
        }
    }
}