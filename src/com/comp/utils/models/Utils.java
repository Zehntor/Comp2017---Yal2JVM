package com.comp.utils.models;

import java.util.Formatter;
import java.util.StringJoiner;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class Utils {

    public String getOrdinal(int n) {
        switch (n) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            default:
                return String.format("%dth", n);
        }
    }

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean isDouble(String str) {
        try {
            Double.valueOf(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public String formatBytes(long bytes) {
        double bytesToDouble = (double) bytes;
        String[] prefixes = {"", "K", "M", "G", "T", "P", "E", "Z", "Y"};
        int i = 0;
        for (i = 0; i < prefixes.length && bytesToDouble >= 1024; bytesToDouble /= 1024, i++);
        bytesToDouble = roundTo(bytesToDouble, 3);

        return String.format("%s %sB", bytesToDouble, prefixes[i]);
    }

    public String getHumanReadableNumber(int number, String singular) {
        String plural = String.format("%ss", singular);
        return getHumanReadableNumber(number, singular, plural);
    }

    public String getHumanReadableNumber(int number, String singular, String plural) {
        return String.format("%s %s", number, number == 1 ? singular : plural);
    }

    public double roundTo(double number, int decimals) {
        if (decimals < 0) {
            throw new IllegalArgumentException("Can only round to positive decimals");
        }
        double exp = Math.pow(10, decimals);

        return Math.round(number * exp) / exp;
    }

    public String sha1(String str) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch(NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException e) {
        }

        return sha1;
    }

    /**
     * {"this", "that", "the other"}, "and" -> "this, that and the other"
     *
     * @param list
     * @param glue
     * @return String
     */
    public String getHumanReadableList(String[] list, String glue) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (String l : list) {
            stringJoiner.add(l);
        }

        String listWithoutGlue = stringJoiner.toString();

        int index = listWithoutGlue.lastIndexOf(", ");
        if (index != -1) {
            return new StringBuilder(listWithoutGlue).replace(index, index + ", ".length(), String.format(" %s ", glue)).toString();
        }

        return listWithoutGlue;
    }

    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();

        return result;
    }
}
