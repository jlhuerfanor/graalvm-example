#include "../include/monitor.h"

namespace snsmon {

monitor::monitor(/* args */)
{
    this->ready = sensors_init(NULL) == 0;
}

monitor::~monitor()
{
    for(auto p : this->devices) {
        delete p.second;
    }
    this->devices.clear();

    sensors_cleanup();
}

void monitor::setup() {
    if(this->ready) {
        int number = 0;
        const sensors_chip_name * current;

        while((current = sensors_get_detected_chips(NULL, &number))) {
            device_p dev = new device(ready, current);
            
            dev->setup();

            std::string name(dev->get_info().name);

            this->device_names.push_back(name);
            this->devices[name] = dev;
        }
    }
}

const std::vector<std::string> & monitor::get_device_names() const {
    return this->device_names;
}

const device_p monitor::get_device(const std::string & name) const
{
    return this->devices.at(name);
}

bool monitor::is_ready() const {
    return this->ready;
}

};