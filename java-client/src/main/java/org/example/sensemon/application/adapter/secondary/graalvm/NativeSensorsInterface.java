package org.example.sensemon.application.adapter.secondary.graalvm;

import org.example.sensemon.application.model.*;
import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.CField;
import org.graalvm.nativeimage.c.struct.CFieldAddress;
import org.graalvm.nativeimage.c.struct.CPointerTo;
import org.graalvm.nativeimage.c.struct.CStruct;
import org.graalvm.nativeimage.c.type.*;
import org.graalvm.word.PointerBase;
import org.graalvm.word.WordFactory;

import java.util.List;

@CContext(NativeSensorsInterface.Context.class)
public final class NativeSensorsInterface {
    @CPointerTo(nameOfCType = "FILE")
    public interface FilePointer extends PointerBase { }

    @CStruct("sensors_bus_id")
    public interface BusIdStruct extends PointerBase {
        @CField("type")
        short getBusType();
        @CField("nr")
        short getBusNumber();
    }

    @CStruct("sensors_chip_name")
    public interface ChipNameStruct extends PointerBase {
        @CFieldAddress("prefix")
        CCharPointerPointer getPrefix();
        @CFieldAddress("bus")
        BusIdStruct getBus();
        @CField("addr")
        int getAddress();
        @CFieldAddress("path")
        CCharPointerPointer getPath();
    }

    @CStruct("sensors_feature")
    public interface FeatureStruct extends PointerBase {
        @CFieldAddress("name")
        CCharPointerPointer getName();
        @CField("number")
        int getNumber();
        @CField("type")
        int getType();
        @CField("first_subfeature")
        int getFirstSubFeature();
        @CField("padding1")
        int getPadding1();
    }

    @CStruct("sensors_subfeature")
    public interface SubFeatureStruct extends PointerBase {
        @CFieldAddress("name")
        CCharPointerPointer getName();
        @CField("number")
        int getNumber();
        @CField("type")
        int getType();
        @CField("mapping")
        int getMapping();
        @CField("flags")
        int getFlags();
    }

    @CFunction("sensors_init")
    public static native int init(FilePointer pointer);

    @CFunction("sensors_cleanup")
    public static native void cleanup();

    @CFunction("sensors_get_detected_chips")
    public static native @CConst ChipNameStruct getDetectedChips(
            @CConst ChipNameStruct chipName,
            CIntPointer number);

    @CFunction("sensors_get_features")
    public static native @CConst FeatureStruct getFeatures(
            @CConst ChipNameStruct chipName,
            CIntPointer number);

    @CFunction("sensors_get_all_subfeatures")
    public static native @CConst SubFeatureStruct getSubFeatures(
            @CConst ChipNameStruct chipName,
            @CConst FeatureStruct feature,
            CIntPointer number);

    @CFunction("sensors_get_value")
    public static native int getValue(
            @CConst ChipNameStruct chipName,
            int subFeatureNumber,
            CDoublePointer value);

    @CFunction("sensors_snprintf_chip_name")
    public static native int printName(
            CCharPointer str,
            int size,
            @CConst ChipNameStruct name);

    public static ChipNameStruct getChipName(int id) {
        var chipNumberPtr = StackValue.get(CIntPointer.class);
        NativeSensorsInterface.ChipNameStruct chipName;

        chipNumberPtr.write(id);

        return NativeSensorsInterface.getDetectedChips(WordFactory.nullPointer(), chipNumberPtr);
    }

    public static @CConst FeatureStruct getFeature(@CConst ChipNameStruct chipName, int id) {
        if(chipName.isNonNull()) {
            var featureNumber = StackValue.get(CIntPointer.class);
            featureNumber.write(id);
            return NativeSensorsInterface.getFeatures(chipName, featureNumber);
        } return WordFactory.nullPointer();
    }

    public static String getName(@CConst ChipNameStruct chipName) {
        CCharPointer null_name = WordFactory.nullPointer();
        var size = printName(null_name, 0, chipName) + 1;
        var buffer = new byte[size];
        String result = null;

        try(var pinned = PinnedObject.create(buffer)) {
            var pointer = pinned.<CCharPointer>addressOfArrayElement(0);

            printName(pointer, size, chipName);

            result = CTypeConversion.utf8ToJavaString(pointer);
        }

        return result;
    }
    public static String toString(@CConst CCharPointer pointer) {
        return pointer.isNonNull() ? CTypeConversion.utf8ToJavaString(pointer) : null;
    }
    public static DeviceInfo toDeviceInfo(@CConst CIntPointer numberPtr, @CConst ChipNameStruct ptr) {
        var name = getName(ptr);

        return DeviceInfo.builder()
                .name(name)
                .bus(DeviceBus.builder()
                        .type(BusType.fromCode(ptr.getBus().getBusType()))
                        .number(ptr.getBus().getBusNumber())
                        .build())
                .prefix(toString(ptr.getPrefix().read()))
                .path(toString(ptr.getPath().read()))
                .address(ptr.getAddress())
                .systemId(numberPtr.read() - 1)
                .build();
    }
    public static FeatureInfo toFeatureInfo(@CConst CIntPointer numberPtr, @CConst FeatureStruct ptr) {
        return FeatureInfo.builder()
                .name(toString(ptr.getName().read()))
                .number(ptr.getNumber())
                .featureType(FeatureType.fromCode(ptr.getType()))
                .systemId(numberPtr.read() - 1)
                .build();
    }

    public static SubFeatureInfo toSubFeatureInfo(@CConst CIntPointer numberPtr, @CConst SubFeatureStruct ptr) {

        return SubFeatureInfo.builder()
                .name(toString(ptr.getName().read()))
                .number(ptr.getNumber())
                .type(SubFeatureType.fromCode(ptr.getType()))
                .mapping(ptr.getMapping())
                .flags(ptr.getFlags())
                .systemId(numberPtr.read() - 1)
                .build();
    }

    public static class Context implements CContext.Directives {
        @Override
        public List<String> getLibraries() {
            return List.of("sensors");
        }

        @Override
        public List<String> getHeaderFiles() {
            return List.of("<string.h>",
                    "</usr/include/sensors/sensors.h>");
        }
    }
}
