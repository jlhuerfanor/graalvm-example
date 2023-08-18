#include "../include/device_subfeature.h"

namespace snsmon
{
    
device_subfeature::device_subfeature(
    bool ready,
    const sensors_chip_name * chip,
    const sensors_subfeature * subfeature)
{
    this->ready = ready;
    this->chip = chip;
    this->subfeature = subfeature;
}

device_subfeature::device_subfeature(device_subfeature & other) 
{
    this->ready = other.ready;
    this->chip = other.chip;
    this->subfeature = other.subfeature;
    this->info_cache = other.info_cache;
}

device_subfeature::~device_subfeature()
{
}

void device_subfeature::setup() {
    this->info_cache = {
        this->subfeature->number,
        this->subfeature->type,
        this->subfeature->name
    };
}

sensemon_device_subfeature_value_t device_subfeature::get_value() const {
    sensemon_device_subfeature_value_t value = { true, 0.0 };

    value.fail = sensors_get_value(chip, subfeature->number, &(value.value));
    return value;
}

const sensemon_device_subfeature_info_t & device_subfeature::get_info() const {
    return this->info_cache;
}

};