package de.fesere.hypermedia.cj.model.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.fesere.hypermedia.cj.serialization.DataEntryDeserializer;
import de.fesere.hypermedia.cj.serialization.DataEntrySerializer;
import org.apache.commons.lang3.StringUtils;

@JsonSerialize(using = DataEntrySerializer.class)
@JsonDeserialize(using = DataEntryDeserializer.class)
abstract public class DataEntry<T> {

    @JsonProperty("name")
    private final String name;

    private String prompt;

    public DataEntry(String name) {
        this(name, null);
    }

    public DataEntry(String name, String prompt) {
        this.name = name;
        this.prompt = prompt;
    }

    public String getName() {
        return name;
    }

    public String getPrompt() {
        return prompt;
    }

    public abstract void set(T value);

    public void clear() {
        set(null);
    }

    public abstract T getValue();

    public String buildQueryRepresentation() {
        T value = getValue();

        if (value != null && StringUtils.isNotBlank(value.toString())) {
            return getName() + "=" + value.toString();
        }
        return "";
    }
}
