package de.fesere.hypermedia.cj.model.builder;

import de.fesere.hypermedia.cj.model.data.DataEntry;
import de.fesere.hypermedia.cj.model.Item;
import de.fesere.hypermedia.cj.model.data.StringDataEntry;
import de.fesere.hypermedia.cj.model.Template;

import java.util.LinkedList;
import java.util.List;

public class TemplateBuilder {
    private List<DataEntry> entries = new LinkedList<>();

    public TemplateBuilder filledFromItem(Item item) {
        setEntries(item.getData());
        return this;
    }

    private void setEntries(List<DataEntry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    public TemplateBuilder emptyFromItem(Item item) {
        List<DataEntry> itemEntries = getClearedDataEntries(item);
        setEntries(itemEntries);

        return this;
    }

    private List<DataEntry> getClearedDataEntries(Item item) {
        List<DataEntry> itemEntries = item.getData();
        for(DataEntry entry : itemEntries) {
            entry.clear();
        }
        return itemEntries;
    }


    public TemplateBuilder emptyWithNames(String ... names) {
        List<DataEntry> nameEntries = new LinkedList<>();
        for(String name : names) {
            nameEntries.add(new StringDataEntry(name));
        }
        setEntries(nameEntries);
        return this;
    }

    public Template build() {
        return new Template(entries);
    }
}
