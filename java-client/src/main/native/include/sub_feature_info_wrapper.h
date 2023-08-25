#ifndef __SUB_FEATURE_INFO_WRAPPER_H__
#define __SUB_FEATURE_INFO_WRAPPER_H__

#include <jni.h>
#include <sensors/sensors.h>
#include <iostream>
#include "jni_domain.h"
#include "feature_info_wrapper.h"

namespace jsensors {

class sub_feature_info_wrapper
{
private:
    JNIEnv * env;
    jobject sub_feature_info;

    jfieldID field_name_id;
    jfieldID field_number_id;
    jfieldID field_type_id;
    jfieldID field_mapping_id;
    jfieldID field_flags_id;
    jfieldID field_feature_info_id;

    void set_field_ids(jclass cls);
public:
    sub_feature_info_wrapper(JNIEnv * env, jobject sub_feature_info);
    sub_feature_info_wrapper(JNIEnv * env, jobject feature, const sensors_subfeature * sub_feature);
    ~sub_feature_info_wrapper();

    std::string get_name() const;
    jint get_number() const;
    jobject get_type() const;
    jint get_mapping() const;
    jint get_flags() const;
    feature_info_wrapper get_feature_info() const;
    jobject get_this() const;

    void set_name(const std::string & value);
    void set_number(const jint & value);
    void set_type(jobject value);
    void set_mapping(const jint & value);
    void set_flags(const jint & value);
    void set_feature_info(jobject value);
};




};

#endif