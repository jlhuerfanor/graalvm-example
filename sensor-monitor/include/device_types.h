#ifndef __SENSEMON_DEVICE_TYPES_H__
#define __SENSEMON_DEVICE_TYPES_H__

#ifdef GRAALVM_COMPILER
#include <graalvm/llvm/polyglot.h>
#endif

#ifdef __cplusplus
extern "C" {
#endif

typedef struct {
    const char * path;
    int bus_type;
    const char * name;
} sensemon_device_info_t;

typedef struct {
    int number;
    int type;
    const char * name;
} sensemon_device_feature_info_t;

typedef struct {
    int number;
    int type;
    const char * name;
} sensemon_device_subfeature_info_t;

typedef struct 
{
    bool fail;
    double value;
} sensemon_device_subfeature_value_t;


#ifdef GRAALVM_COMPILER

POLYGLOT_DECLARE_TYPE(sensemon_device_info_t)
POLYGLOT_DECLARE_TYPE(sensemon_device_feature_info_t)
POLYGLOT_DECLARE_TYPE(sensemon_device_subfeature_info_t)

#endif


void sensemon_init();

bool sensemon_is_initialized();
bool sensemon_is_ready();

int sensemon_get_device_count();
const char * sensemon_get_device_name(const int & index);
sensemon_device_info_t sensemon_get_device_info(const char * device_name);

int sensemon_get_feature_count(const char * device_name);
int sensemon_get_feature_number(const char * device_name, const int & index);
sensemon_device_feature_info_t sensemon_get_feature_info(const char * device_name, const int & number);

int sensemon_get_subfeature_count(const char * device_name, const int & feature_number);
int sensemon_get_subfeature_number(const char * device_name, const int & feature_number, const int & index);
sensemon_device_subfeature_info_t sensemon_get_subfeature_info(const char * device_name, const int & feature_number, const int & subfeature_number);
sensemon_device_subfeature_value_t sensemon_get_subfeature_value(const char * device_name, const int & feature_number, const int & subfeature_number);

void sensemon_dispose();

#ifdef __cplusplus
}
#endif

#endif