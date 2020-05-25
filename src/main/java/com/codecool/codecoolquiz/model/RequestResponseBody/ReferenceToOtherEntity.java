package com.codecool.codecoolquiz.model.RequestResponseBody;

import lombok.Data;

@Data
public class ReferenceToOtherEntity {

    Integer id;
    String name;

    public ReferenceToOtherEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
