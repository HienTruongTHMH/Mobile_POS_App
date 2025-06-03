package com.midterm.pos_app;

public class CoffeeItem {
    private String name;
    private String price;
    private String imageUrl; // Changed from int imageResource
    private boolean isFavorite;
    private String selectedSize;
    private String[] availableSizes;

    public CoffeeItem(String name, String price, String imageUrl, boolean isFavorite, // Changed constructor
                      String selectedSize, String[] availableSizes) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl; // Assign imageUrl
        this.isFavorite = isFavorite;
        this.selectedSize = selectedSize;
        this.availableSizes = availableSizes;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() { // Changed getter
        return imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public String getSelectedSize() {
        return selectedSize;
    }

    public String[] getAvailableSizes() {
        return availableSizes;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) { // Changed setter
        this.imageUrl = imageUrl;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setSelectedSize(String selectedSize) {
        this.selectedSize = selectedSize;
    }

    public void setAvailableSizes(String[] availableSizes) {
        this.availableSizes = availableSizes;
    }
}