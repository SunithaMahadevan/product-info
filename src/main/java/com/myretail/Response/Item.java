package com.myretail.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private int tcin;
    private String dpci;
    private ProductDescription product_description;

    public int getTcin() {
        return tcin;
    }

    public void setTcin(int tcin) {
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


    @Override
    public String toString() {
        return "Item{" +
                "tcin=" + tcin +
                ", dpci='" + dpci + '\'' +
                ", product_description=" + product_description +
                '}';
    }


}
