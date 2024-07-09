package com.common.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TourOperatorResponse {

    private Map<String,String> metadata;
    private List<TourOffer> deals;
}
