#ifndef __SENSEMON_DEVICE_FEATURE_H__
#define __SENSEMON_DEVICE_FEATURE_H__

#include "device_types.h"
#include "device_subfeature.h"

#include <map>
#include <sensors/sensors.h>

namespace snsmon
{

class device_feature
{
private:
    bool ready;
    const sensors_chip_name * chip;
    const sensors_feature * feature;
    sensemon_device_feature_info_t info_cache;
    std::vector<int> subfeature_numbers;
    std::map<int, device_subfeature_p> subfeatures;
    /* data */
public:
    device_feature(
        bool ready,
        const sensors_chip_name * chip,
        const sensors_feature * feature);
    device_feature(device_feature & other);
    ~device_feature();
    
    void setup();

    const sensemon_device_feature_info_t & get_info() const;
    const std::vector<int> & get_subfeature_numbers() const;
    const device_subfeature_p get_subfeature(const int & number) const;
};

typedef device_feature * device_feature_p;

};



#endif