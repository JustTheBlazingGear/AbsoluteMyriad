package am.effective;

public class EffMenuRenderer {
    public MenuBackground background;

    public EffMenuRenderer() {
    }

    public void changeBackground(MenuBackground background) {
        this.background = background;
        if (background != null) {
            background.generateWorld();
        }

    }

    public void render() {
        if (this.background != null) {
            this.background.render();
        }

    }
}

