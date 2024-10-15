package app.main;

import app.model.Record;
import app.parser.DataParser;
import app.report.ReportGenerator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String inputData = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n"
                + "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n"
                + "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n"
                + "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n"
                + "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        DataParser parser = new DataParser();
        List<Record> data = parser.parse(inputData);

        ReportGenerator reportGenerator = new ReportGenerator(data);

        Map<String, Long> uniqueCustomerPerContract = reportGenerator.getUniqueCustomersPerContract();
        Map<String, Long> uniqueCustomerPerGeoZone = reportGenerator.getUniqueCustomersPerGeozone();
        Map<String, Double> avgBuildDurationPerGeoZone = reportGenerator.getAverageBuildDurationPerGeozone();
        Map<String, Set<String>> customerIdsPerGeoZone = reportGenerator.getUniqueCustomerListPerGeozone();

        System.out.println("Unique Customers per ContractId: " + uniqueCustomerPerContract);
        System.out.println("Unique Customers per GeoZone: " + uniqueCustomerPerGeoZone);
        System.out.println("Average Build Duration per GeoZone: " + avgBuildDurationPerGeoZone);
        System.out.println("Customer IDs per GeoZone: " + customerIdsPerGeoZone);
    }
}
