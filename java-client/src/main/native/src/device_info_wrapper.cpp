#include "device_info_wrapper.h"

namespace jsensors {

device_info_wrapper::device_info_wrapper(JNIEnv * env, jobject device_info) {
    auto cls = env->GetObjectClass(device_info);
    this->env = env;
    this->device_info = device_info;
    this->set_field_ids(cls);
}

device_info_wrapper::device_info_wrapper(JNIEnv * env, const int & system_id, const sensors_chip_name * chip) {
    auto cls = env->FindClass(DEVICE_INFO_CLASS);
    auto constructorId = env->GetMethodID(cls, "<init>", "()V");
    auto name = get_chip_name(chip);

    this->env = env;
    this->device_info = env->NewObject(cls, constructorId);
    this->set_field_ids(cls);

    this->set_prefix(std::string(chip->prefix));
    this->set_path(std::string(chip->path));
    this->set_address(chip->addr);
    this->set_device_bus(device_bus_wrapper(env, chip->bus));
    this->set_name(get_chip_name(chip));
    this->set_system_id(system_id);
}

void device_info_wrapper::set_field_ids(jclass cls) {
    this->field_prefix = env->GetFieldID(cls, "prefix", STRING_SIGNATURE);
    this->field_bus = env->GetFieldID(cls, "bus", DEVICE_BUS_SIGNATURE);
    this->field_path = env->GetFieldID(cls, "path", STRING_SIGNATURE);
    this->field_address = env->GetFieldID(cls, "address", "I");
    this->field_name = env->GetFieldID(cls, "name", STRING_SIGNATURE);
    this->field_system_id = env->GetFieldID(cls, "systemId", "I");
}

device_info_wrapper::~device_info_wrapper() { }

jobject device_info_wrapper::get_this() const {
    return this->device_info;
}

std::string device_info_wrapper::get_prefix() const {
    auto value = (jstring) (env->GetObjectField(this->device_info, field_prefix));
    
    return to_string(env, value);
}
device_bus_wrapper device_info_wrapper::get_device_bus() const {
    auto value = env->GetObjectField(this->device_info, field_bus);
    
    return device_bus_wrapper(env, value);
}
std::string device_info_wrapper::get_path() const {
    auto value = (jstring) (env->GetObjectField(this->device_info, field_path));
    
    return to_string(env, value);
}
jint device_info_wrapper::get_address() const {
    return env->GetIntField(this->device_info, field_address);
}
std::string device_info_wrapper::get_name() const {
    auto value = (jstring) (env->GetObjectField(this->device_info, field_name));
    
    return to_string(env, value);
}

jint device_info_wrapper::get_system_id() const {
    return env->GetIntField(this->device_info, field_system_id);
}

void device_info_wrapper::set_prefix(const std::string & value) {
    jstring str = env->NewStringUTF(value.c_str());
    env->SetObjectField(this->device_info, field_prefix, str);
}
void device_info_wrapper::set_device_bus(const device_bus_wrapper & value) {
    env->SetObjectField(this->device_info, field_bus, value.get_this());
}
void device_info_wrapper::set_path(const std::string & value) {
    jstring str = env->NewStringUTF(value.c_str());
    env->SetObjectField(this->device_info, field_path, str);
}
void device_info_wrapper::set_address(const jint & value) {
    env->SetIntField(this->device_info, field_address, value);
}
void device_info_wrapper::set_name(const std::string & value) {
    jstring str = env->NewStringUTF(value.c_str());
    env->SetObjectField(this->device_info, field_name, str);
}

void device_info_wrapper::set_system_id(const jint & value) {
    env->SetIntField(this->device_info, field_system_id, value);
}

}