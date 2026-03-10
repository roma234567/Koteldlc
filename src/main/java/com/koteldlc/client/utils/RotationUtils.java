package com.koteldlc.client.utils;

public final class RotationUtils {
    private RotationUtils() { }

    public static float[] getRotations(double dx, double dy, double dz) {
        double dist = Math.sqrt(dx * dx + dz * dz);
        return new float[]{(float) (Math.toDegrees(Math.atan2(dz, dx)) - 90f), (float) -Math.toDegrees(Math.atan2(dy, dist))};
    }
}
