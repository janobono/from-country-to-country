package sk.janobono.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.janobono.api.service.so.RoutingResultSO;
import sk.janobono.api.service.service.RoutingApiService;

@RestController
@RequestMapping(value = "/")
public class RoutingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoutingController.class);

    private final RoutingApiService routingService;

    public RoutingController(RoutingApiService routingService) {
        this.routingService = routingService;
    }

    @GetMapping(value = "/routing/{origin}/{destination}")
    public ResponseEntity<RoutingResultSO> getRoutingResult(@PathVariable("origin") String origin, @PathVariable("destination") String destination) {
        LOGGER.debug("getRoutingResult({},{})", origin, destination);
        return new ResponseEntity<>(routingService.getRoutingResult(origin, destination), HttpStatus.OK);
    }
}
