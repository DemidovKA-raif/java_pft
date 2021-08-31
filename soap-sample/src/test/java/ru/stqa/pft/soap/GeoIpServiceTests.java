package ru.stqa.pft.soap;

import com.lavasoft.*;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIP(){

        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("212.34.48.80");
        System.out.println(ipLocation);
    }
}
