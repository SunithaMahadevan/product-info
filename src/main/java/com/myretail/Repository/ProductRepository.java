package com.myretail.Repository;

import com.myretail.Response.Item;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    Item getPrice(String tcin);
}