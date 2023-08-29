#ifndef __JNI_SENSOR_H__
#define __JNI_SENSOR_H__

#include <vector>
#include <map>
#include <string>
#include <iostream>
#include <sensors/sensors.h>
#include <jni.h>
#include <limits>

#include "jni_domain.h"
#include "device_bus_wrapper.h"
#include "device_info_wrapper.h"
#include "feature_info_wrapper.h"
#include "sub_feature_info_wrapper.h"
#include "sensor_data_wrapper.h"

namespace jsensors {

extern std::vector<sensor_chip_name_p> detected_chips;
extern std::multimap<int, sensor_feature_p> chip_features;
extern std::multimap<feature_key, sensor_subfeature_p, feature_key_comp> chip_sub_features;
extern bool sensors_ready;

bool init();

std::string get_name(sensor_chip_name_p chip);
jobjectArray create_device_infos(JNIEnv * env);
jobjectArray create_device_feature_infos(JNIEnv * env, jobject device_info);
jobjectArray create_device_sub_feature_infos(JNIEnv * env, jobject feature_info);
jobject get_sub_feature_value(JNIEnv * env, jobject sub_feature_info);

void cleanup();

};

#endif