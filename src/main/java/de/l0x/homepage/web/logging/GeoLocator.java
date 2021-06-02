package de.l0x.homepage.web.logging;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Component
@Slf4j
public class GeoLocator
{

    private final DatabaseReader dbReader;

    public GeoLocator(@Value("${geoip.db}") String dbFile) throws IOException
    {
        File file = new File(dbFile);
        dbReader = new DatabaseReader.Builder(file).fileMode(Reader.FileMode.MEMORY_MAPPED).build();
    }

    @Cacheable("geoLocation")
    public String locationFromIp(InetAddress addr) {
        try
        {
            return dbReader.city(addr).getCity().getName();
        } catch (IOException | GeoIp2Exception e)
        {
            log.error("Could not match geo location for IP: " + addr.toString());
            return "unknown";
        }
    }

}
