package com.myretail.Repository;

import com.myretail.Model.ItemPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    ItemPrice getPrice(String tcin);

    ItemPrice updatePrice(ItemPrice itemPrice, String tcin);
}