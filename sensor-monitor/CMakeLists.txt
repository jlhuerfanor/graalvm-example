cmake_minimum_required(VERSION 3.27)

project(sensemon VERSION 1.0.0)

set(CMAKE_CXX_COMPILER          "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/clang++")
set(CMAKE_CXX_FLAGS             " ")
set(CMAKE_AR                    "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/ar")
set(CMAKE_LINKER                "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/ld")
set(CMAKE_NM                    "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/nm")
set(CMAKE_OBJDUMP               "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/objdump")
set(CMAKE_RANLIB                "/usr/lib/jvm/java-17-graalvm/languages/llvm/native/bin/ranlib")
set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)
# set(CMAKE_INSTALL_RPATH_USE_LINK_PATH TRUE)

add_definitions(-DGRAALVM_COMPILER)

include_directories(include "/usr/include" "/usr/lib/jvm/java-17-graalvm/languages/llvm/include")

file(GLOB SOURCES "src/*.cpp")
file(GLOB HEADERS "include/*.h")
file(GLOB TEST_SOURCES "test/*.cpp")

add_library(sensemon SHARED ${SOURCES})
install(TARGETS sensemon DESTINATION lib)

target_link_libraries(sensemon sensors)
target_compile_options(sensemon PUBLIC -lgraalvm-llvm -shared)

add_executable(sensemon_test ${SOURCES} ${TEST_SOURCES})
target_link_libraries(sensemon_test sensors)