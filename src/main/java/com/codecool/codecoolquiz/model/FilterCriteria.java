package com.codecool.codecoolquiz.model;

import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class FilterCriteria {

    private List<Pair<String, String>> filters;

    @SafeVarargs
    public final void setFilters(Pair<String, String>... parameters) {
        this.filters = Arrays.asList(parameters);
    }

    public List<Pair<String, String>> getFilters() {
        return filters;
    }
}
