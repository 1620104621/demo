package com.ats.qzj.atschapter2.model;

public class SettleEntity {
    Long  id;
    String card_number ;
    String card_scheme;
    String card_product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_scheme() {
        return card_scheme;
    }

    public void setCard_scheme(String card_scheme) {
        this.card_scheme = card_scheme;
    }

    public String getCard_product() {
        return card_product;
    }

    public void setCard_product(String card_product) {
        this.card_product = card_product;
    }

    @Override
    public String toString() {
        return "SettleEntity{" +
                "id=" + id +
                ", card_number='" + card_number + '\'' +
                ", card_scheme='" + card_scheme + '\'' +
                ", card_product='" + card_product + '\'' +
                '}';
    }
}
