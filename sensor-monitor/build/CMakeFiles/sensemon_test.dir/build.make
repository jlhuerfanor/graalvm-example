# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.27

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/bin/cmake

# The command to remove a file.
RM = /usr/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/jhuerfano/git/endava/graalvm/sensor-monitor

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/jhuerfano/git/endava/graalvm/sensor-monitor/build

# Include any dependencies generated for this target.
include CMakeFiles/sensemon_test.dir/depend.make
# Include any dependencies generated by the compiler for this target.
include CMakeFiles/sensemon_test.dir/compiler_depend.make

# Include the progress variables for this target.
include CMakeFiles/sensemon_test.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/sensemon_test.dir/flags.make

CMakeFiles/sensemon_test.dir/src/device.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/src/device.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device.cpp
CMakeFiles/sensemon_test.dir/src/device.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/sensemon_test.dir/src/device.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/src/device.cpp.o -MF CMakeFiles/sensemon_test.dir/src/device.cpp.o.d -o CMakeFiles/sensemon_test.dir/src/device.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device.cpp

CMakeFiles/sensemon_test.dir/src/device.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/src/device.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device.cpp > CMakeFiles/sensemon_test.dir/src/device.cpp.i

CMakeFiles/sensemon_test.dir/src/device.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/src/device.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device.cpp -o CMakeFiles/sensemon_test.dir/src/device.cpp.s

CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_feature.cpp
CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o -MF CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o.d -o CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_feature.cpp

CMakeFiles/sensemon_test.dir/src/device_feature.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/src/device_feature.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_feature.cpp > CMakeFiles/sensemon_test.dir/src/device_feature.cpp.i

CMakeFiles/sensemon_test.dir/src/device_feature.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/src/device_feature.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_feature.cpp -o CMakeFiles/sensemon_test.dir/src/device_feature.cpp.s

CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_subfeature.cpp
CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o -MF CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o.d -o CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_subfeature.cpp

CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_subfeature.cpp > CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.i

CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/device_subfeature.cpp -o CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.s

CMakeFiles/sensemon_test.dir/src/monitor.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/src/monitor.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/monitor.cpp
CMakeFiles/sensemon_test.dir/src/monitor.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/sensemon_test.dir/src/monitor.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/src/monitor.cpp.o -MF CMakeFiles/sensemon_test.dir/src/monitor.cpp.o.d -o CMakeFiles/sensemon_test.dir/src/monitor.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/monitor.cpp

CMakeFiles/sensemon_test.dir/src/monitor.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/src/monitor.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/monitor.cpp > CMakeFiles/sensemon_test.dir/src/monitor.cpp.i

CMakeFiles/sensemon_test.dir/src/monitor.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/src/monitor.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/monitor.cpp -o CMakeFiles/sensemon_test.dir/src/monitor.cpp.s

CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/sensemon.cpp
CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Building CXX object CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o -MF CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o.d -o CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/sensemon.cpp

CMakeFiles/sensemon_test.dir/src/sensemon.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/src/sensemon.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/sensemon.cpp > CMakeFiles/sensemon_test.dir/src/sensemon.cpp.i

CMakeFiles/sensemon_test.dir/src/sensemon.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/src/sensemon.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/src/sensemon.cpp -o CMakeFiles/sensemon_test.dir/src/sensemon.cpp.s

CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o: CMakeFiles/sensemon_test.dir/flags.make
CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o: /home/jhuerfano/git/endava/graalvm/sensor-monitor/test/sensemon_test.cpp
CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o: CMakeFiles/sensemon_test.dir/compiler_depend.ts
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_6) "Building CXX object CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -MD -MT CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o -MF CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o.d -o CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o -c /home/jhuerfano/git/endava/graalvm/sensor-monitor/test/sensemon_test.cpp

CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Preprocessing CXX source to CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.i"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/jhuerfano/git/endava/graalvm/sensor-monitor/test/sensemon_test.cpp > CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.i

CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green "Compiling CXX source to assembly CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.s"
	/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/jhuerfano/git/endava/graalvm/sensor-monitor/test/sensemon_test.cpp -o CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.s

# Object files for target sensemon_test
sensemon_test_OBJECTS = \
"CMakeFiles/sensemon_test.dir/src/device.cpp.o" \
"CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o" \
"CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o" \
"CMakeFiles/sensemon_test.dir/src/monitor.cpp.o" \
"CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o" \
"CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o"

# External object files for target sensemon_test
sensemon_test_EXTERNAL_OBJECTS =

sensemon_test: CMakeFiles/sensemon_test.dir/src/device.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/src/device_feature.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/src/device_subfeature.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/src/monitor.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/src/sensemon.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/test/sensemon_test.cpp.o
sensemon_test: CMakeFiles/sensemon_test.dir/build.make
sensemon_test: CMakeFiles/sensemon_test.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color "--switch=$(COLOR)" --green --bold --progress-dir=/home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles --progress-num=$(CMAKE_PROGRESS_7) "Linking CXX executable sensemon_test"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/sensemon_test.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/sensemon_test.dir/build: sensemon_test
.PHONY : CMakeFiles/sensemon_test.dir/build

CMakeFiles/sensemon_test.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/sensemon_test.dir/cmake_clean.cmake
.PHONY : CMakeFiles/sensemon_test.dir/clean

CMakeFiles/sensemon_test.dir/depend:
	cd /home/jhuerfano/git/endava/graalvm/sensor-monitor/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/jhuerfano/git/endava/graalvm/sensor-monitor /home/jhuerfano/git/endava/graalvm/sensor-monitor /home/jhuerfano/git/endava/graalvm/sensor-monitor/build /home/jhuerfano/git/endava/graalvm/sensor-monitor/build /home/jhuerfano/git/endava/graalvm/sensor-monitor/build/CMakeFiles/sensemon_test.dir/DependInfo.cmake "--color=$(COLOR)"
.PHONY : CMakeFiles/sensemon_test.dir/depend

