package sk.janobono.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.janobono.component.CountryParser;
import sk.janobono.component.DataLinkClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RoutingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingService.class);

    private DataLinkClient dataLinkClient;

    private CountryParser countryParser;

    @Autowired
    public void setDataLinkClient(DataLinkClient dataLinkClient) {
        this.dataLinkClient = dataLinkClient;
    }

    @Autowired
    public void setCountryParser(CountryParser countryParser) {
        this.countryParser = countryParser;
    }

    public List<String> findRoute(String origin, String destination) {
        LOGGER.debug("findRoute({},{})", origin, destination);
        List<String> result = new ArrayList<>();
        byte[] data = dataLinkClient.getData();
        Map<String, List<String>> countryMap = countryParser.parseCountryMap(data);
        if (countryMap.containsKey(origin) && countryMap.containsKey(destination)) {
            findRoute(origin, destination, countryMap, result);
        }
        LOGGER.debug("findRoute({},{})={}", origin, destination, result);
        return result;
    }

    private void findRoute(String origin, String destination, Map<String, List<String>> countryMap, List<String> result) {
        // TODO
    }
}
