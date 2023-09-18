#!/bin/bash

echo "Deleting build directory..."
rm -rf build

echo "Configuring CMake project..."
if ! /usr/bin/cmake --no-warn-unused-cli \
    -DCMAKE_BUILD_TYPE:STRING=Debug \
    -DCMAKE_EXPORT_COMPILE_COMMANDS:BOOL=TRUE \
    -DCMAKE_C_COMPILER:FILEPATH=/usr/bin/gcc \
    -DCMAKE_CXX_COMPILER:FILEPATH=/usr/bin/g++ \
    -S./ \
    -B./build \
    -G "Unix Makefiles" ; then
  echo "Failed to configure project."
  exit 1
fi

echo "Building CMake project..."
if ! /usr/bin/cmake --build ./build --config Debug --target all ; then
  echo "Failed to build project"
  exit 1
fi

echo "Generated library"
exit 0