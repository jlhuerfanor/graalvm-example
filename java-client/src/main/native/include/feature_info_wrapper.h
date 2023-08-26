#ifndef __FEATURE_INFO_WRAPPER_H__
#define __FEATURE_INFO_WRAPPER_H__

#include <jni.h>
#include <sensors/sensors.h>
#include "device_bus_wrapper.h"
#include "device_info_wrapper.h"

namespace jsensors
{

class feature_info_wrapper
{
private:
    JNIEnv * env;
    jobject feature_info;

    jfieldID field_name;
    jfieldID field_number;
    jfieldID field_feature_type;
    jfieldID field_device_info;
    jfieldID field_system_id;

    void set_field_ids(jclass cls);
public:
    feature_info_wrapper(JNIEnv * env, jobject feature_info);
    feature_info_wrapper(JNIEnv * env, jobject device_info, const int & system_id, const sensors_feature * feature);
    ~feature_info_wrapper();

    std::string get_name() const;
    jint get_number() const;
    jobject get_feature_type() const;
    device_info_wrapper get_device_info() const;
    jint get_system_id() const;
    jobject get_this() const;
    
    void set_name(const std::string & value) const;
    void set_number(const jint & value) const;
    void set_feature_type(jobject value) const;
    void set_device_info(jobject value) const;
    void set_system_id(const jint & value) const;
};

}



#endif