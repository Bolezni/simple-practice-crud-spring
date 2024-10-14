package org.example.mtccoursepractice.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {

    @NonNull
    private String courseName;

    @NonNull
    private String description;
}
