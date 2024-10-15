package tests.report;

import static org.junit.jupiter.api.Assertions.*;

import app.model.Record;
import app.parser.DataParser;
import app.report.ReportGenerator;
import org.junit.jupiter.api.Test;
import java.util.*;

class ReportGeneratorTest {

    private final String data =
            "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                    "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                    "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
                    "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
                    "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

    @Test
    void testUniqueCustomersPerContract() {
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        Map<String, Long> result = generator.getUniqueCustomersPerContract();

        assertEquals(3, result.get("2345"));
        assertEquals(2, result.get("2346"));
    }

    @Test
    void testUniqueCustomersPerGeozone() {
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        Map<String, Long> result = generator.getUniqueCustomersPerGeozone();

        assertEquals(1, result.get("us_east"));
        assertEquals(2, result.get("us_west"));
        assertEquals(2, result.get("eu_west"));
    }

    @Test
    void testAverageBuildDurationPerGeozone() {
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        Map<String, Double> result = generator.getAverageBuildDurationPerGeozone();

        assertEquals(3445.0, result.get("us_east"));
        assertEquals(2216.0, result.get("us_west"));
        assertEquals(4222.0, result.get("eu_west"));
    }

    @Test
    void testUniqueCustomerListPerGeozone() {
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        Map<String, Set<String>> result = generator.getUniqueCustomerListPerGeozone();

        assertTrue(result.get("us_east").contains("2343225"));
        assertTrue(result.get("us_west").contains("1223456"));
        assertTrue(result.get("us_west").contains("1233456"));
        assertTrue(result.get("eu_west").contains("3244332"));
    }

    @Test
    void testWithEmptyRecords() {
        List<Record> records = new ArrayList<>();
        ReportGenerator generator = new ReportGenerator(records);

        assertTrue(generator.getUniqueCustomersPerContract().isEmpty());
        assertTrue(generator.getUniqueCustomersPerGeozone().isEmpty());
        assertTrue(generator.getAverageBuildDurationPerGeozone().isEmpty());
        assertTrue(generator.getUniqueCustomerListPerGeozone().isEmpty());
    }

    @Test
    void testWithSameContractId() {
        String data = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244132,2345,eu_west,YellowTeam,ProjectCarrot,4322s";
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        assertEquals(3, generator.getUniqueCustomersPerContract().get("2345"));
    }

    @Test
    void testWithSameGeozone() {
        String data = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2346,us_east,BlueTeam,ProjectBanana,2211s\n" +
                "3244132,2347,us_east,YellowTeam,ProjectCarrot,4322s";
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        assertEquals(3, generator.getUniqueCustomersPerGeozone().get("us_east"));
    }

    @Test
    void testWithDifferentBuildDurations() {
        String data = "2343225,2345,us_east,RedTeam,ProjectApple,1000s\n" +
                "1223456,2346,us_east,BlueTeam,ProjectBanana,2000s\n" +
                "3244132,2347,us_east,YellowTeam,ProjectCarrot,3000s";
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(data);
        ReportGenerator generator = new ReportGenerator(records);

        assertEquals(2000.0, generator.getAverageBuildDurationPerGeozone().get("us_east"));
    }
}
