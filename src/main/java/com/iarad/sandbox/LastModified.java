package com.iarad.sandbox;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface LastModified {
    
    String lastModified() default "N/A";
}
