#include "sensor_data_wrapper.h"

namespace jsensors {

sensor_data_wrapper::sensor_data_wrapper(JNIEnv * env, jobject sensor_data) {
    this->cls = env->GetObjectClass(sensor_data);
    this->env = env;
    this->sensor_data = sensor_data;
    
    this->set_fields();
}

sensor_data_wrapper::sensor_data_wrapper(JNIEnv * env) {
    this-> cls = env->FindClass(SENSOR_DATA_CLASS);

    jmethodID constructorId = env->GetMethodID(cls, "<init>", "()V");

    this->env = env;
    this->sensor_data = env->NewObject(cls, constructorId);
    this->set_fields();
}

sensor_data_wrapper::~sensor_data_wrapper() { }

void sensor_data_wrapper::set_fields() {
    this->field_success = env->GetFieldID(this->cls, "success", "Z");
    this->field_value = env->GetFieldID(this->cls, "value", "D");
}

jboolean sensor_data_wrapper::get_success() const {
    return env->GetBooleanField(this->sensor_data, this->field_success);
}

jdouble sensor_data_wrapper::get_value() const {
    return env->GetDoubleField(this->sensor_data, this->field_value);
}

jobject sensor_data_wrapper::get_this() const {
    return this->sensor_data;
}

void sensor_data_wrapper::set_success(const jboolean & value) {
    return env->SetBooleanField(this->sensor_data, this->field_success, value);
}

void sensor_data_wrapper::set_value(const jdouble & value) {
    return env->SetDoubleField(this->sensor_data, this->field_value, value);
}

};