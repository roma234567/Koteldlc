package com.koteldlc.client.utils;

public final class EntityUtils {
    private EntityUtils() { }

    public static boolean isValidTarget(double distance, double maxRange) {
        return distance <= maxRange;
    }
}
