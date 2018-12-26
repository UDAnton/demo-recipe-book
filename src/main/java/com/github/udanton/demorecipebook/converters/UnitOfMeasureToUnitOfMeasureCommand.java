package com.github.udanton.demorecipebook.converters;

import com.github.udanton.demorecipebook.commands.UnitOfMeasureCommand;
import com.github.udanton.demorecipebook.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure == null) {
            return null;
        }

        final UnitOfMeasureCommand unitOfMeasureCommand1 = new UnitOfMeasureCommand();
        unitOfMeasureCommand1.setId(unitOfMeasure.getId());
        unitOfMeasureCommand1.setDescription(unitOfMeasure.getDescription());

        return unitOfMeasureCommand1;
    }
}
