package Kotel.utils.time;

public class TimerUtils { private long last=System.nanoTime(); public void reset(){last=System.nanoTime();} public boolean passedMillis(long ms){return (System.nanoTime()-last)/1_000_000L >= ms;} }
