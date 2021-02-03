package cz.osu.kip.TTT.utils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SecurityUtils {
    public static byte[] generateSalt(){
        java.security.SecureRandom random = null;
        try {
            random = java.security.SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Failed to get strong alg.", e);
        }
        return random.generateSeed(128);
    }

    public static byte[] hashPassword(String password, byte[] salt) {
        char[] passwordBytes = password.toCharArray();
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            PBEKeySpec spec = new PBEKeySpec(passwordBytes, salt, 100, 128);
            SecretKey key = skf.generateSecret(spec);
            return key.getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e){
            throw new RuntimeException();
        }
    }

    public static boolean matchPassword(byte[] a, byte[] b) {
        boolean ret;
        if (a == null || b == null){
            ret = false;
        } else {
            if (a.length != b.length){
                ret = false;
            } else {
                ret = true;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] != b[i]){
                        ret = false;
                    }
                }
            }
        }
        return ret;
    }
}
