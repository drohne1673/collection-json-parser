package de.fesere.hypermedia.cj.model.builder;

import de.fesere.hypermedia.cj.model.*;
import org.junit.Test;

import java.net.URI;
import java.util.Arrays;

public class TemplateBuilderTest extends SerializationTestBase {

    @Test
    public void test_generatePrefilledTemplateFromItem() {
        String expectedJson = readFile("/examples/builder/filled-template.json");

        Item item = new Item(Arrays.asList(new DataEntry("name", "foo", "Full Name"), new DataEntry("city", "karlsruhe", "City")));

        Template template = new TemplateBuilder().filledFromItem(item).build();

        Collection collection = new CollectionBuilder(URI.create("http://foo")).addTemplate(template).build();

        assertCollectionSerialization(expectedJson, collection);

    }

    @Test
    public void test_generateEmptyTemplateFromItem() {
        String expectedJson = readFile("/examples/builder/empty-template.json");

        Item item = new ItemBuilder(URI.create("http://foo")).addData(new DataEntry("name", "foo", "Full Name"))
                                                              .addData(new DataEntry("city", "karlsruhe", "City")).build();
        Template template = new TemplateBuilder().emptyFromItem(item).build();

        Collection collection = new CollectionBuilder(URI.create("http://foo")).addTemplate(template).build();

        assertCollectionSerialization(expectedJson, collection);
    }

    @Test
    public void test_generateEmptyTemplateUsingNames() {
        String expectedJson = readFile("/examples/builder/empty-template.json");

        Template template = new TemplateBuilder().emptyWithNames("name", "city").build();

        Collection collection = new CollectionBuilder(URI.create("http://foo")).addTemplate(template).build();
        assertCollectionSerialization(expectedJson, collection);
    }
}
