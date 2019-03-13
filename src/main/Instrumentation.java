package main;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Instrumentation {

    long start = 0;
    boolean active = false;
    private String log = "";
    int indent = 0;

    Map<String,Long> map = new HashMap<String,Long>();

    private static Instrumentation instance = new Instrumentation();

    private Instrumentation(){
    }

    public void activate(Boolean onoff) {
        active = onoff;
        start = System.nanoTime();
    }

    public void startTiming(String key){
        if(active) {    
            map.put(key, System.nanoTime());
            for (int i = 0; i < indent; i++) {
                log = log.concat("  ");
            }
            log = log.concat("START TIMER: " + key + "\n");
            indent++;
        } else {
            System.out.println("Instrumentation not active");
        }
    } 

    public void stopTiming(String key){
        if(active) {    
            if (map.get(key) == null){
                indent--;
                for (int i = 0; i < indent; i++) {
                    log = log.concat("  ");
                }
                log = log.concat("BAD STRING: " + key + " is not mapped to a timer\n");
                return;
            }

            long start = map.get(key);
            long end = System.nanoTime();
            
            indent--;
            for (int i = 0; i < indent; i++) {
                log = log.concat("  ");
            }
            log = log.concat("STOP TIMER: " + key + " " + (end - start) + " ns \n");
        } else {
            System.out.println("Instrumentation not active");
        }
    }

    public static Instrumentation getInstance() {
        return instance;
    }

    public void comment(String com) {
        if (active) { 
            for (int i = 0; i < indent; i++) {
                log = log.concat("  ");
            }
            log = log.concat("COMMENT: " + com + "\n");
        } else {
            System.out.println("Instrumentation not active");
        }
    }

    public void dump(String filename) throws IOException {
        if (active) { 
            log = log.concat("TOTAL TIME: " + (System.nanoTime()-start) + "ns\n\n ");
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt"));
            writer.write(log);
            writer.close();
            
            
            System.out.println(log);
            return;
        } else {
            System.out.println("Instrumentation not active");
        }       
    }



}