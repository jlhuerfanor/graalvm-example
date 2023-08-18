#include "jni_sensor.h"

#define OBJ_CONSTRUCTOR "<init>"
#define JAVA_LANG_STRING_SIGNATURE "Ljava/lang/String;"

#define DEVICE_INFO_CLASS "org/example/sensemon/application/model/DeviceInfo"

#define DEVICE_BUS_CLASS "org/example/sensemon/application/model/DeviceBus"
#define DEVICE_BUS_CTOR_SIGNATURE "()V"
#define DEVICE_BUS_SIGNATURE "Lorg/example/sensemon/application/model/DeviceBus;"

#define BUS_TYPE_CLASS "org/example/sensemon/application/model/BusType"
#define BUS_TYPE_ENUM_SIGNATURE "Lorg/example/sensemon/application/model/BusType;"
#define BUS_TYPE_FROM_CODE "fromCode"
#define BUS_TYPE_FROM_CODE_SIGNATURE "(I)Lorg/example/sensemon/application/model/BusType;"

namespace jsensors {


std::map<std::string, sensor_chip_name_p> detected_chips;
bool sensors_ready = false;

jobject create_string(JNIEnv * env, const char * value) {
    return env->NewStringUTF(value);
}

jobject create_string(JNIEnv * env, const std::string & string) {
    return create_string(env, string.c_str());
}

jobject get_bus_type_from_code(JNIEnv * env, jint code) {
    jclass bus_type_class = env->FindClass(BUS_TYPE_CLASS);
    jmethodID bus_type_from_code = env->GetStaticMethodID(bus_type_class, BUS_TYPE_FROM_CODE, 
            BUS_TYPE_FROM_CODE_SIGNATURE);
    return env->CallStaticObjectMethod(bus_type_class, bus_type_from_code, code);
}

jobject create_device_bus(JNIEnv * env, const sensors_bus_id & bus_id) {
    auto device_bus_class = env->FindClass(DEVICE_BUS_CLASS);
    auto device_bus_ctor_id = env->GetMethodID(device_bus_class, OBJ_CONSTRUCTOR, DEVICE_BUS_CTOR_SIGNATURE);
    auto field_type = env->GetFieldID(device_bus_class, "type", BUS_TYPE_ENUM_SIGNATURE);
    auto field_number = env->GetFieldID(device_bus_class, "number", "I");

    auto device_bus = env->NewObject(device_bus_class, device_bus_ctor_id);
    auto bus_type = get_bus_type_from_code(env, bus_id.type);
    
    env->SetObjectField(device_bus, field_type, bus_type);
    env->SetIntField(device_bus, field_number, bus_id.nr);

    return device_bus;
}

jobject create_device_info(JNIEnv * env, const std::string & name, sensor_chip_name_p chip) {
    auto device_info_class = env->FindClass(DEVICE_INFO_CLASS);

    auto device_info_ctor_id = env->GetMethodID(device_info_class, "<init>", "()V");
    auto field_prefix = env->GetFieldID(device_info_class, "prefix", JAVA_LANG_STRING_SIGNATURE);
    auto field_bus = env->GetFieldID(device_info_class, "bus", DEVICE_BUS_SIGNATURE);
    auto field_path = env->GetFieldID(device_info_class, "path", JAVA_LANG_STRING_SIGNATURE);
    auto field_address = env->GetFieldID(device_info_class, "address", "I");
    auto field_name = env->GetFieldID(device_info_class, "name", JAVA_LANG_STRING_SIGNATURE);
    
    auto prefix = create_string(env, chip->prefix);
    auto bus = create_device_bus(env, chip->bus);
    auto path = create_string(env, chip->path);
    auto device_name = create_string(env, name);
    auto device_info = env->NewObject(device_info_class, device_info_ctor_id);

    env->SetObjectField(device_info, field_prefix, prefix);
    env->SetObjectField(device_info, field_bus, bus);
    env->SetObjectField(device_info, field_path, path);
    env->SetIntField(device_info, field_address, chip->addr);
    env->SetObjectField(device_info, field_name, device_name);

    return device_info;
}

bool init() {
    if(!sensors_ready) { 
        sensors_ready = sensors_init(NULL) == 0;

        if(sensors_ready) {
            sensor_chip_name_p current;
            int number = 0;

            while((current = sensors_get_detected_chips(NULL, &number))) {
                std::string name = get_name(current);

                detected_chips[name] = current;
            }
        } else {
            sensors_cleanup();
        }
    }

    return sensors_ready;
}

std::string get_name(sensor_chip_name_p chip) {
    std::size_t size = sensors_snprintf_chip_name(NULL, 0, chip) + 1;

    if(size > 0) {
        char * name_ptr = new char[size + 1];
        sensors_snprintf_chip_name(name_ptr, size, chip);

        return std::string(name_ptr, size);
    } else {
        return "";
    }
}

jobjectArray create_device_infos(JNIEnv * env) {
    jclass device_info_class = env->FindClass(DEVICE_INFO_CLASS);
    jobjectArray array = env->NewObjectArray(detected_chips.size(), 
            device_info_class, NULL);
    
    if(!detected_chips.empty()) {
        int i = 0;

        for(auto entry : detected_chips) {
            auto device_info = create_device_info(env, entry.first, entry.second);

            env->SetObjectArrayElement(array, i++, device_info);
        }
    }

    return array;
}

jobjectArray create_device_features(JNIEnv * env) {
    jclass device_info_class = env->FindClass(DEVICE_INFO_CLASS);
    jobjectArray array = env->NewObjectArray(detected_chips.size(), 
            device_info_class, NULL);
    
    if(!detected_chips.empty()) {
        int i = 0;

        for(auto entry : detected_chips) {
            auto device_info = create_device_info(env, entry.first, entry.second);

            env->SetObjectArrayElement(array, i++, device_info);
        }
    }

    return array;
}

jobjectArray create_device_feature_infos(JNIEnv * env, jobject device_info) {
    
}

void cleanup() {
    detected_chips.clear();
    sensors_cleanup();
}

};