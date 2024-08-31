package org.learning.firstproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String name;
    private String gender;
    private float score1;
    private float score2;
    private float score3;
    private float total;
}
