package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * Created by User on 04.06.2016.
 */
public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("178.151.21.195");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("178.151.21.qqq");
        assertEquals(geoIP.getCountryCode(), "UKR");
    }
}
