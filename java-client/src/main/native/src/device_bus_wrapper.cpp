#include "device_bus_wrapper.h"

namespace jsensors {
    
device_bus_wrapper::device_bus_wrapper(JNIEnv * env, jobject device_bus) {
    auto cls = env->GetObjectClass(device_bus);

    this->env = env;
    this->device_bus = device_bus;
    this->set_field_ids(cls);
}

device_bus_wrapper::device_bus_wrapper(JNIEnv * env, const sensors_bus_id & bus_id) {
    auto cls = env->FindClass(DEVICE_BUS_CLASS);
    auto constructorId = env->GetMethodID(cls, "<init>", "()V");

    this->env = env;
    this->device_bus = env->NewObject(cls, constructorId);
    this->set_field_ids(cls);
    this->set_type(ENUM_FROM_CODE(env, BUS_TYPE, bus_id.type));
}

void device_bus_wrapper::set_field_ids(jclass cls) {
    this->field_type = env->GetFieldID(cls, "type", BUS_TYPE_ENUM_SIGNATURE);
    this->field_number = env->GetFieldID(cls, "number", "I");
}

device_bus_wrapper::~device_bus_wrapper() { }

jobject device_bus_wrapper::get_this() const {
    return this->device_bus;
}

jobject device_bus_wrapper::get_type() const {
    return env->GetObjectField(this->device_bus, this->field_type);
}

jint device_bus_wrapper::get_number() const {
    return env->GetIntField(this->device_bus, this->field_number);
}

void device_bus_wrapper::set_type(jobject value) {
    env->SetObjectField(this->device_bus, this->field_type, value);
}

void device_bus_wrapper::set_number(jint value) {
    env->SetIntField(this->device_bus, this->field_number, value);
}

}