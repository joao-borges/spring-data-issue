package com.joaoborges.test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

/**
 * Config for the jackson xml/json object mapper
 */
@Configuration
public class JacksonConfiguration {

    private static final DateTimeFormatter ISO_DATE_OPTIONAL_TIME = new DateTimeFormatterBuilder()
        .append(DateTimeFormatter.ISO_LOCAL_DATE)
        .optionalStart()
        .appendLiteral('T')
        .append(DateTimeFormatter.ISO_TIME)
        .toFormatter();

    @Bean
    public Jackson2ObjectMapperBuilder filteringObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.failOnEmptyBeans(false);

        builder
            .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(ISO_DATE_OPTIONAL_TIME))
            .deserializerByType(LocalDate.class, new LocalDateTimeDeserializer(ISO_DATE_OPTIONAL_TIME))
            .deserializerByType(String.class, new EmptyToNullStringDeserializer());

        return builder;
    }

    private static class EmptyToNullStringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jp,
                                  DeserializationContext ctxt) throws
                                                               IOException {
            String text = jp.getText();
            return "".equals(text) ? null : text;
        }
    }
}
