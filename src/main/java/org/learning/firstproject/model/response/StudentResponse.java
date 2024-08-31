package org.learning.firstproject.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.learning.firstproject.model.entities.Student;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StudentResponse<T> {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    private Timestamp timestamp;
    private HttpStatus status;
}
