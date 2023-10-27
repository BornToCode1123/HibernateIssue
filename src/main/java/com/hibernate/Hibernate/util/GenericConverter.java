package com.hibernate.Hibernate.util;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = false)
public class GenericConverter implements AttributeConverter<String, String> {


        @Override
        public String convertToDatabaseColumn(String s) {
        return s;
    }

        @Override
        public String convertToEntityAttribute(String s) {

            return s;

    }
}
