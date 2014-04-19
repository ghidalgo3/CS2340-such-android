package edu.gatech.CS2340.suchwow.Security;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Wayne on 4/19/2014.
 */
public class EncryptionHandler {
    public final static int SALT_LENGTH = 32;
    private final static String ALGORITHM = "MD5";

    public static String generateSalt() {
        SecureRandom random =  new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return Base64.encodeToString(salt, Base64.DEFAULT);
    }

    public static String hashString(String input, String salt) {
        String saltedInput = input + salt;
        byte[] saltedBytes = saltedInput.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance(ALGORITHM);
            byte[] hashBytes = md5.digest(saltedBytes);
            return Base64.encodeToString(hashBytes, Base64.DEFAULT);
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
