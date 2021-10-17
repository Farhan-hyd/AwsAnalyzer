package utils;


import bootstrap.GlobalConstants;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import domain.SuspiciousUser;

import java.io.File;
import java.net.InetAddress;
import java.util.Vector;

public class LocationListener {

    public static void data(Vector<SuspiciousUser>  suspiciousUsers) throws Exception {
        File database = new File(GlobalConstants.DB_FILE_LOCATION);
        DatabaseReader databaseReader = new DatabaseReader.Builder(database).build();

        for (SuspiciousUser suspiciousUser : suspiciousUsers) {
            String ip = suspiciousUser.getIp();
            InetAddress inetAddress = InetAddress.getByName(ip);
            CityResponse cityResponse = databaseReader.city(inetAddress);
            suspiciousUser.setCity(cityResponse.getCity().getName());
            suspiciousUser.setCountry(cityResponse.getCountry().getName());
            suspiciousUser.setPostal(cityResponse.getPostal().getCode());
            suspiciousUser.setSubdivision(cityResponse.getLeastSpecificSubdivision().getName());
        }
    }
}
