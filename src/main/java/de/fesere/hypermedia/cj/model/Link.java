package de.fesere.hypermedia.cj.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.net.URI;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Link extends Linkable{

    private final URI href;

    @JsonProperty("prompt")
    private final String prompt;

    @JsonCreator
    public Link(@JsonProperty("rel") String rel, @JsonProperty("href") URI href){
        this(rel, href, null);
    }

    public Link(String rel, URI href, String prompt) {
        super(rel);
        this.href = href;
        this.prompt = prompt;
    }


    public URI getHref() {
        return href;
    }
}
