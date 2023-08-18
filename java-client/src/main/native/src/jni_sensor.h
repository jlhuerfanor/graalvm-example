#ifndef __JNI_SENSOR_H__
#define __JNI_SENSOR_H__

#include <vector>
#include <map>
#include <string>
#include <sensors/sensors.h>
#include <jni.h>

typedef const sensors_chip_name * sensor_chip_name_p;

namespace jsensors {

typedef char * char_p;

extern std::map<std::string, sensor_chip_name_p> detected_chips;

bool init();

std::string get_name(sensor_chip_name_p chip);

jobjectArray create_device_infos(JNIEnv * env);

jobjectArray create_device_feature_infos(JNIEnv * env, jobject device_info);

void cleanup();

};

#endif