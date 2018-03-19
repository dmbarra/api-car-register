package com.car.models;

public enum EnunCarCategory {
    COMPACT("Compact"),
    PICKUP("Pickup");

    private String category;

    EnunCarCategory(String category) {
        this.category = category;
    }
}
