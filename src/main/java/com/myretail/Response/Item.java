package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

    private String tcin;
    private String dpci;
    private ProductDescription product_description;
    private Float price;
    private String currency;


    public String getTcin() {
        return tcin;
    }

    public void setTcin(String tcin) {
        this.tcin = tcin;
    }

    public String getDpci() {
        return dpci;
    }

    public void setDpci(String dpci) {
        this.dpci = dpci;
    }

    public ProductDescription getProduct_description() {
        return product_description;
    }

    public void setProduct_description(ProductDescription product_description) {
        this.product_description = product_description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tcin='" + tcin + '\'' +
                ", dpci='" + dpci + '\'' +
                ", product_description=" + product_description +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
