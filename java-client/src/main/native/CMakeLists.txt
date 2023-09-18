cmake_minimum_required(VERSION 3.27)

project(jsensors VERSION 1.0.0)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)

file(GLOB SOURCES "src/*.cpp")
file(GLOB TEST_SOURCES "test/*.cpp")

add_library(jsensors SHARED ${SOURCES} ${HEADERS})

target_include_directories(jsensors PUBLIC "include/" "native-include/" "/usr/include" "/usr/lib/jvm/java-17-graalvm/include" "/usr/lib/jvm/java-17-graalvm/include/linux")
target_link_libraries(jsensors sensors)
target_compile_options(jsensors PUBLIC -shared)

# install(TARGETS sensemon DESTINATION lib)
