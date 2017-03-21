package com.car.models;

public enum EnunCarCategory {
    COMPACT("Compact"),
    PICKUP("Pickup"),
    UNKNOWN("");

    private String category;

    EnunCarCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }
}
