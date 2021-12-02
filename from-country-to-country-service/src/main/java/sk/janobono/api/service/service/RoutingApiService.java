package sk.janobono.api.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.janobono.api.service.so.RoutingResultSO;
import sk.janobono.service.RoutingService;

@Service
public class RoutingApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingApiService.class);

    private final RoutingService routingService;

    public RoutingApiService(RoutingService routingService) {
        this.routingService = routingService;
    }

    public RoutingResultSO getRoutingResult(String origin, String destination) {
        LOGGER.debug("getRoutingResult({},{})", origin, destination);
        RoutingResultSO resultSO = new RoutingResultSO(routingService.findRoute(origin, destination));
        if (resultSO.route().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No route to destination.");
        }
        return resultSO;
    }
}
