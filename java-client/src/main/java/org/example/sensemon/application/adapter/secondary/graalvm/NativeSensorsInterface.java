package org.example.sensemon.application.adapter.secondary.graalvm;

import org.graalvm.nativeimage.PinnedObject;
import org.graalvm.nativeimage.StackValue;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.function.CFunction;
import org.graalvm.nativeimage.c.struct.*;
import org.graalvm.nativeimage.c.type.*;
import org.graalvm.word.Pointer;
import org.graalvm.word.PointerBase;
import org.graalvm.word.UnsignedWord;
import org.graalvm.word.WordFactory;

import java.util.List;

@CContext(NativeSensorsInterface.Context.class)
public final class NativeSensorsInterface {
    @CStruct("sensors_bus_id")
    public interface BusId extends PointerBase {
        @CField("type")
        @AllowWideningCast
        short getType();

        @CField("nr")
        @AllowWideningCast
        short getNumber();
    }

    @CStruct("sensors_chip_name")
    public interface ChipName extends PointerBase {
        @CField("prefix")
        CCharPointer getPrefix();
        @CField("bus")
        @AllowWideningCast
        BusId getBusId();
        @CField("addr")
        int getAddress();
        @CField("path")
        CCharPointer getPath();
    }

    @CStruct("sensors_feature")
    public interface Feature extends PointerBase {
        @CField("name")
        CCharPointer getName();
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
    public interface SubFeature extends PointerBase {
        @CField("name")
        CCharPointer getName();
        @CField("number")
        int getNumber();
        @CField("type")
        int getType();
        @CField("mapping")
        int getFirstSubFeature();
        @CField("flags")
        int getPadding1();
    }

    @CPointerTo(nameOfCType = "FILE")
    public interface FilePointer extends PointerBase { }

    @CPointerTo(ChipName.class)
    @CConst
    public interface ChipNamePointer extends PointerBase {
        ChipName read();
        void write(ChipName value);
    }

    @CPointerTo(Feature.class)
    @CConst
    public interface FeaturePointer extends PointerBase {
        void write(Feature value);
    }

    @CPointerTo(SubFeature.class)
    public interface SubFeaturePointer extends PointerBase {
        void write(SubFeature value);
    }

    @CFunction("sensors_init")
    public static native int init(FilePointer pointer);

    @CFunction("sensors_cleanup")
    public static native void cleanup();

    @CFunction("sensors_get_detected_chips")
    public static native ChipNamePointer getDetectedChips(
            @CConst ChipNamePointer name,
            CIntPointer number);

    @CFunction("sensors_get_features")
    public static native ChipNamePointer getFeatures(
            @CConst ChipNamePointer name,
            CIntPointer number);

    @CFunction("sensors_get_all_subfeatures")
    public static native ChipNamePointer getSubFeatures(
            @CConst ChipNamePointer name,
            @CConst FeaturePointer feature,
            CIntPointer number);

    @CFunction("sensors_get_value")
    public static native int getValue(
            @CConst ChipNamePointer name,
            int subFeatureNumber,
            CDoublePointer value);

    @CFunction("sensors_snprintf_chip_name")
    public static native int printName(
            CCharPointer str,
            int size,
            @CConst ChipNamePointer name);

    public static String getName(@CConst ChipNamePointer chipName) {
        CCharPointer null_name = WordFactory.nullPointer();
        var size = printName(null_name, 0, chipName);
        var buffer = new byte[size];
        String result = null;

        try(var pinned = PinnedObject.create(buffer)) {
            var pointer = pinned.<CCharPointer>addressOfArrayElement(0);

            printName(pointer, size, chipName);

            result = CTypeConversion.toJavaString(pointer, WordFactory.unsigned(size));
        }

        return result;
    }

    public static class Context implements CContext.Directives {
        @Override
        public List<String> getLibraries() {
            return List.of("sensors");
        }

        @Override
        public List<String> getHeaderFiles() {
            return List.of("</usr/include/sensors/sensors.h>");
        }
    }
}
