package sk.janobono.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import sk.janobono.TestUtil;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountryParserTest {

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public CountryParser countryParser;

    @Autowired
    public DataLinkClient dataLinkClient;

    @Test
    public void dummyDataTest() throws Exception {
        byte[] data = TestUtil.getFileData(ResourceUtils.getFile("classpath:dummy-data.json"));
        Map<String, List<String>> countryMap = countryParser.parseCountryMap(data);
        assertThat(countryMap.containsKey("CZE")).isTrue();
        assertThat(countryMap.get("CZE").size()).isEqualTo(4);
    }

    @Disabled
    @Test
    public void liveDataTest() throws Exception {
        byte[] data = dataLinkClient.getData();
        Map<String, List<String>> countryMap = countryParser.parseCountryMap(data);
        assertThat(countryMap.containsKey("CZE")).isTrue();
        assertThat(countryMap.get("CZE").size()).isEqualTo(4);
    }
}
