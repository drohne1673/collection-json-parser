package de.fesere.hypermedia;

import junit.framework.Assert;
import org.junit.Test;

import java.net.URI;

public class ItemSerializationTests extends SerializationTestBase {

    private static final URI TEST_COM_ITEM = URI.create("http://test.com/item/1");

    @Test
    public void testPOJOtoJSON() {

        Item classUnderTest = new Item(TEST_COM_ITEM);

        assertSerialization("{\"href\":\"http://test.com/item/1\"}", classUnderTest);
    }

    @Test
    public void testJSONtoPOJO() {
        String givenJSON = "{\"href\":\"http://test.com/item/1\"}";

        Item item = deserialize(givenJSON, Item.class);

        Assert.assertEquals(TEST_COM_ITEM, item.getHref());
    }

    @Test
    public void testSerializeItemWithSingleDataEntry() {

        Item item = new Item(TEST_COM_ITEM);
        item.addData(new DataEntry("foo", "bar"));

        assertSerialization("{\"href\":\"http://test.com/item/1\", \"data\":[{\"name\":\"foo\", \"value\":\"bar\"}]}", item);
    }

}
