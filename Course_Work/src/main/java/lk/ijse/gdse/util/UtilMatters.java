package lk.ijse.gdse.util;

import java.util.Base64;


public class UtilMatters {
    public static String convertBase64(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static byte[] convertBase64ToBytes(String data) {
        return Base64.getDecoder().decode(data);
    }
}
