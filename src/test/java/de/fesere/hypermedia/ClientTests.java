package de.fesere.hypermedia;

import de.fesere.hypermedia.http.DummyHttpClient;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

public class ClientTests {

    @Test
    public void parserCreatesQuery() {
        Collection collection = simpleCollection();

        DummyHttpClient httpClient = new DummyHttpClient();
        httpClient
                .expectGetLinkWith(URI.create("http://test.com/search?name=Max"))
                .returnStringOnGetLink(collectionToString(collection()));

        Client client = new Client(httpClient);
        client.use(collection);


        Query query = client.selectQuery("search").set("name", "Max");

        List<Person> personList = client.follow(query).convert(new Transformation<Person>() {
            @Override
            public Person convert(Item item) {
                Person p = new Person();

                String name = item.getString("name");
                if(name != null) {
                    p.firstname = name;
                }
                String surname = item.getString("surname");
                if(surname != null) {
                    p.lastname = surname;
                }

                return p;
            }
        });

        Assert.assertEquals(1, personList.size());
        Assert.assertEquals("Max", personList.get(0).firstname);
        Assert.assertEquals("Musterman", personList.get(0).lastname);

       // Assert.assertEquals(URI.create("http://test.com/people/1"), responseCollection.getItem(0).getHref());
    }

    private static class Person {
        String firstname;
        String lastname;

        public String toString() {
            return firstname + " " + lastname;
        }
    }


    private Collection simpleCollection() {
        Collection collection = new Collection(URI.create("http://test.com"));
        Query query = new Query(URI.create("http://test.com/search"), "search", Arrays.asList(new DataEntry("name", ""), new DataEntry("surname", "")));
        collection.setQueries(Arrays.asList(query));
        return collection;
    }

    private Collection collection() {
        Collection c = new Collection(URI.create("http://test.com"));
        Item item = new Item(URI.create("http://test.com/people/1"), null);

        item.addData(new DataEntry("name", "Max"));
        item.addData(new DataEntry("surname", "Musterman"));

        c.addItem(item);
        return c;
    }

    private String collectionToString(Collection c) {
        ObjectMapper mapper = new ObjectMapper();

        StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return sw.toString();
    }


}
