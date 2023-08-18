#include "../include/device_feature.h"

namespace snsmon
{

device_feature::device_feature(
    bool ready,
    const sensors_chip_name * chip,
    const sensors_feature * feature)
{
    this->ready = ready;
    this->chip = chip;
    this->feature = feature;
}

device_feature::device_feature(device_feature & other) 
{
    this->ready = other.ready;
    this->chip = other.chip;
    this->feature = other.feature;
    this->info_cache = other.info_cache;
    this->subfeature_numbers = other.subfeature_numbers;
    this->subfeatures = other.subfeatures;
}

device_feature::~device_feature()
{
    for(auto p : this->subfeatures) {
        delete p.second;
    }
    
    this->subfeatures.clear();
}

void device_feature::setup() {
    if(this->ready) {
        int number = 0;
        const sensors_subfeature * current;

        this->info_cache = {
            this->feature->number,
            this->feature->type,
            this->feature->name
        };

        while((current = sensors_get_all_subfeatures(chip, feature, &number))) {
            this->subfeature_numbers.push_back(current->number);
            this->subfeatures[current->number] = new device_subfeature(ready, chip, current);
            this->subfeatures[current->number]->setup();
        }
    }
}

const sensemon_device_feature_info_t & device_feature::get_info() const {
    return this->info_cache;
}

const std::vector<int> & device_feature::get_subfeature_numbers() const {
    return this->subfeature_numbers;
}

const device_subfeature_p device_feature::get_subfeature(const int & number) const {
    auto found = this->subfeatures.find(number);

    return found != this->subfeatures.end()
            ? found->second
            : nullptr;
}
    
} // namespace sensemon
