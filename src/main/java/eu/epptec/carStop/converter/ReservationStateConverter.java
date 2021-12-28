package eu.epptec.carStop.converter;

import eu.epptec.carStop.enums.ReservationStates;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ReservationStateConverter implements AttributeConverter<ReservationStates, String> {

    @Override
    public String convertToDatabaseColumn(ReservationStates attribute) {
        return attribute.getState();
    }

    @Override
    public ReservationStates convertToEntityAttribute(String dbData) {
        return ReservationStates.fromStringState(dbData);
    }
}
