#ifndef __SENSOR_DEVICE_H__
#define __SENSOR_DEVICE_H__

#include "device_types.h"
#include "monitor.h"
#include <sensors/sensors.h>
#include <vector>
#include <map>
#include <string>

extern snsmon::monitor * monitor;

void cpp_sensemon_init();

bool cpp_sensemon_is_initialized();
bool cpp_sensemon_is_ready();

int cpp_sensemon_get_device_count();
const char * cpp_sensemon_get_device_name(const int & index);
sensemon_device_info_t cpp_sensemon_get_device_info(const char * device_name);

int cpp_sensemon_get_feature_count(const char * device_name);
int cpp_sensemon_get_feature_number(const char * device_name, const int & index);
sensemon_device_feature_info_t cpp_sensemon_get_feature_info(const char * device_name, const int & number);

int cpp_sensemon_get_subfeature_count(const char * device_name, const int & feature_number);
int cpp_sensemon_get_subfeature_number(const char * device_name, const int & feature_number, const int & index);
sensemon_device_subfeature_info_t cpp_sensemon_get_subfeature_info(const char * device_name, const int & feature_number, const int & subfeature_number);
sensemon_device_subfeature_value_t cpp_sensemon_get_subfeature_value(const char * device_name, const int & feature_number, const int & subfeature_number);

void cpp_sensemon_dispose();

#endif