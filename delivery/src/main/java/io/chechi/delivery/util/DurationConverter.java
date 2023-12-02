package io.chechi.delivery.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;

@Converter
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration != null ? duration.toMillis() : null;
    }

    @Override
    public Duration convertToEntityAttribute(Long millis) {
        return millis != null ? Duration.ofMillis(millis) : null;
    }
}
