package com.example.kompc;

public class Purchase {
    private String productId;
    private String productName;
    private String price;
    private String purchaseDate;
    private String userEmail;

    public Purchase() {

    }

    public Purchase(String productId, String productName, String price, String purchaseDate, String userEmail) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.userEmail = userEmail;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
