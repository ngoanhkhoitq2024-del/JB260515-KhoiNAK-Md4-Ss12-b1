package re.edu.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import re.edu.productservice.entity.Product;

import java.time.Duration;

@Configuration
public class RedisConfig {
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        JacksonJsonRedisSerializer<Product> serializer =
                new JacksonJsonRedisSerializer<>(Product.class);

        return RedisCacheConfiguration.defaultCacheConfig()
                // Cache tồn tại trong 30 phút
                .entryTtl(Duration.ofMinutes(30))

                // Lưu dữ liệu Product dưới dạng JSON
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(serializer)
                );
    }
}