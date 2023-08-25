cmake_minimum_required(VERSION 3.27)

project(jsensors VERSION 1.0.0)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

include_directories(include "/usr/include" "/usr/lib/jvm/java-17-graalvm/include" "/usr/lib/jvm/java-17-graalvm/include/linux")

file(GLOB SOURCES "src/*.cpp")
file(GLOB HEADERS "native-include/*.h" "include/*.h")
file(GLOB TEST_SOURCES "test/*.cpp")

add_library(jsensors SHARED ${SOURCES})
# install(TARGETS sensemon DESTINATION lib)

target_link_libraries(jsensors sensors)
target_compile_options(jsensors PUBLIC -shared)
