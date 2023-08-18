#include "../include/sensemon.h"

#ifdef __cplusplus
extern "C" {
#endif

void sensemon_init() {
    cpp_sensemon_init();
}

bool sensemon_is_initialized() {
    return cpp_sensemon_is_initialized();
}
bool sensemon_is_ready() {
    return cpp_sensemon_is_ready();
}

int sensemon_get_device_count() {
    return cpp_sensemon_get_device_count();
}
const char * sensemon_get_device_name(const int & index) {
    return cpp_sensemon_get_device_name(index);
}
sensemon_device_info_t sensemon_get_device_info(const char * device_name) {
    return cpp_sensemon_get_device_info(device_name);
}

int sensemon_get_feature_count(const char * device_name) {
    return cpp_sensemon_get_feature_count(device_name);
}
int sensemon_get_feature_number(const char * device_name, const int & index) {
    return cpp_sensemon_get_feature_number(device_name, index);
}
sensemon_device_feature_info_t sensemon_get_feature_info(const char * device_name, const int & number) {
    return cpp_sensemon_get_feature_info(device_name, number);
}

int sensemon_get_subfeature_count(const char * device_name, const int & feature_number) {
    return cpp_sensemon_get_subfeature_count(device_name, feature_number);
}
int sensemon_get_subfeature_number(const char * device_name, const int & feature_number, const int & index) {
    return cpp_sensemon_get_subfeature_number(device_name, feature_number, index);
}
sensemon_device_subfeature_info_t sensemon_get_subfeature_info(const char * device_name, const int & feature_number, const int & subfeature_number) {
    return cpp_sensemon_get_subfeature_info(device_name, feature_number, subfeature_number);
}
sensemon_device_subfeature_value_t sensemon_get_subfeature_value(const char * device_name, const int & feature_number, const int & subfeature_number) {
    return cpp_sensemon_get_subfeature_value(device_name, feature_number, subfeature_number);
}

void sensemon_dispose() {
    cpp_sensemon_dispose();
}

#ifdef __cplusplus
}
#endif

snsmon::monitor * monitor = nullptr;
sensemon_device_info_t empty_device_info { NULL, 0, NULL };
sensemon_device_feature_info_t empty_feature_info { 0, 0, NULL };
sensemon_device_subfeature_info_t empty_subfeature_info { 0, 0, NULL};
sensemon_device_subfeature_value_t empty_subfeature_value { true, 0.0 };

void cpp_sensemon_init() {
    monitor = new snsmon::monitor();
    monitor->setup();
}

bool cpp_sensemon_is_initialized() {
    return monitor != nullptr;
}

bool cpp_sensemon_is_ready() {
    return monitor != nullptr
        ? monitor->is_ready()
        : false;
}

int cpp_sensemon_get_device_count() {
    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device_names().size()
            : 0;
}
const char * cpp_sensemon_get_device_name(const int & index) {
    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device_names()[index].c_str()
            : nullptr;
}
sensemon_device_info_t cpp_sensemon_get_device_info(const char * device_name) {
    std::string name(device_name);
    

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_info()
            : empty_device_info;
}

int cpp_sensemon_get_feature_count(const char * device_name) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature_numbers().size()
            : 0;
}
int cpp_sensemon_get_feature_number(const char * device_name, const int & index) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature_numbers()[index]
            : -1;
}
sensemon_device_feature_info_t cpp_sensemon_get_feature_info(const char * device_name, const int & number) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature(number)->get_info()
            : empty_feature_info;
}

int cpp_sensemon_get_subfeature_count(const char * device_name, const int & feature_number) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature(feature_number)->get_subfeature_numbers().size()
            : 0;
}
int cpp_sensemon_get_subfeature_number(const char * device_name, const int & feature_number, const int & index) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature(feature_number)->get_subfeature_numbers()[index]
            : -1;
}
sensemon_device_subfeature_info_t cpp_sensemon_get_subfeature_info(const char * device_name, const int & feature_number, const int & subfeature_number) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature(feature_number)->get_subfeature(subfeature_number)->get_info()
            : empty_subfeature_info;
}
sensemon_device_subfeature_value_t cpp_sensemon_get_subfeature_value(const char * device_name, const int & feature_number, const int & subfeature_number) {
    std::string name(device_name);

    return (monitor != nullptr && monitor->is_ready())
            ? monitor->get_device(name)->get_feature(feature_number)->get_subfeature(subfeature_number)->get_value()
            : empty_subfeature_value;
}

void cpp_sensemon_dispose() {
    delete monitor;
    monitor = nullptr;
}