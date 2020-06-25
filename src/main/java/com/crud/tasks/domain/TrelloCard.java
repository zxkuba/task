package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
