#ifndef __SENSMON_DEVICE_MONITOR_H__
#define __SENSMON_DEVICE_MONITOR_H__

#include "device_types.h"
#include "device_feature.h"
#include <map>
#include <string>

namespace snsmon {
class device
{
private:
    bool ready;
    const sensors_chip_name * chip;
    sensemon_device_info_t info_cache;
    std::vector<int> feature_numbers;
    std::map<int, device_feature_p> features;
    /* data */
public:
    device(bool ready, const sensors_chip_name * chip);
    device(device & t);
    ~device();

    void setup();
    const sensemon_device_info_t & get_info() const;
    const device_feature_p get_feature(const int & number) const;
    const std::vector<int> & get_feature_numbers() const;
};

typedef device * device_p;

};


#endif