package app.report;

import app.model.Record;
import java.util.*;
import java.util.stream.Collectors;

public class ReportGenerator {
    private List<Record> records;

    public ReportGenerator(List<Record> records) {
        this.records = records;
    }

    public Map<String, Long> getUniqueCustomersPerContract() {

        Map<String, Set<String>> uniqueCustomersPerContract = getUniqueCustomerListPerGeozone();
        return records.stream()
                .collect(Collectors.groupingBy(Record::getContractId, Collectors.mapping(Record::getCustomerId, Collectors.toSet())))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
    }

    public Map<String, Long> getUniqueCustomersPerGeozone() {
        Map<String, Set<String>> uniqueCustomersPerGeozone = getUniqueCustomerListPerGeozone();
        return uniqueCustomersPerGeozone.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue().size()));
    }

    public Map<String, Double> getAverageBuildDurationPerGeozone() {
        return records.stream()
                .collect(Collectors.groupingBy(Record::getGeozone, Collectors.averagingInt(Record::getBuildDuration)));
    }

    public Map<String, Set<String>> getUniqueCustomerListPerGeozone() {
        return records.stream()
                .collect(Collectors.groupingBy(Record::getGeozone, Collectors.mapping(Record::getCustomerId, Collectors.toSet())));
    }
}
