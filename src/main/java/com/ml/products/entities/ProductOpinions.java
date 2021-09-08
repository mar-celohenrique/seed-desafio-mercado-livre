package com.ml.products.entities;

import com.ml.opinions.entities.Opinion;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductOpinions {

    private final Set<Opinion> opinions;

    public ProductOpinions(Set<Opinion> opinions) {
        this.opinions = opinions;
    }

    public <T> Set<T> mapOpinions(Function<Opinion, T> mapperFunction) {
        return this.opinions.stream().map(mapperFunction)
                .collect(Collectors.toSet());
    }

    public Double average() {
        Set<Integer> grades = mapOpinions(Opinion::getGrade);
        OptionalDouble average = grades.stream().mapToInt(nota -> nota).average();
        return average.orElse(0.0);
    }

    public int total() {
        return this.opinions.size();
    }

}
