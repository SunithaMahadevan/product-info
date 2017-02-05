package com.myretail.Controller;


import com.myretail.Response.ProductInfoConsumer;
import com.myretail.Service.ProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/product_info/v1")


public class ProductInfoController {

    @Autowired
    ProductInfoService productInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInfoController.class);

    @ResponseBody
    @RequestMapping (value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductInfoConsumer getProductData (@PathVariable("id") Integer id)  {

        ProductInfoConsumer productInfoConsumer = productInfoService.getProductDescription(id);

        if (productInfoConsumer.getError_message() != null && productInfoConsumer.getError_message().contains("403")) {
                productInfoConsumer.setError_message("403 Forbidden");
        }

        return productInfoConsumer;

    }

}
