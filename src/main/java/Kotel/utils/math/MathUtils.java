package Kotel.utils.math;

public final class MathUtils { private MathUtils() {} public static double clamp(double v,double min,double max){return Math.max(min, Math.min(max, v));} public static double lerp(double a,double b,double t){return a+(b-a)*clamp(t,0,1);} }
