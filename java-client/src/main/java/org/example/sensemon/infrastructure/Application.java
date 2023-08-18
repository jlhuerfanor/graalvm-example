package org.example.sensemon.infrastructure;

import org.example.sensemon.application.adapter.secondary.jni.JniSensorMonitor;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

// @SpringBootApplication
public class Application {
    public static void main(String[] args) {
        try(var m = new JniSensorMonitor()) {
            var list = m.getDevices();

            System.out.printf("Devices: %n%s%n", list.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(System.lineSeparator()))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void doYourThing(Value cpart) {

        var sensemonInit = cpart.getMember("sensemon_init");
        var sensemonIsInitialized = cpart.getMember("sensemon_is_initialized");
        var sensemonIsReady = cpart.getMember("sensemon_is_ready");
        var sensemonDeviceCount = cpart.getMember("sensemon_get_device_count");
        var sensemonDispose = cpart.getMember("sensemon_dispose");

        sensemonInit.executeVoid();

        System.out.printf("Initialized: %s%n", sensemonIsInitialized.execute().asBoolean());
        System.out.printf("Ready: %s%n", sensemonIsReady.execute().asBoolean());
        System.out.printf("DeviceCount: %s%n", sensemonDeviceCount.execute().asInt());

        sensemonDispose.executeVoid();
    }
}
