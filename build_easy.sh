#!/usr/bin/env bash
set -u

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
NATIVE_DIR="$ROOT_DIR/native-loader"
NATIVE_BUILD_DIR="$NATIVE_DIR/build"

log() { printf "\n[build_easy] %s\n" "$1"; }
warn() { printf "[build_easy][WARN] %s\n" "$1"; }
err() { printf "[build_easy][ERROR] %s\n" "$1"; }

show_help() {
  cat <<'HELP'
Usage:
  ./build_easy.sh [--all|--native|--java|--help]

Options:
  --all      Build native loader + Java JAR (default)
  --native   Build only native loader via CMake
  --java     Build only Java artifact via Maven
  --help     Show this help

Notes:
- On Linux/macOS native build outputs executable binary.
- On Windows with Visual Studio/CMake generator native build can output .exe/.dll depending on CMake target.
- Java build requires access to Maven/Fabric repositories.
HELP
}

build_native() {
  log "Building native loader (CMake)"

  if ! command -v cmake >/dev/null 2>&1; then
    err "cmake is not installed."
    return 1
  fi

  cmake -S "$NATIVE_DIR" -B "$NATIVE_BUILD_DIR" || return 1
  cmake --build "$NATIVE_BUILD_DIR" -j || return 1

  if [[ -f "$NATIVE_BUILD_DIR/KotelDLCLoader" ]]; then
    log "Native binary ready: $NATIVE_BUILD_DIR/KotelDLCLoader"
  elif [[ -f "$NATIVE_BUILD_DIR/Release/KotelDLCLoader.exe" ]]; then
    log "Native exe ready: $NATIVE_BUILD_DIR/Release/KotelDLCLoader.exe"
  else
    warn "Native target built, but output path is generator-dependent."
  fi
}

build_java() {
  log "Building Java artifact (Maven)"

  if ! command -v mvn >/dev/null 2>&1; then
    err "mvn is not installed."
    return 1
  fi

  set +e
  mvn -f "$ROOT_DIR/pom.xml" -DskipTests package
  local code=$?
  set -e

  if [[ $code -ne 0 ]]; then
    warn "Maven build failed."
    warn "If you see 403/Forbidden from Maven repos, run in a network with access to Maven Central/Fabric repos."
    return $code
  fi

  log "JAR build finished. Check $ROOT_DIR/target"
}

main() {
  local mode="all"
  if [[ $# -gt 0 ]]; then
    mode="$1"
  fi

  case "$mode" in
    --all)
      set -e
      build_native
      build_java || true
      ;;
    --native)
      set -e
      build_native
      ;;
    --java)
      set -e
      build_java
      ;;
    --help|-h)
      show_help
      ;;
    *)
      err "Unknown option: $mode"
      show_help
      exit 2
      ;;
  esac
}

main "$@"
