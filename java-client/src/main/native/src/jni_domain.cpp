#include "jni_domain.h"

namespace jsensors {

bool feature_key_comp::operator()(const feature_key & a, const feature_key & b) {
    return a.device_number == b.device_number
            ? a.feature_number < b.feature_number
            : a.device_number < b.device_number;
}

std::string get_chip_name(sensor_chip_name_p chip) {
    std::size_t size = sensors_snprintf_chip_name(NULL, 0, chip) + 1;

    if(size > 0) {
        char * name_ptr = new char[size + 1];
        sensors_snprintf_chip_name(name_ptr, size, chip);
        std::string name(name_ptr, size - 1);

        delete [] name_ptr;

        return name;
    } else {
        return "";
    }
}

std::string to_string(JNIEnv * env, jstring value) {
    jboolean isCopy;
    auto chars = env->GetStringUTFChars(value, &isCopy);
    auto length = env->GetStringUTFLength(value);
    
    std::string result(chars, length);
    env->ReleaseStringUTFChars(value, chars);
    
    return result;
}

void vmprintln(JNIEnv * env, const std::string & value) {
    auto cls = env->FindClass("java/lang/System");
    auto out_field_id = env->GetStaticFieldID(cls, "out", "Ljava/io/PrintStream;");
    auto out = env->GetStaticObjectField(cls, out_field_id);
    auto out_class = env->GetObjectClass(out);
    auto println_id = env->GetMethodID(out_class, "println", "(Ljava/lang/String;)V");
    auto str = env->NewStringUTF(value.c_str());

    env->CallObjectMethod(out, println_id, str);
}

void vmprint(JNIEnv * env, const std::string & value) {
    auto cls = env->FindClass("java/lang/System");
    auto out_field_id = env->GetStaticFieldID(cls, "out", "Ljava/io/PrintStream;");
    auto out = env->GetStaticObjectField(cls, out_field_id);
    auto out_class = env->GetObjectClass(out);
    auto println_id = env->GetMethodID(out_class, "print", "(Ljava/lang/String;)V");
    auto str = env->NewStringUTF(value.c_str());

    env->CallObjectMethod(out, println_id, str);
}

jobject get_enum_by_id(JNIEnv * env, const char * class_name, const char * signature, int value) {
    auto cls = env->FindClass(class_name);
    auto by_code_id = env->GetStaticMethodID(cls, "fromCode", signature);

    return env->CallStaticObjectMethod(cls, by_code_id, (jint) value);
}

};