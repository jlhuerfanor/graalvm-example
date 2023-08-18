#include "../include/monitor.h"
#include <iostream>
#include <cstdio>

void print_features(const snsmon::device_p & device);
void print_sub_features(const snsmon::device_feature_p & feature);

int main() {
    snsmon::monitor m;

    if(m.is_ready()) {
        m.setup();
        try {
            for(auto name : m.get_device_names()) {
                auto device = m.get_device(name);

                std::cout << "Detected sensor: " << name
                    << std::endl << "Path: " << device->get_info().path
                    << std::endl << "Type: " << device->get_info().bus_type
                    << std::endl;

                print_features(device);
            }
        } catch(std::exception & ex) {
            std::cout << ex.what() << std::endl;
        }

    } else {
        std::cout << "Could not initialize sensors" << std::endl;
        return 1;
    }
}

void print_features(const snsmon::device_p & device) {
    for(auto feature_number : device->get_feature_numbers()) {
        auto feature = device->get_feature(feature_number);
        
        std::cout << "\tFeature [" << (feature_number) << "]: " << feature->get_info().name
                << std::endl << "\tNumber: " << feature->get_info().number
                << std::endl << "\tType: " << feature->get_info().type
                << std::endl;

        print_sub_features(feature);
    }
}

void print_sub_features(const snsmon::device_feature_p & feature) {
    for(auto subfeature_number : feature->get_subfeature_numbers()) {
        auto subfeature = feature->get_subfeature(subfeature_number);

        std::cout << "\t\tSubfeature [" << (subfeature_number) << "]: " << subfeature->get_info().name
                << std::endl << "\t\tNumber: " << subfeature->get_info().number
                << std::endl << "\t\tType: " << subfeature->get_info().type
                << std::endl;
    }

}