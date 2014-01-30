package de.fesere.hypermedia.cj.model.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperConfig {

    public ObjectMapper getConfiguredObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new WrapperModule());

        return mapper;
    }

    public static class WrapperModule extends SimpleModule {
        public WrapperModule() {
            addSerializer(Wrapper.class, new WrapperSerializer());
            addDeserializer(Wrapper.class, new WrapperDeserializer());
        }
    }
}
