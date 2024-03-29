package com.mimic.accesrest;

import java.util.Properties;
import android.util.Log;

public class PropertyLoader {

    private boolean hasCredentials = false;
    private String tokenVendingMachineURL = null;
    private boolean useSSL = false;
       
    private static PropertyLoader instance = null;
    
    public static PropertyLoader getInstance() {
        if ( instance == null ) {
            instance = new PropertyLoader();
        }
        
        return instance;
    }
              
    public PropertyLoader() {
        try {
     Properties properties = new Properties();
     properties.load( this.getClass().getResourceAsStream( "AwsCredentials.properties" ) );

     this.tokenVendingMachineURL = properties.getProperty( "tokenVendingMachineURL" );
     this.useSSL = Boolean.parseBoolean( properties.getProperty( "useSSL" ) );

            if ( this.tokenVendingMachineURL == null || this.tokenVendingMachineURL.equals( "" ) || this.tokenVendingMachineURL.equals( "CHANGEME" ) ) {
                this.tokenVendingMachineURL = null;
                this.useSSL = false;
                this.hasCredentials = false;
            }
            else {
                this.hasCredentials = true;
            }
        }
        catch ( Exception exception ) {
            Log.e( "PropertyLoader", "Unable to read property file." );
        }
    }
    
    public boolean hasCredentials() {
        return this.hasCredentials;
    }
    
    public String getTokenVendingMachineURL() {
        return this.tokenVendingMachineURL;
    }
        
    public boolean useSSL() {
        return this.useSSL;
    }
        
}

