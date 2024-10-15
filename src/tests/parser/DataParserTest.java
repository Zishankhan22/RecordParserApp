package tests.parser;

import static org.junit.jupiter.api.Assertions.*;

import app.model.Record;
import app.parser.DataParser;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataParserTest {

    @Test
    void testDataParser() {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
                "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
                "3244132,2346,eu_west,YellowTeam,ProjectCarrot,4322s";
        DataParser parser = new DataParser();
        List<Record> records = parser.parse(input);
        assertEquals(3, records.size());
        assertEquals("2343225", records.get(0).getCustomerId());
        assertEquals("2345", records.get(0).getContractId());
        assertEquals("us_east", records.get(0).getGeozone());
        assertEquals("RedTeam", records.get(0).getTeamCode());
        assertEquals("ProjectApple", records.get(0).getProjectCode());
        assertEquals(3445, records.get(0).getBuildDuration());
        assertEquals("1223456", records.get(1).getCustomerId());
        assertEquals("2345", records.get(1).getContractId());
        assertEquals("us_west", records.get(1).getGeozone());
        assertEquals("BlueTeam", records.get(1).getTeamCode());
        assertEquals("ProjectBanana", records.get(1).getProjectCode());
        assertEquals(2211, records.get(1).getBuildDuration());
        assertEquals("3244132", records.get(2).getCustomerId());
        assertEquals("2346", records.get(2).getContractId());
        assertEquals("eu_west", records.get(2).getGeozone());
        assertEquals("YellowTeam", records.get(2).getTeamCode());
        assertEquals("ProjectCarrot", records.get(2).getProjectCode());
        assertEquals(4322, records.get(2).getBuildDuration());
    }

    @Test
    void testParseWithEmptyString() {
        DataParser parser = new DataParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    @Test
    void testParseWithNullString() {
        DataParser parser = new DataParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null));
    }

    @Test
    void testParseWithLessThanSixFields() {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple";
        DataParser parser = new DataParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void testParseWithMoreThanSixFields() {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s,ExtraField";
        DataParser parser = new DataParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse(input));
    }

    @Test
    void testParseWithNonIntegerBuildDuration() {
        String input = "2343225,2345,us_east,RedTeam,ProjectApple,NotAnInteger";
        DataParser parser = new DataParser();
        assertThrows(NumberFormatException.class, () -> parser.parse(input));
    }

}
