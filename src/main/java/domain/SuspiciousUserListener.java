package domain;

import utils.LocationListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class SuspiciousUserListener {

    public static void fetchSuspiciousUser(Vector<SuspiciousUser> suspiciousUsers, String filename) throws Exception {
       new File(filename);
       fetchUsers(suspiciousUsers);
       LocationListener.data(suspiciousUsers);
       outputFile(filename, suspiciousUsers);
    }


    public static void fetchUsers(Vector<SuspiciousUser> suspiciousUsers) throws Exception {
//        Scanner data = new Scanner(new File("/var/log/auth.log"));
        Scanner data = new Scanner(new File("src/main/resources/test.txt"));
        HashMap<String,SuspiciousUser> invalidUserHistogram = new HashMap<>();
        while (data.hasNextLine()) {
            String str = data.nextLine();
            String[] StringArray = str.split(" ");
            if (StringArray.length > 8) {
                if (StringArray[5].equals("Failed") && StringArray[8].equals("invalid")) {
                    String ip = StringArray[12];
                    if (!invalidUserHistogram.containsKey(ip)) {
                        invalidUserHistogram.put(ip,
                                new SuspiciousUser(new SuspiciousUserBuilder()
                                .withIP(ip)
                                .withFrequency(1)
                                .withTimestamps(new ArrayList<>(
                                        Collections.singletonList(StringArray[0]+";"+StringArray[1]+";"+StringArray[2]
                                        )))));
                    } else {
                        (invalidUserHistogram.get(ip))
                                .setFrequency(invalidUserHistogram.get(ip).getFrequency()+1);
                        ArrayList<String> timestamps = invalidUserHistogram.get(ip).getTimestamps();
                        timestamps.add(StringArray[0]+";"+StringArray[1]+";"+StringArray[2]);
                        invalidUserHistogram.put(ip, invalidUserHistogram.get(ip));
                    }
                }
            }
        }

        invalidUserHistogram.forEach((key, value) -> suspiciousUsers.add(value));
    }

    static String convertVectorToString(Vector<SuspiciousUser> suspiciousUsers) {
        StringBuilder strBuilder = new StringBuilder();
        suspiciousUsers.forEach(suspiciousUser -> strBuilder.append(suspiciousUser.getIp()).append(",")
                .append(suspiciousUser.getFrequency()).append(",")
                .append(suspiciousUser.getCity()).append(",")
                .append(suspiciousUser.getCountry()).append(",")
                .append(suspiciousUser.getPostal()).append(",")
                .append(suspiciousUser.getSubdivision()).append("\n"));
        return strBuilder.toString();
    }

    public static void outputFile(String fileName, Vector<SuspiciousUser> suspiciousUsers) throws Exception {
        String analyzedHistogram = convertVectorToString(suspiciousUsers);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        bufferedWriter.write(analyzedHistogram);
        bufferedWriter.close();
    }
}
