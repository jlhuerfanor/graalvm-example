#include "../native-include/org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor.h"

#include "jni_sensor.h"
#include <cstring>
#include "jni_domain.h"


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

JNIEXPORT jobjectArray JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_getChipSubFeatures
  (JNIEnv * env, jclass cls, jobject feature_info) {
    return jsensors::create_device_sub_feature_infos(env, feature_info);
}

JNIEXPORT jobject JNICALL Java_org_example_sensemon_application_adapter_secondary_jni_JniSensorMonitor_getSubFeatureValue
  (JNIEnv * env, jclass cls, jobject sub_feature_info) {
    return jsensors::get_sub_feature_value(env, sub_feature_info);
}

#ifdef __cplusplus
}
#endif