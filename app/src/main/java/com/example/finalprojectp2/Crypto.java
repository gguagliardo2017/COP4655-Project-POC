package com.example.finalprojectp2;

public class Crypto {
    private String symbol;
    private String name;
    private String iconUrl;
    private String price;
    private String change;

    public Crypto(String symbol, String name, String iconUrl, String price, String change) {
        this.symbol = symbol;
        this.name = name;
        this.iconUrl = iconUrl;
        this.price = price;
        this.change = change;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }


}
