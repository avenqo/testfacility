package com.avenqo.testfacility.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;

@LoadPolicy(LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:general.properties",
        "classpath:grid.properties"})
public interface Configuration extends Config {

	//Local, Remote
    @Key("target")
    String target();

    @Key("browser")
    String browser();

    @Key("headless")
    Boolean headless();

    @Key("url.base")
    String url();

    @Key("grid.url")
    String gridUrl();

    @Key("grid.port")
    String gridPort();
    
    // readable name for test
    @Key("test.name")
    String testName();
    
}
