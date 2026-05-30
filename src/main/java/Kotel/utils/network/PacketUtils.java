package Kotel.utils.network;

public final class PacketUtils { private PacketUtils() {} public static String describe(Object packet){return packet == null ? "null" : packet.getClass().getSimpleName();} }
