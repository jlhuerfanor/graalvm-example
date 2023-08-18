#include "../include/device.h"

namespace snsmon
{

device::device(device & t) {
    this->ready = t.ready;
    this->chip = t.chip;
    this->info_cache = t.info_cache;
    this->feature_numbers = t.feature_numbers;
    this->features = t.features;
}
    
device::device(bool ready, const sensors_chip_name * chip)
{
    this->ready = ready;
    this->chip = chip;
    this->info_cache = { NULL, 0, NULL };
}

device::~device()
{
    if(this->info_cache.name) {
        delete [] this->info_cache.name;
    }
    for(auto p : this->features) {
        delete p.second;
    }
    this->features.clear();
}

void device::setup() {
    if(ready) {
        int len = sensors_snprintf_chip_name(NULL, 0, chip) + 1;
        char * const name = new char[len];
        const sensors_feature * current;
        int number = 0;

        sensors_snprintf_chip_name(name, len, chip);

        this->info_cache = {
            this->chip->path,
            this->chip->bus.type,
            name
        };

        while((current = sensors_get_features(this->chip, &number))) {
            this->feature_numbers.push_back(current->number);
            this->features[current->number] = new device_feature(ready, chip, current);
            this->features[current->number]->setup();
        }
    }
}

const sensemon_device_info_t & device::get_info() const {
    return this->info_cache;
}

const device_feature_p device::get_feature(const int & number) const {
    auto found = this->features.find(number);

    return found != this->features.end()
            ? found->second
            : nullptr;
}

const std::vector<int> & device::get_feature_numbers() const {
    return this->feature_numbers;
}

};