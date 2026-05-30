from dataclasses import dataclass
from pathlib import Path

from settings import APP_NAME, MINECRAFT_VERSION, DEFAULT_PROFILE


@dataclass(frozen=True)
class LaunchProfile:
    name: str = DEFAULT_PROFILE
    version: str = MINECRAFT_VERSION
    game_dir: Path = Path.home() / ".minecraft"


class Launcher:
    def __init__(self, profile: LaunchProfile | None = None) -> None:
        self.profile = profile or LaunchProfile()

    def describe(self) -> str:
        return f"{APP_NAME} profile={self.profile.name} minecraft={self.profile.version} dir={self.profile.game_dir}"
