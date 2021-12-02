package sk.janobono.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sk.janobono.config.ConfigProperties;

import java.util.Objects;

@Component
public class DataLinkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataLinkClient.class);

    private final RestTemplate restTemplate;

    private final ConfigProperties configProperties;

    public DataLinkClient(RestTemplateBuilder restTemplateBuilder, ConfigProperties configProperties) {
        this.restTemplate = restTemplateBuilder.build();
        this.configProperties = configProperties;
    }

    public byte[] getData() {
        LOGGER.debug("getData()");
        byte[] result = restTemplate.getForObject(configProperties.dataLink(), byte[].class);
        LOGGER.debug("getData(data not null {})", Objects.nonNull(result));
        return result;
    }
}
