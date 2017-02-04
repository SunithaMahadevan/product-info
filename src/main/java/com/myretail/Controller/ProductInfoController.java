package com.myretail.Controller;

import com.myretail.Response.ProductDescriptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController

public class ProductInfoController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoController.class);

    RestTemplate restTemplate = new RestTemplate();

    ProductDescriptionResponse productDescriptionResponse = restTemplate.getForObject("http://redsky.target.com/v1/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", ProductDescriptionResponse.class);

    LOGGER.
}
