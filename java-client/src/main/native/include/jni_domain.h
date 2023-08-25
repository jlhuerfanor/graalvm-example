#ifndef __JNI_DOMAIN_CODE_H__
#define __JNI_DOMAIN_CODE_H__

#define BUS_TYPE_ENUM "org/example/sensemon/application/model/BusType"
#define FEATURE_TYPE_ENUM "org/example/sensemon/application/model/FeatureType"
#define SUB_FEATURE_TYPE_ENUM "org/example/sensemon/application/model/SubFeatureType"

#define DEVICE_BUS_CLASS "org/example/sensemon/application/model/DeviceBus"
#define DEVICE_INFO_CLASS "org/example/sensemon/application/model/DeviceInfo"
#define FEATURE_INFO_CLASS "org/example/sensemon/application/model/FeatureInfo"
#define SUB_FEATURE_INFO_CLASS "org/example/sensemon/application/model/SubFeatureInfo"

#define STRING_SIGNATURE "Ljava/lang/String;"
#define DEVICE_BUS_SIGNATURE "Lorg/example/sensemon/application/model/DeviceBus;"
#define DEVICE_INFO_SIGNATURE "Lorg/example/sensemon/application/model/DeviceInfo;"
#define FEATURE_INFO_SIGANTURE "Lorg/example/sensemon/application/model/FeatureInfo;"

#define BUS_TYPE_ENUM_SIGNATURE "Lorg/example/sensemon/application/model/BusType;"
#define FEATURE_TYPE_ENUM_SIGNATURE "Lorg/example/sensemon/application/model/FeatureType;"
#define SUB_FEATURE_TYPE_ENUM_SIGNATURE "Lorg/example/sensemon/application/model/SubFeatureType;"

#define BUS_TYPE_FROM_CODE_SIGNATURE "(I)Lorg/example/sensemon/application/model/BusType;"
#define FEATURE_TYPE_FROM_CODE_SIGNATURE "(I)Lorg/example/sensemon/application/model/FeatureType;"
#define SUB_FEATURE_TYPE_FROM_CODE_SIGNATURE "(I)Lorg/example/sensemon/application/model/SubFeatureType;"

#define ENUM_FROM_CODE(env, enum_type, code) \
    jsensors::get_enum_by_id(env, enum_type##_ENUM, enum_type##_FROM_CODE_SIGNATURE, code)

#include <jni.h>
#include <string>
#include <cstring>
#include <sensors/sensors.h>

namespace jsensors {

typedef const sensors_chip_name * sensor_chip_name_p;
typedef const sensors_feature * sensor_feature_p;
typedef const sensors_subfeature * sensor_subfeature_p;

typedef struct {
    int device_number;
    int feature_number;
} feature_key;

typedef struct {
    int device_number;
    int feature_number;
    int sub_feature_number;
} sub_feature_key;

typedef struct {
    bool operator()(const feature_key & a, const feature_key & b);
} feature_key_comp;

std::string get_chip_name(sensor_chip_name_p chip);
std::string to_string(JNIEnv * env, jstring value);
jobject get_enum_by_id(JNIEnv * env, const char * class_name, const char * signature, int value);

void vmprintln(JNIEnv * env, const std::string & value);
void vmprint(JNIEnv * env, const std::string & value);

};

#endif