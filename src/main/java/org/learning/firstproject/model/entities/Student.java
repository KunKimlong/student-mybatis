package org.learning.firstproject.model.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    private int id;
    private String name;
    private String gender;
    private float score1;
    private float score2;
    private float score3;
    private float total;
}
