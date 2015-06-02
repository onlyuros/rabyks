package com.uslive.rabyks.common;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common {

	public static Logger log = LoggerFactory.getLogger(Common.class);
	
	public static String hashPost(String url, String user) {
        try {
            String salt = "registration salt";
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(salt.getBytes());
            byte[] hash = digest.digest((url + user).getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            log.error("Hash failed!", e.getMessage());
            return "";
        }
    }
}
