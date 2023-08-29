#include "jni_sensor.h"

namespace jsensors {

std::vector<sensor_chip_name_p> detected_chips;
std::multimap<int, sensor_feature_p> chip_features;
std::multimap<feature_key, sensor_subfeature_p, feature_key_comp> chip_sub_features;
bool sensors_ready = false;

sensor_feature_p get_feature(const feature_key & key) {
    auto current = chip_features.lower_bound(key.device_number);
    auto last = chip_features.upper_bound(key.device_number);

    while(current != last) {
        if(current->first == key.device_number && current->second->number == key.feature_number) {
            return current->second;
        }
        current++;
    }

    return NULL;
}

sensor_subfeature_p get_sub_feature(const sub_feature_key & key) {
    feature_key feature_k = { key.device_number, key.feature_number };
    auto current = chip_sub_features.lower_bound(feature_k);
    auto last = chip_sub_features.upper_bound(feature_k);

    while(current != last) {
        if(current->first.device_number == key.device_number
                && current->first.feature_number == key.feature_number
                && current->second->number == key.sub_feature_number) {
            return current->second;
        }
        current++;
    }

    return NULL;
}

bool init() {
    if(!sensors_ready) { 
        sensors_ready = sensors_init(NULL) == 0;

        if(sensors_ready) {
            sensor_chip_name_p current_chip;
            int chip_number = 0;

            while((current_chip = sensors_get_detected_chips(NULL, &chip_number))) {
                sensor_feature_p current_feature;
                int feature_number = 0;

                detected_chips.push_back(current_chip);
                
                while((current_feature = sensors_get_features(current_chip, &feature_number))) {
                    sensor_subfeature_p current_subfeature;
                    int sub_feature_number = 0;

                    chip_features.insert(std::pair<int, sensor_feature_p>(chip_number - 1, current_feature));

                    while((current_subfeature = sensors_get_all_subfeatures(current_chip, current_feature, &sub_feature_number))) {
                        feature_key feature_k = { chip_number - 1, feature_number - 1 };
                        chip_sub_features.insert(std::pair<feature_key, sensor_subfeature_p>(feature_k, current_subfeature));
                    }
                }
            }
        } else {
            sensors_cleanup();
        }
    }

    return sensors_ready;
}

jobjectArray create_device_infos(JNIEnv * env) {
    if(sensors_ready) {
        jclass device_info_class = env->FindClass(DEVICE_INFO_CLASS);
        jobjectArray array = env->NewObjectArray(detected_chips.size(), device_info_class, NULL);
        int i = 0;

        for(auto entry : detected_chips) {
            jsensors::device_info_wrapper device(env, i, entry);
            env->SetObjectArrayElement(array, i++, device.get_this());
        }

        return array;
    }
    return NULL;
}

jobjectArray create_device_feature_infos(JNIEnv * env, jobject device_info) {
    if(sensors_ready) {
        jsensors::device_info_wrapper device(env, device_info);
        auto device_number = device.get_system_id();

        if(device_number < detected_chips.size()) {
            auto chip = detected_chips[device.get_system_id()];
            auto current = chip_features.lower_bound(device_number);
            auto last = chip_features.upper_bound(device_number);
            std::vector<jsensors::feature_info_wrapper> features;

            while(current != last) {
                if(current->first == device_number) {
                    jsensors::feature_info_wrapper feature(env, device_info, -1, current->second);
                    features.push_back(feature);
                }
                current++;
            }

            jclass cls = env->FindClass(FEATURE_INFO_CLASS);
            jobjectArray array = env->NewObjectArray(features.size(), cls, NULL);
            int i = 0;

            for(auto entry : features) {
                entry.set_system_id(i);
                env->SetObjectArrayElement(array, i++, entry.get_this());
            }

            return array;
        } 
    }

    return NULL;
}

jobjectArray create_device_sub_feature_infos(JNIEnv * env, jobject feature_info) {
    if(sensors_ready) {
        auto feature = jsensors::feature_info_wrapper(env, feature_info);
        auto device = feature.get_device_info();    
        auto device_number = device.get_system_id();
        auto feature_number = feature.get_number();

        if(device_number < detected_chips.size()) {
            auto chip_p = detected_chips[device_number];
            auto feature_p = get_feature({ device_number, feature_number });

            if(feature_p != NULL) {
                std::vector<jsensors::sub_feature_info_wrapper> subfeatures;
                feature_key key = { device_number, feature_number };
                auto current = chip_sub_features.lower_bound(key);
                auto last = chip_sub_features.upper_bound(key);

                while(current != last) {
                    if(current->first.device_number == key.device_number
                            && current->first.feature_number == key.feature_number) {
                        subfeatures.push_back(jsensors::sub_feature_info_wrapper(env, 
                                feature.get_this(),
                                current->second));
                    }

                    current++;
                }

                jclass cls = env->FindClass(SUB_FEATURE_INFO_CLASS);
                jobjectArray array = env->NewObjectArray(subfeatures.size(), cls, NULL);
                int i = 0;

                for(auto entry : subfeatures) {
                    env->SetObjectArrayElement(array, i++, entry.get_this());
                }

                return array;
            }
        }
    }

    return NULL;
}

jobject get_sub_feature_value(JNIEnv * env, jobject sub_feature_info) {
    sensor_data_wrapper result(env);

    result.set_failed(true);
    result.set_value(0.0);

    if(sensors_ready) {
        sub_feature_info_wrapper sub_feature(env, sub_feature_info);
        auto feature = sub_feature.get_feature_info();
        auto device = feature.get_device_info();
        auto device_number = device.get_system_id();

        if(device_number < detected_chips.size()) {
            auto chip = detected_chips[device_number];
            double value;

            if(sensors_get_value(chip, sub_feature.get_number(), &value) == 0) {
                result.set_failed(false);
                result.set_value(value);
            }
        }
    }

    return result.get_this();
}

void cleanup() {
    detected_chips.clear();
    chip_features.clear();
    chip_sub_features.clear();
    sensors_cleanup();
}

};