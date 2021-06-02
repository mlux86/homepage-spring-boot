package de.l0x.homepage.web.logging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogDataExtractor {

    private final GeoLocator geoLocator;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public List<String> extractFrom(HttpServletRequest request) {
        // Time
        String now = formatter.format(new Date());

        // Request path
        String path = request.getServletPath();

        // Geo IP
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String location = "unknown";
        try
        {
            InetAddress addr = InetAddress.getByName(ipAddress);
            location = geoLocator.locationFromIp(addr);
        } catch (UnknownHostException e)
        {
            log.debug("Cannot obtain location from IP.", e);
        }

        return List.of(now, path, ipAddress, location);
    }

}
