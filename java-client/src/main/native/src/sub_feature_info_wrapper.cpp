#include "sub_feature_info_wrapper.h"

namespace jsensors
{

sub_feature_info_wrapper::sub_feature_info_wrapper(JNIEnv * env, jobject sub_feature_info)
{
    auto cls = env->GetObjectClass(sub_feature_info);

    this->env = env;
    this->sub_feature_info = sub_feature_info;
    this->set_field_ids(cls);
}

sub_feature_info_wrapper::sub_feature_info_wrapper(JNIEnv * env, jobject feature, const sensors_subfeature * sub_feature) 
{
    auto cls = env->FindClass(SUB_FEATURE_INFO_CLASS);
    auto constructor_id = env->GetMethodID(cls, "<init>", "()V");

    this->env = env;
    this->sub_feature_info = env->NewObject(cls, constructor_id);
    this->set_field_ids(cls);

    this->set_name(std::string(sub_feature->name));
    this->set_number(sub_feature->number);
    this->set_type(ENUM_FROM_CODE(env, SUB_FEATURE_TYPE, sub_feature->type));
    this->set_mapping(sub_feature->mapping);
    this->set_flags(sub_feature->flags);
    this->set_feature_info(feature);
}

sub_feature_info_wrapper::~sub_feature_info_wrapper()
{
}

void sub_feature_info_wrapper::set_field_ids(jclass cls) {
    this->field_name_id = this->env->GetFieldID(cls, "name", STRING_SIGNATURE);
    this->field_number_id = this->env->GetFieldID(cls, "number", "I");
    this->field_type_id = this->env->GetFieldID(cls, "type", SUB_FEATURE_TYPE_ENUM_SIGNATURE);
    this->field_mapping_id = this->env->GetFieldID(cls, "mapping", "I");
    this->field_flags_id = this->env->GetFieldID(cls, "flags", "I");
    this->field_feature_info_id = this->env->GetFieldID(cls, "featureInfo", FEATURE_INFO_SIGANTURE);
}

std::string sub_feature_info_wrapper::get_name() const {
    auto value = (jstring) (env->GetObjectField(this->sub_feature_info, field_name_id));
    return to_string(env, value);
}
jint sub_feature_info_wrapper::get_number() const {
    return env->GetIntField(this->sub_feature_info, field_number_id);
}
jobject sub_feature_info_wrapper::get_type() const {
    return env->GetObjectField(this->sub_feature_info, field_type_id);
}
jint sub_feature_info_wrapper::get_mapping() const {
    return env->GetIntField(this->sub_feature_info, field_mapping_id);
}
jint sub_feature_info_wrapper::get_flags() const {
    return env->GetIntField(this->sub_feature_info, field_flags_id);
}
feature_info_wrapper sub_feature_info_wrapper::get_feature_info() const {
    auto feature_info = env->GetObjectField(this->sub_feature_info, field_feature_info_id);
    return feature_info_wrapper(env, feature_info);
}
jobject sub_feature_info_wrapper::get_this() const {
    return this->sub_feature_info;
}

void sub_feature_info_wrapper::set_name(const std::string & value) {
    jstring str = env->NewStringUTF(value.c_str());
    env->SetObjectField(this->sub_feature_info, field_name_id, str);
}
void sub_feature_info_wrapper::set_number(const jint & value) {
    env->SetIntField(this->sub_feature_info, field_number_id, value);
}
void sub_feature_info_wrapper::set_type(jobject value) {
    env->SetObjectField(this->sub_feature_info, field_type_id, value);
}
void sub_feature_info_wrapper::set_mapping(const jint & value) {
    env->SetIntField(this->sub_feature_info, field_mapping_id, value);
}
void sub_feature_info_wrapper::set_flags(const jint & value) {
    env->SetIntField(this->sub_feature_info, field_flags_id, value);
}
void sub_feature_info_wrapper::set_feature_info(jobject value) {
    env->SetObjectField(this->sub_feature_info, field_feature_info_id, value);
}

} // namespace jsensors
