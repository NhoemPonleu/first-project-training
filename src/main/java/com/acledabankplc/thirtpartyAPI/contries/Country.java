package com.acledabankplc.thirtpartyAPI.contries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Country {

    private Name name;

    @JsonProperty("flags")
    private Flags flags;

    @Data
    public static class Name {
        private String official;
        private String common;
    }

    @Data
    public static class Flags {
        private String png;
        private String svg;
    }
}
