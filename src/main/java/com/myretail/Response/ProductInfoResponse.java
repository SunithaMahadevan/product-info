package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfoResponse {

    private Product product;
    private String error_message;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "ProductInfoResponse{" +
                "product=" + product +
                ", error_message='" + error_message + '\'' +
                '}';
    }


}
