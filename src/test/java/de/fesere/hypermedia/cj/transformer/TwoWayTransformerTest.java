package de.fesere.hypermedia.cj.transformer;

import de.fesere.hypermedia.cj.model.Item;
import org.junit.Test;

//For sake of consistency
public class TwoWayTransformerTest {
    private DummyTransformer t =  new DummyTransformer();

    @Test(expected = IllegalStateException.class)
    public void testConvertToItem() throws Exception {
        t.transform("foo");
    }

    @Test(expected = IllegalStateException.class)
    public void testConvertFromItem() throws Exception {
        t.transform(new Item());
    }

    private class DummyTransformer extends TwoWayTransformer<String> {
    }

}
