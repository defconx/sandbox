package com.iarad.sandbox;

public class Main {
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(Main.class);
    
    
    public static void main(String[] args) {
    
        ModifiableRecord record = new ModifiableRecord("Tony", "Starke");
        
        log.info("Last modified: {}", record.getLastModified());
        
        // oops, we spelled Tony's last name wrong.
        record.setLastname("Stark");
    
        log.info("Last modified: {}", record.getLastModified());
    }
}
