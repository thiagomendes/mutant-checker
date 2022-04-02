package br.com.mutant.checker.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.support.NoOpCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfigTest {

    @Bean
    public CacheManager getNoOpCacheManager() {
        return new NoOpCacheManager();
    }
}
