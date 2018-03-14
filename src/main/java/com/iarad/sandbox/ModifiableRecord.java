package com.iarad.sandbox;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@LastModified()
public class ModifiableRecord {
    
    private static final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(ModifiableRecord.class);
    
    private String firstname;
    private String lastname;
    
    public ModifiableRecord(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        
        updateLastModified();
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
        updateLastModified();
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
        updateLastModified();
    }
    
    public String getLastModified() {
        LastModified annotation = this.getClass().getAnnotation(LastModified.class);
        
        if (annotation == null) {
            log.error("Unable to load annotation.");
            return "";
        }
        
        log.info("last modified value: {}", annotation.lastModified());
    
        return annotation.lastModified();
    }
    
    private void updateLastModified() {
    
        try {
            Method method = Class.class.getDeclaredMethod("annotationData");
            method.setAccessible(true);
    
            Object annotationData = method.invoke(this.getClass());
    
            Field annotations = annotationData.getClass().getDeclaredField("annotations");
            annotations.setAccessible(true);
    
            Map<Class<? extends Annotation>, Annotation> map =
                    (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
    
            String previous = this.getClass().getAnnotation(LastModified.class).lastModified();
            String updated = Long.toString(System.currentTimeMillis());
            
            map.put(LastModified.class, new DynamicLastModified(updated));
            log.info("last modified value - original: {} updated to: {}", previous, updated);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
