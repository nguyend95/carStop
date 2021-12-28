package eu.epptec.carStop.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringToLongConverter implements AttributeConverter<String, Long> {
    @Override
    public Long convertToDatabaseColumn(String attribute) {
        return Long.getLong(attribute);
    }

    @Override
    public String convertToEntityAttribute(Long dbData) {
        return String.valueOf(dbData);
    }
}
