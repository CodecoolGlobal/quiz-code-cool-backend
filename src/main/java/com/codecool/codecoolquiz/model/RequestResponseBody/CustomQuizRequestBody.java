package com.codecool.codecoolquiz.model.RequestResponseBody;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomQuizRequestBody {
    private String name;
    private int[] questionIds;
}
