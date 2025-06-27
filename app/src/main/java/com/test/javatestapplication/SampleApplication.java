package com.test.javatestapplication;

import android.app.Application;
import android.util.Log;

//import com.splunk.rum.SplunkRum;
//import com.splunk.rum.StandardAttributes;


import com.splunk.rum.integration.agent.api.SplunkRum;
import com.splunk.rum.integration.agent.api.attributes.StandardAttributes;

import io.opentelemetry.api.common.Attributes;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("Sample Application", "onCreate called");

        SplunkRum.builder()
                // note: for these values to be resolved, put them in your local.properties
                // file as rum.beacon.url and rum.access.token
                .setRealm("")
                .setApplicationName("")
                .setRumAccessToken("")
                .enableDebug()
                /*.disableSubprocessInstrumentation(BuildConfig.APPLICATION_ID)
                .enableBackgroundInstrumentationDeferredUntilForeground()
                .setSlowRenderingDetectionPollInterval(Duration.ofMillis(1000))*/
                .setDeploymentEnvironment("demo")
                //.limitDiskUsageMegabytes(1)
                .setGlobalAttributes(
                        Attributes.builder()
                                .put("global", "dummy")
                                //.put(StandardAttributes.APP_VERSION, "1.0.0")
                                .build())
                .build(this);
    }
}
