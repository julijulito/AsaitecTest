package com.example.julian.test.serviceApi.entities;

import com.example.julian.test.serviceApi.common.entities.BaseResponseBody;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInvoice implements BaseResponseBody {

    @JsonProperty(value = "Invoice", required = true)
    private final Map<String, BigDecimal> costs;

    @JsonCreator
    public OrderInvoice(
            @JsonProperty(value = "Invoice", required = true)
            final Map<String, BigDecimal> costs
    ){
     this.costs = costs;
    }
}
