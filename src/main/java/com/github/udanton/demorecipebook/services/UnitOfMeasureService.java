package com.github.udanton.demorecipebook.services;

import com.github.udanton.demorecipebook.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> units();
}
