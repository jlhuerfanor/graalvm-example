#ifndef __DEVICE_BUS_WRAPPER_H__
#define __DEVICE_BUS_WRAPPER_H__

#include <jni.h>
#include <sensors/sensors.h>
#include "jni_domain.h"

namespace jsensors {

class device_bus_wrapper
{
private:
    JNIEnv * env;
    jobject device_bus;

    jfieldID field_type;
    jfieldID field_number;

    void set_field_ids(jclass cls);
public:
    device_bus_wrapper(JNIEnv * env, jobject device_bus);
    device_bus_wrapper(JNIEnv * env, const sensors_bus_id & bus_id);
    ~device_bus_wrapper();

    jobject get_this() const;
    jobject get_type() const;
    void set_type(jobject value);

    jint get_number() const;
    void set_number(jint value);
};


};

#endif