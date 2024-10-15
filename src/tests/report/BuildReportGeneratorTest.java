package tests.report;

import app.model.Record;
import app.processor.BuildDataProcessor;
import app.report.BuildReportGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildReportGeneratorTest {

    private BuildReportGenerator reportGenerator;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        List<Record> records = Arrays.asList(
                new Record("2343225", "2345", "us_east", "RedTeam", "ProjectApple", 3445),
                new Record("1223456", "2345", "us_west", "BlueTeam", "ProjectBanana", 2211),
                new Record("3244332", "2346", "eu_west", "YellowTeam3", "ProjectCarrot", 4322)
        );
        BuildDataProcessor processor = new BuildDataProcessor(records);
        processor.process("");
        reportGenerator = new BuildReportGenerator(processor);

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGenerateReport() {
        reportGenerator.generateReport();
        String output = outContent.toString();

        assertTrue(output.contains("Unique Customers by ContractId:"));
        assertTrue(output.contains("Unique Customers by Geozone:"));
        assertTrue(output.contains("Average Build Duration by Geozone:"));
        assertTrue(output.contains("Unique Customers List by Geozone:"));
    }
}
