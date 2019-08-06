package com.github.udanton.demorecipebook.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"})
public class Notes {
    private String id;
    private Recipe recipe;
    private String recipeNotes;
}
