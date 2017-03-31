package com.car.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;


@Getter
@Builder
public class CarBodyModel {

    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    public CarBodyModel(String model,
                        String year,
                        String collor,
                        EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }

    public CarBodyModel() {
    }

    public JSONObject toJson() throws JsonProcessingException {
        return new JSONObject(new ObjectMapper().writeValueAsString(this));
    }
}
