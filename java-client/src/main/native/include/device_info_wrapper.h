#ifndef __DEVICE_INFO_WRAPPER_H__
#define __DEVICE_INFO_WRAPPER_H__

#include <jni.h>
#include <sensors/sensors.h>
#include "device_bus_wrapper.h"

namespace jsensors {

class device_info_wrapper
{
private:
    JNIEnv * env;
    jobject device_info;

    jclass obj_class;
    jfieldID field_prefix;
    jfieldID field_bus;
    jfieldID field_path;
    jfieldID field_address;
    jfieldID field_name;
    jfieldID field_system_id;

    void set_field_ids(jclass cls);
public:
    device_info_wrapper(JNIEnv * env, jobject device_info);
    device_info_wrapper(JNIEnv * env, const int & system_id, const sensors_chip_name * chip);
    ~device_info_wrapper();

    jobject get_this() const;
    std::string get_prefix() const;
    device_bus_wrapper get_device_bus() const;
    std::string get_path() const;
    jint get_address() const;
    std::string get_name() const;
    jint get_system_id() const;
    
    void set_prefix(const std::string & value);
    void set_device_bus(const device_bus_wrapper & value);
    void set_path(const std::string & value);
    void set_address(const jint & value);
    void set_name(const std::string & value);
    void set_system_id(const jint & value);
};

}
#endif