package Kotel.utils.rotation;

public final class RotationUtils { private RotationUtils() {} public static float wrapDegrees(float degrees){float r=degrees%360f; if(r>=180f) r-=360f; if(r<-180f) r+=360f; return r;} }
