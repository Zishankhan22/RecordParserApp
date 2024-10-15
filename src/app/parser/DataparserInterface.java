package app.parser;

import app.model.Record;

import java.util.List;

public interface DataparserInterface {
    public List<Record> parse(String input);
}
