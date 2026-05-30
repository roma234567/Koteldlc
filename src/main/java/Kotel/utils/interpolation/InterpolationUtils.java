package Kotel.utils.interpolation;

public final class InterpolationUtils { private InterpolationUtils() {} public static double smoothStep(double t){t=Math.max(0,Math.min(1,t)); return t*t*(3-2*t);} }
