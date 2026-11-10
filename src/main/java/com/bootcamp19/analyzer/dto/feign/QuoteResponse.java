package com.bootcamp19.analyzer.dto.feign;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse {
    private long id;
    private String quote;
    private String author;
}
