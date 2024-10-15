package app.report;

import app.processor.BuildDataProcessor;

public class BuildReportGenerator implements ReportGeneratorInterface{

    private BuildDataProcessor dataProcessor;

    public BuildReportGenerator(BuildDataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
    }
    @Override
    public void generateReport() {
        System.out.println("Unique Customers by ContractId:");
        dataProcessor.getUniqueCustomersByContract().forEach((contractId, customers) -> {
            System.out.println("ContractId: " + contractId + ", Unique Customers: " + customers.size());
        });

        System.out.println("\nUnique Customers by Geozone:");
        dataProcessor.getUniqueCustomersByGeozone().forEach((geozone, customers) -> {
            System.out.println("Geozone: " + geozone + ", Unique Customers: " + customers.size());
        });

        System.out.println("\nAverage Build Duration by Geozone:");
        dataProcessor.getAverageBuildDurationByGeozone().forEach((geozone, avgDuration) -> {
            System.out.println("Geozone: " + geozone + ", Avg Build Duration: " + avgDuration + "s");
        });

        System.out.println("\nUnique Customers List by Geozone:");
        dataProcessor.getUniqueCustomersByGeozone().forEach((geozone, customers) -> {
            System.out.println("Geozone: " + geozone + ", Customers: " + customers);
        });
    }
}
