package com.example.julian.test.serviceApi.common.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseBody implements BaseResponseBody {

    private static final String MESSAGE_ERROR_KEY = "message";
    private static final String HTTP_STATUS_DESCRIPTION = "description";
    private static final String TIME_STAMP_VALUE = "timeStamp";

    @JsonProperty(value = MESSAGE_ERROR_KEY, required = true)
    @NotNull
    private final String message;

    @JsonProperty(value = HTTP_STATUS_DESCRIPTION, required = true)
    @NotNull
    private final HttpStatus status;

    @JsonProperty(value = TIME_STAMP_VALUE, required = true)
    @JsonFormat(pattern = DATE_FORMAT)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private final OffsetDateTime dateTime;

    @JsonCreator
    public ErrorResponseBody(
            @JsonProperty(value = MESSAGE_ERROR_KEY, required = true)
            @NotNull final String message,
            @JsonProperty(value = HTTP_STATUS_DESCRIPTION, required = true)
            @NotNull final HttpStatus status,
            @JsonProperty(value = TIME_STAMP_VALUE, required = true)
            @JsonFormat(pattern = DATE_FORMAT)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @NotNull final OffsetDateTime dateTime
    ) {

        this.message = message;
        this.status = status;
        this.dateTime = dateTime;
    }

}
