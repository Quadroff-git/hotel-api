package org.pileka.hotel_api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.pileka.hotel_api.dto.HistogramDTO;
import org.springframework.boot.jackson.JacksonComponent;

import java.io.IOException;
import java.util.List;

/**
 * Custom serialization logic for histogram responses
 */
@JacksonComponent
public class HistogramSerializer extends JsonSerializer<List<HistogramDTO>> {
    @Override
    public void serialize(List<HistogramDTO> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for (HistogramDTO item : value) {
            gen.writeNumberField(item.getColumnValue(), item.getCount());
        }
        gen.writeEndObject();
    }
}
