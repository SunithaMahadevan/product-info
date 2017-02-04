package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescriptionResponse {

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    @Override
    public String toString() {
        return "ProductDescriptionResponse{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                '}';
    }

    private Integer product_id;
    private String product_name;


}
