package com.myretail.Configuration;

import com.myretail.Model.ItemPrice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.Map;

@Configuration
@EnableRedisRepositories
@ComponentScan("com.myretail")
public class ApplicationConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean(name="itemRedisTemplate")
    public RedisTemplate<String, Map<String,ItemPrice>> redisTemplate() {
        RedisTemplate<String, Map<String,ItemPrice>> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(template.getStringSerializer());
        template.setHashKeySerializer(template.getStringSerializer());
        template
                .setHashValueSerializer(new Jackson2JsonRedisSerializer<ItemPrice>(
                        ItemPrice.class));
        template.afterPropertiesSet();
        return template;
    }

}