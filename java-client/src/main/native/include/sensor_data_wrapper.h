#ifndef __SENSOR_DATA_WRAPPER_H__
#define __SENSOR_DATA_WRAPPER_H__

#include <jni.h>
#include "jni_domain.h"

namespace jsensors {
    
class sensor_data_wrapper
{
private:
    JNIEnv * env;
    jclass cls;
    jobject sensor_data;

    jfieldID field_failed;
    jfieldID field_value;

    void set_fields();
public:
    sensor_data_wrapper(JNIEnv * env, jobject sensor_data);
    sensor_data_wrapper(JNIEnv * env);
    ~sensor_data_wrapper();

    jboolean get_failed() const;
    jdouble get_value() const;
    jobject get_this() const;

    void set_failed(const jboolean & value);
    void set_value(const jdouble & value);
};


};

#endif