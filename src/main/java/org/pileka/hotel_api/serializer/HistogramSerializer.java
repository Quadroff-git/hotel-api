package org.pileka.hotel_api.serializer;

import org.pileka.hotel_api.dto.HistogramItemDTO;
import org.pileka.hotel_api.dto.HistogramResponseDTO;
import org.springframework.boot.jackson.JacksonComponent;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;


/**
 * Custom serialization logic for histogram responses
 */
@JacksonComponent
public class HistogramSerializer extends ValueSerializer<HistogramResponseDTO> {

    @Override
    public void serialize(HistogramResponseDTO value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        gen.writeStartObject();
        for (HistogramItemDTO item : value.getData()) {
            gen.writeNumberProperty(item.getColumnValue(), item.getCount());
        }
        gen.writeEndObject();
    }
}
