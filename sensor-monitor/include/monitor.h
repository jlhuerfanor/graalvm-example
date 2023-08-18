#ifndef __SENSEMON_MONITOR_H__
#define __SENSEMON_MONITOR_H__

#include "device_types.h"
#include "device.h"
#include "monitor.h"
#include <sensors/sensors.h>
#include <map>
#include <string>

namespace snsmon
{

class monitor
{
private:
    bool ready;
    std::vector<std::string> device_names;
    std::map<std::string, device_p> devices;
public:
    monitor();
    ~monitor();

    void setup();

    bool is_ready() const;

    const std::vector<std::string> & get_device_names() const;
    const device_p get_device(const std::string & name) const;
};

}; // namespace snsmon


#endif