package tests.processor;

import app.model.Record;
import app.processor.BuildDataProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuildDataProcessorTest {

    private BuildDataProcessor processor;

    @BeforeEach
    public void setUp() {
        List<Record> records = Arrays.asList(
                new Record("2343225", "2345", "us_east", "RedTeam", "ProjectApple", 3445),
                new Record("1223456", "2345", "us_west", "BlueTeam", "ProjectBanana", 2211),
                new Record("3244332", "2346", "eu_west", "YellowTeam3", "ProjectCarrot", 4322)
        );
        processor = new BuildDataProcessor(records);
        processor.process("");
    }

    @Test
    public void testGetUniqueCustomersByContract() {
        assertEquals(Set.of("2343225", "1223456"), processor.getUniqueCustomersByContract().get("2345"));
        assertEquals(Set.of("3244332"), processor.getUniqueCustomersByContract().get("2346"));
    }

    @Test
    public void testGetUniqueCustomersByGeozone() {
        assertEquals(Set.of("2343225"), processor.getUniqueCustomersByGeozone().get("us_east"));
        assertEquals(Set.of("1223456"), processor.getUniqueCustomersByGeozone().get("us_west"));
        assertEquals(Set.of("3244332"), processor.getUniqueCustomersByGeozone().get("eu_west"));
    }

    @Test
    public void testGetAverageBuildDurationByGeozone() {
        assertEquals(3445.0, processor.getAverageBuildDurationByGeozone().get("us_east"));
        assertEquals(2211.0, processor.getAverageBuildDurationByGeozone().get("us_west"));
        assertEquals(4322.0, processor.getAverageBuildDurationByGeozone().get("eu_west"));
    }
}
