#include "../include/org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor.h"

#include "jni_sensor.h"
#include <cstring>


#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jboolean JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_sensorsInit
  (JNIEnv * env, jclass cls) {
    return jsensors::init();
}

JNIEXPORT void JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_sensorsCleanup
  (JNIEnv * env, jclass cls) {
    jsensors::cleanup();
}

JNIEXPORT jobjectArray JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_getChipNames
  (JNIEnv * env, jclass cls) {
    return jsensors::create_device_infos(env);
}

JNIEXPORT jobjectArray JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_getChipFeatures
  (JNIEnv * env, jclass cls, jobject device_info) {
    return jsensors::create_device_feature_infos(env, device_info);
}

#ifdef __cplusplus
}
#endif