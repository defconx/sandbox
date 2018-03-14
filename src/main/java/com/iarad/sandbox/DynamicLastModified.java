package com.iarad.sandbox;

import java.lang.annotation.Annotation;

public class DynamicLastModified implements LastModified {
    
    private String lastModified;
    
    public DynamicLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
    
    @Override
    public String lastModified() {
        return lastModified;
    }
    
    /**
     * Returns the annotation type of this annotation.
     *
     * @return the annotation type of this annotation
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return DynamicLastModified.class;
    }
}
