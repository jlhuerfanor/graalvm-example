#ifndef __SENSEMON_DEVICE_SUBFEATURE_H__
#define __SENSEMON_DEVICE_SUBFEATURE_H__

#include "device_types.h"
#include <sensors/sensors.h>

namespace snsmon
{

class device_subfeature
{
private:
    bool ready;
    const sensors_chip_name * chip;
    const sensors_subfeature * subfeature;
    sensemon_device_subfeature_info_t info_cache;
    /* data */
public:
    device_subfeature(
        bool ready,
        const sensors_chip_name * chip,
        const sensors_subfeature * subfeature);
    device_subfeature(device_subfeature & other);
    ~device_subfeature();

    void setup();
    const sensemon_device_subfeature_info_t & get_info() const;
    sensemon_device_subfeature_value_t get_value() const;
};

typedef device_subfeature * device_subfeature_p;

};

#endif