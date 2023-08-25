#include "feature_info_wrapper.h"

namespace jsensors {


feature_info_wrapper::feature_info_wrapper(JNIEnv * env, jobject feature_info) {
    auto cls = env->GetObjectClass(feature_info);

    this->env = env;
    this->feature_info = feature_info;
    this->set_field_ids(cls);
}

feature_info_wrapper::feature_info_wrapper(JNIEnv * env, jobject device_info, const sensors_feature * feature) {
    auto cls = env->FindClass(FEATURE_INFO_CLASS);
    auto constructorId = env->GetMethodID(cls, "<init>", "()V");

    this->env = env;
    this->feature_info = env->NewObject(cls, constructorId);
    this->set_field_ids(cls);

    this->set_device_info(device_info);
    this->set_number(feature->number);
    this->set_name(std::string(feature->name));
    this->set_feature_type(ENUM_FROM_CODE(env, FEATURE_TYPE, feature->type));
}

void feature_info_wrapper::set_field_ids(jclass cls) {
    this->field_name = env->GetFieldID(cls, "name", STRING_SIGNATURE);
    this->field_number = env->GetFieldID(cls, "number", "I");
    this->field_feature_type = env->GetFieldID(cls, "featureType", FEATURE_TYPE_ENUM_SIGNATURE);
    this->field_device_info = env->GetFieldID(cls, "deviceInfo", DEVICE_INFO_SIGNATURE);
}

feature_info_wrapper::~feature_info_wrapper() { }

std::string feature_info_wrapper::get_name() const {
    auto value = (jstring)(env->GetObjectField(feature_info, field_name));
    return to_string(env, value);
}
jint feature_info_wrapper::get_number() const {
    return env->GetIntField(feature_info, field_number);
}
jobject feature_info_wrapper::get_feature_type() const {
    return env->GetObjectField(feature_info, field_feature_type);    
}
device_info_wrapper feature_info_wrapper::get_device_info() const {
    auto value = env->GetObjectField(feature_info, field_device_info);
    return device_info_wrapper(env, value);
}

jobject feature_info_wrapper::get_this() const {
    return this->feature_info;
}

void feature_info_wrapper::set_name(const std::string & value) const {
    auto str = env->NewStringUTF(value.c_str());
    env->SetObjectField(feature_info, field_name, str);
}
void feature_info_wrapper::set_number(const jint & value) const {
    env->SetIntField(feature_info, field_number, value);
}
void feature_info_wrapper::set_feature_type(jobject value) const {
    env->SetObjectField(feature_info, field_feature_type, value);
}
void feature_info_wrapper::set_device_info(jobject value) const {
    env->SetObjectField(feature_info, field_device_info, value);
}


}