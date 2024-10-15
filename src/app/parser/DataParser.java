package app.parser;

import app.model.Record;
import java.util.List;
import java.util.ArrayList;

public class DataParser implements DataparserInterface{
    public List<Record> parse(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        List<Record> records = new ArrayList<>();
        String[] lines = input.split("\\n");

        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields.length != 6) {
                throw new IllegalArgumentException("Each line must have exactly 6 fields");
            }
            String customerId = fields[0];
            String contractId = fields[1];
            String geozone = fields[2];
            String teamCode = fields[3];
            String projectCode = fields[4];
            int buildDuration;
            try {
                buildDuration = Integer.parseInt(fields[5].replace("s", ""));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Build duration must be a valid integer");
            }
            records.add(new Record(customerId, contractId, geozone, teamCode, projectCode, buildDuration));
        }
        return records;
    }
}
