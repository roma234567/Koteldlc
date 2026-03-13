#include <iostream>
#include <string>

int main(int argc, char* argv[]) {
    std::cout << "KotelDLC native loader stub" << std::endl;

    if (argc > 1) {
        std::cout << "Arguments:" << std::endl;
        for (int i = 1; i < argc; ++i) {
            std::cout << "  [" << i << "] " << argv[i] << std::endl;
        }
    } else {
        std::cout << "No arguments provided." << std::endl;
    }

    std::cout << "Build system is ready (CMake/Visual Studio project files present)." << std::endl;
    return 0;
}
