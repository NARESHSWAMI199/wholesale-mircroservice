package com.auth.utils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class Util {


    public static boolean isEmpty(String value) {
        return value == null || value.equals("");
    }

    public static String generateImageUrl(String image, String prefix, String placeholder) {
        if (!isEmpty(image) && !image.equals("0")) {
            return prefix + image;
        }
        return placeholder;
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static Long getCurrentTime() {
        Date date = new Date();
        return date.getTime() / 1000;
    }

    public static String getFormatedDate(String date, String inFormate, String outFormate) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(inFormate);
            SimpleDateFormat sdf2 = new SimpleDateFormat(outFormate);
            return sdf2.format(sdf1.parse(date));
        } catch (Exception ignore) {

        }
        return "";
    }

    public static String getFormatedDate(long date, String formate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat(formate);
            return sdf2.format(calendar.getTime());
        } catch (Exception ignore) {

        }
        return "";
    }

    public static long getFormatedDateMilli(String date, String formate) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(formate);
            return sdf1.parse(date).getTime();
        } catch (Exception ignore) {

        }
        return 0;
    }

    public static Date getFormattedDateObject(String date, String formate) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(formate);
            return sdf1.parse(date);
        } catch (Exception ignore) {

        }
        return null;
    }

    public static long getFormatedDateMilliTimeZone(String date, String formate, String timeZone) {
        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat(formate);
            sdf1.setTimeZone(TimeZone.getTimeZone(timeZone));
            return sdf1.parse(date).getTime() / 1000;
        } catch (Exception ignore) {

        }
        return 0;
    }

    public static String convertToMD5(String input) {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getAlphaNumericString(int n) {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index = (int) (AlphaNumericString.length() * Math.random());

            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

//    public static String encodeData(String data) {
//        int position = 2;
//        int sposition = 3;
//
//        String string = getAlphaNumericString(4);
//        String string1 = getAlphaNumericString(4);
//        String newstring = new StringBuffer(data).insert(position, string).toString();
//        String received_text = new StringBuffer(newstring).insert(newstring.length() - sposition, string1).toString();
//        received_text = Base64.getEncoder().encodeToString(received_text.getBytes());
//
//        BigInteger currentTime = BigInteger.valueOf(Util.getCurrentTime());
//        String returnString = received_text + "----" + currentTime;
//        returnString = Base64.getEncoder().encodeToString(returnString.toString().getBytes());
//
//        String string3 = getAlphaNumericString(4);
//        String string4 = getAlphaNumericString(4);
//        newstring = new StringBuffer(returnString).insert(position, string3).toString();
//        received_text = new StringBuffer(newstring).insert(newstring.length() - sposition, string4).toString();
//
//        return received_text;
//
//    }

    public static String decodeData(String data) {
        if (data != null && data.length() > 7) {
            StringBuilder sb = new StringBuilder(data);
            int i = data.length() - 7;
            sb.delete(i, i + 4);
            i = 2;
            sb.delete(i, i + 4);

            data = new String(Base64.getDecoder().decode(sb.toString().getBytes()));
            data = data.split("----")[0];
            data = new String(Base64.getDecoder().decode(data.getBytes()));
            sb = new StringBuilder(data);
            i = data.length() - 7;
            sb.delete(i, i + 4);
            i = 2;
            sb.delete(i, i + 4);
            return sb.toString();
        }
        return data;
    }

    public static String getSHA512(String input) {
        String toReturn = null;
        try {
            byte[] hashseq = input.getBytes();
            StringBuffer hexString = new StringBuffer();
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException nsae) {
        }
        return toReturn;
    }

    public static String getHmac256(String key, String input) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key_spec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key_spec);

            byte[] bytes = sha256_HMAC.doFinal(input.getBytes());

            return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(input.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static float numberFormate(float value, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    public static String getBaseProjectUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() + "/";
        return baseURL;
    }

    public static String getStringDateFromTimeStamp(long timeStamp, String format) {
        Date d = new Date((long) timeStamp * 1000);
        DateFormat f = new SimpleDateFormat(format);
        return f.format(d);
    }

    public static Date atEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTime();
    }

    public static String aesEncrypt(String Data, String secretKey) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES"); // Default uses ECB PKCS5Padding
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(Data.getBytes());
            String encryptedValue = Base64.getEncoder().encodeToString(encVal);
            return encryptedValue;
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String aesDecrypt(String strToDecrypt, String secretKey) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}
