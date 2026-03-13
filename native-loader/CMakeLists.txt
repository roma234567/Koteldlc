cmake_minimum_required(VERSION 3.20)
project(KotelDLCLoader LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_executable(KotelDLCLoader
    src/main.cpp
)

target_compile_definitions(KotelDLCLoader PRIVATE
    UNICODE
    _UNICODE
)

if (MSVC)
    target_compile_options(KotelDLCLoader PRIVATE /W4 /EHsc)
else()
    target_compile_options(KotelDLCLoader PRIVATE -Wall -Wextra -Wpedantic)
endif()
