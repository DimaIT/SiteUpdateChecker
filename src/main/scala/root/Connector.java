package root;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author D.Tolpekin
 */
public class Connector {

    public static String md5(String st) {
        MessageDigest messageDigest;
        byte[] digest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String md5Hex = bigInt.toString(16);

            while( md5Hex.length() < 32 ){
                md5Hex = "0" + md5Hex;
            }

            return md5Hex;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String testSite = "http://www.fr.gov.by/arenda/";
        boolean changed = false;
        String previous = Jsoup.connect(testSite).get().toString();
        String current = Jsoup.connect(testSite).get().toString();
        while (current.equals(previous)) {
            System.out.println("one more try");
            Thread.sleep(2000);
            previous = current;
            current = Jsoup.connect(testSite).get().toString();
        }
    }
}
