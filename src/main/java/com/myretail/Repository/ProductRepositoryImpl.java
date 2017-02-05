package com.myretail.Repository;

import com.myretail.Response.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private static final String KEY = "Item";

    private RedisTemplate<String, Item> redisTemplate;
    private HashOperations hashOperations;

    public ProductRepositoryImpl(RedisTemplate<String, Item> redisTemplate, HashOperations hashOperations) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = hashOperations;
    }


    @Autowired
    private ProductRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public Item getPrice(String tcin) {

    return (Item) hashOperations.get(KEY, tcin);
    }

}
