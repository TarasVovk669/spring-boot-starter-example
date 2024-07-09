package com.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hotel {

    private String name;
    private String rating;
    private String countryCode;
}
