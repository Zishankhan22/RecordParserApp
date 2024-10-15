package app.processor;

import app.model.Record;

import java.util.*;

public class BuildDataProcessor implements DataProcessor{

    private List<Record> records;
    private Map<String, Set<String>> uniqueCustomersByContract = new HashMap<>();
    private Map<String, Set<String>> uniqueCustomersByGeozone = new HashMap<>();
    private Map<String, List<Integer>> buildDurationsByGeozone = new HashMap<>();

    public BuildDataProcessor(List<Record> records) {
        this.records = records;
    }
    @Override
    public void process(String data) {
        for (Record record : records) {
            uniqueCustomersByContract
                    .computeIfAbsent(record.getContractId(), k -> new HashSet<>())
                    .add(record.getCustomerId());

            uniqueCustomersByGeozone
                    .computeIfAbsent(record.getGeozone(), k -> new HashSet<>())
                    .add(record.getCustomerId());

            buildDurationsByGeozone
                    .computeIfAbsent(record.getGeozone(), k -> new ArrayList<>())
                    .add(record.getBuildDuration());
        }
    }

    public Map<String, Set<String>> getUniqueCustomersByContract() {
        return uniqueCustomersByContract;
    }
    public Map<String, Set<String>> getUniqueCustomersByGeozone() {
        return uniqueCustomersByGeozone;
    }
    public Map<String, Double> getAverageBuildDurationByGeozone() {
        Map<String, Double> averageDurations = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : buildDurationsByGeozone.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            averageDurations.put(entry.getKey(), avg);
        }
        return averageDurations;
    }

}
