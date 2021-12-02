package sk.janobono.api.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.janobono.api.service.so.RoutingResultSO;

@Service
public class RoutingApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingApiService.class);

    public RoutingResultSO getRoutingResult(String origin, String destination) {
        LOGGER.debug("getRoutingResult({},{})", origin, destination);

        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Not implemented yet.");
    }
}
