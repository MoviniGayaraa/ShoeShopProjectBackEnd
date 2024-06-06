package lk.ijse.gdse66.Shoe_Shop_Management_SystemBackend.utill;

import java.util.Base64;

public class UtilMatters {
    public static String convertBase64(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public static byte[] convertBase64ToBytes(String data) {
        return Base64.getDecoder().decode(data);
    }
}