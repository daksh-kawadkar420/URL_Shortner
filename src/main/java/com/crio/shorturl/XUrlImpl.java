package com.crio.shorturl;

import java.util.HashMap;
import java.util.Random;

public class XUrlImpl implements XUrl {

    private HashMap<String, String> longToShort = new HashMap<>();
    private HashMap<String, String> shortToLong = new HashMap<>();
    private HashMap<String, Integer> storeCount = new HashMap<>();
    private int count = 0;
    static int urlLen = 9;
    static char[] shortURL = new char[urlLen];
    static String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public String registerNewUrl(String longUrl) {
        if (longToShort.containsKey(longUrl)) {
            return longToShort.get(longUrl);
        } else {
            Random rand = new Random();
            for (int i = 0; i < urlLen; i++) {
                shortURL[i] = randChars.charAt(rand.nextInt(randChars.length()));
            }
            StringBuilder sb = new StringBuilder("http://short.url/");
            sb.append(new String(shortURL));
            String shortUrl = sb.toString();
            longToShort.put(longUrl, shortUrl);
            shortToLong.put(shortUrl, longUrl);
            return shortUrl;
        }
    }

    public String registerNewUrl(String longUrl, String shortUrl) {
        if (longToShort.containsValue(shortUrl)) {
            return null;
        } else {
            longToShort.put(longUrl, shortUrl);
            return shortUrl;
        }
    }

    public String getUrl(String shortUrl) {
        String ans = null;
        if (longToShort.containsValue(shortUrl)) {
            for (String i : longToShort.keySet()) {
                if (longToShort.get(i).equals(shortUrl)) {
                    // System.out.println(i);
                    storeCount.put(i, storeCount.getOrDefault(i, 0) + 1);
                    ans = i;
                    break;
                }
            }
        }
        return ans;
    }

    public Integer getHitCount(String longUrl) {
        if (storeCount.containsKey(longUrl))
            return storeCount.get(longUrl);
        return 0;
    }

    public String delete(String longUrl) {
        longToShort.put(longUrl, null);
        return longToShort.get(longUrl);
    }
}
