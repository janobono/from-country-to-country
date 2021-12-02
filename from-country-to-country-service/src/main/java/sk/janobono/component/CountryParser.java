package sk.janobono.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryParser.class);

    private final ObjectMapper objectMapper;

    public CountryParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<String, List<String>> parseCountryMap(byte[] data) {
        Map<String, List<String>> result = new HashMap<>();
        try {
            JsonNode root = objectMapper.readTree(data);
            for (JsonNode jsonNode : root) {
                String countryCode = jsonNode.get("cca3").asText();
                List<String> countryBorders = new ArrayList<>();
                JsonNode borders = jsonNode.get("borders");
                for (JsonNode border : borders) {
                    countryBorders.add(border.asText());
                }
                LOGGER.debug("{}, {}", countryCode, countryBorders);
                result.put(countryCode, countryBorders);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        LOGGER.debug("parseCountryMap()={}", result);
        return result;
    }
}
