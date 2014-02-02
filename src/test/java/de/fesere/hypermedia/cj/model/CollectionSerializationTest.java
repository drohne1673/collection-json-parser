package de.fesere.hypermedia.cj.model;

import de.fesere.hypermedia.cj.exceptions.ElementNotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.util.Arrays;

public class CollectionSerializationTest extends SerializationTestBase {

    private static final URI TEST_COM = URI.create("http://test.com");

    @Test
    public void testPOJOtoJSON() {
        Collection collection = new Collection(TEST_COM);

        assertCollectionSerialization("{\"collection\":{\"href\":\"http://test.com\",\"version\":\"1.0\"}}", collection);
    }


    @Test
    public void testJSONtoPOJO() {
        String givenJson = "{\"collection\":{\"href\":\"http://test.com\",\"version\":\"1.0\"}}";

        Collection collection = deserializeCollection(givenJson);

        Assert.assertEquals("1.0", collection.getVersion());
        Assert.assertEquals(TEST_COM, collection.getHref());
    }

    @Test
    public void serializeCollectionWithSingleItem() {
        Item item = new Item(URI.create("http://test.com/items/1"));
        Collection collection = new Collection(TEST_COM, Arrays.asList(item), null, null, null);

        assertCollectionSerialization("{\"collection\":{\"href\":\"http://test.com\",\"version\":\"1.0\",\"items\":[{\"href\":\"http://test.com/items/1\"}]}}", collection);
    }

    @Test
    public void serializeCollectionWithSingleItemWithData() {
        Item item = new Item(URI.create("http://test.com/items/1"));
        item.addData(new DataEntry("foo", "bar"));

        Collection collection = new Collection(TEST_COM, Arrays.asList(item), null, null, null);

        assertCollectionSerialization("{\"collection\":{" +
                "\"href\":\"http://test.com\"," +
                "\"version\":\"1.0\"," +
                "\"items\":[" +
                "{\"href\":\"http://test.com/items/1\"," +
                "\"data\":[" +
                "{\"name\":\"foo\", \"value\":\"bar\"}" +
                "]" +
                "}" +
                "]" +
                "}" +
                "}", collection);
    }

    @Test(expected = ElementNotFoundException.class)
    public void testCollectionMissingQuery() {
        Collection collection = new Collection(TEST_COM);

        collection.getQuery("doesNotExist");
    }

    @Test(expected = ElementNotFoundException.class)
    public void testCollectionMissingLink() {
        Collection collection = new Collection(TEST_COM);

        collection.getLink("doesNotExist");
    }


}
