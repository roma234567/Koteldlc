from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path
import tkinter as tk

from settings import APP_NAME, MINECRAFT_VERSION, DEFAULT_PROFILE, WINDOW_SIZE, ACCENT, BACKGROUND, PANEL, MUTED


@dataclass(frozen=True)
class LaunchProfile:
    name: str = DEFAULT_PROFILE
    version: str = MINECRAFT_VERSION
    game_dir: Path = Path.home() / ".minecraft"


class LoaderWindow:
    """Vector-only premium loader; no bundled binary images are required."""

    def __init__(self, profile: LaunchProfile | None = None) -> None:
        self.profile = profile or LaunchProfile()
        self.root = tk.Tk()
        self.root.title(f"{APP_NAME} Premium Loader")
        self.root.geometry(WINDOW_SIZE)
        self.root.configure(bg=BACKGROUND)
        self.root.resizable(False, False)
        self.canvas = tk.Canvas(self.root, width=960, height=560, bg=BACKGROUND, highlightthickness=0)
        self.canvas.pack(fill="both", expand=True)
        self._draw()

    def _draw(self) -> None:
        c = self.canvas
        # background corner frame inspired by the attached Lucky-style mockup
        for x1, y1, x2, y2 in [(88, 72, 148, 72), (88, 72, 88, 132), (812, 72, 872, 72), (872, 72, 872, 132),
                                (88, 428, 88, 488), (88, 488, 148, 488), (872, 428, 872, 488), (812, 488, 872, 488)]:
            c.create_line(x1, y1, x2, y2, fill="#f8f8f8", width=2)
        for i in range(90):
            x = (i * 67) % 960
            y = (i * 43) % 560
            c.create_oval(x, y, x + 1, y + 1, fill="#2d2d30", outline="")

        c.create_text(480, 82, text="✱", fill=ACCENT, font=("Arial", 54, "bold"))
        c.create_text(480, 144, text=APP_NAME, fill=ACCENT, font=("Arial", 32, "bold"))
        c.create_text(480, 178, text=f"Premium loader • Minecraft {self.profile.version}", fill=MUTED, font=("Arial", 12))

        c.create_rectangle(240, 220, 720, 390, fill=PANEL, outline="#1c1c20", width=1)
        c.create_text(278, 252, anchor="w", text="Profile", fill=MUTED, font=("Arial", 11))
        c.create_text(278, 278, anchor="w", text=self.profile.name, fill=ACCENT, font=("Arial", 18, "bold"))
        c.create_text(278, 318, anchor="w", text="Game directory", fill=MUTED, font=("Arial", 11))
        c.create_text(278, 344, anchor="w", text=str(self.profile.game_dir), fill=ACCENT, font=("Arial", 12))

        self._button(560, 322, 680, 360, "Launch")
        self._settings_icon(690, 236)
        c.create_text(480, 482, text="Lucky", fill=ACCENT, font=("Arial", 28, "bold"))

    def _button(self, x1: int, y1: int, x2: int, y2: int, text: str) -> None:
        self.canvas.create_rectangle(x1, y1, x2, y2, fill=ACCENT, outline=ACCENT)
        self.canvas.create_text((x1 + x2) // 2, (y1 + y2) // 2, text=text, fill="#070708", font=("Arial", 12, "bold"))

    def _settings_icon(self, x: int, y: int) -> None:
        c = self.canvas
        c.create_rectangle(x, y, x + 34, y + 34, fill="#18181b", outline="#24242a")
        c.create_text(x + 17, y + 17, text="⚙", fill=ACCENT, font=("Arial", 15, "bold"))

    def run(self) -> None:
        self.root.mainloop()


class Launcher:
    def __init__(self, profile: LaunchProfile | None = None) -> None:
        self.profile = profile or LaunchProfile()

    def describe(self) -> str:
        return f"{APP_NAME} profile={self.profile.name} minecraft={self.profile.version} dir={self.profile.game_dir}"

    def run_gui(self) -> None:
        LoaderWindow(self.profile).run()
