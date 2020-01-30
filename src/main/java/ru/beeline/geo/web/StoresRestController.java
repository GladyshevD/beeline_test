package ru.beeline.geo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.beeline.geo.model.SalesPointDo;
import ru.beeline.geo.service.StoresService;
import ru.beeline.geo.util.exception.IPHandlerException;
import ru.beeline.geo.util.exception.UrlFormingException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class StoresRestController {

    private final StoresService service;

    @Autowired
    public StoresRestController(StoresService service) {
        this.service = service;
    }

    @GetMapping("/salesPoints")
    public List<SalesPointDo> getPoints(@RequestParam Double latitude,
                                        @RequestParam Double longitude,
                                        @Context HttpServletRequest requestContext) throws UrlFormingException, IPHandlerException {
        String remoteAddr = requestContext.getRemoteAddr();
        return service.getPoints(latitude, longitude, remoteAddr);
    }
}
