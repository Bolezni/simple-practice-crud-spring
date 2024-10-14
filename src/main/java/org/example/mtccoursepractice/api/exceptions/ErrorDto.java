package org.example.mtccoursepractice.api.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {

    String error;

    @JsonProperty("error_time")
    Instant errorTime;

    @JsonProperty("error_description")
    String errorDescription;
}
