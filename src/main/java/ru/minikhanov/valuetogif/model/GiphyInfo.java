package ru.minikhanov.valuetogif.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


public class GiphyInfo {
    private String id;
    private String url;

    @JsonProperty("data")
    private void unpackNested(Map<String, Object> data) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.id = (String) data.get("id");
        Map<String, Object> images = (Map<String, Object>) data.get("images");
        Map<String, Object> fixedHeight = (Map<String, Object>) images.get("fixed_height_downsampled");
        this.url = (String) fixedHeight.get("url");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
