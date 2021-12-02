package sk.janobono.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import sk.janobono.TestUtil;
import sk.janobono.component.CountryParser;
import sk.janobono.component.DataLinkClient;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RoutingServiceTest {

    @Autowired
    public CountryParser countryParser;

    @Autowired
    public DataLinkClient dataLinkClient;

    @Autowired
    public RoutingService routingService;

    @Test
    public void findRouteTest() throws Exception {
        byte[] data = TestUtil.getFileData(ResourceUtils.getFile("classpath:dummy-data.json"));
        Map<String, List<String>> countryMap = countryParser.parseCountryMap(data);
        List<String> route = routingService.findRoute("CZE", "ITA", countryMap);
        assertThat(route.contains("CZE")).isTrue();
        assertThat(route.contains("AUT")).isTrue();
        assertThat(route.contains("ITA")).isTrue();
    }
}
