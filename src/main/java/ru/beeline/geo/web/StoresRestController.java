package ru.beeline.geo.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.beeline.geo.model.SalesPointDo;
import ru.beeline.geo.service.StoresService;
import ru.beeline.geo.util.exception.IPFindingException;
import ru.beeline.geo.util.exception.UrlFormingException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoresRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final StoresService service;

    @Autowired
    public StoresRestController(StoresService service) {
        this.service = service;
    }

    @GetMapping("/salesPoints")
    public List<SalesPointDo> getPoints(@RequestParam Double latitude, @RequestParam Double longitude,
                                        @Context HttpServletRequest requestContext)
            throws UrlFormingException, IPFindingException {
        String remoteAddr = requestContext.getRemoteAddr();
        log.debug("getPoints for latitude = {} and longitude = {}", latitude, longitude);
        return service.getPoints(latitude, longitude, remoteAddr);
    }
}
