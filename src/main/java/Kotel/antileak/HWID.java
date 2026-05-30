package Kotel.antileak;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HWID { private HWID() {} public static String localFingerprint(String input){ try { byte[] d=MessageDigest.getInstance("SHA-256").digest(input.getBytes(StandardCharsets.UTF_8)); StringBuilder sb=new StringBuilder(); for(byte b:d) sb.append(String.format("%02x", b)); return sb.toString(); } catch (NoSuchAlgorithmException e) { throw new IllegalStateException(e); } } }
