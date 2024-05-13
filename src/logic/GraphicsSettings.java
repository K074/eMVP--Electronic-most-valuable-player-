package logic;

public class GraphicsSettings extends SkillSetting {
    private int resolutionWidth;
    private int resolutionHeight;
    private String graphicsQuality;

    public GraphicsSettings(int aim, int movement, int graf, String crosshair, int resolutionWidth, int resolutionHeight, String graphicsQuality) {
        super(aim, movement, graf, crosshair);
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;
        this.graphicsQuality = graphicsQuality;
    }

    public int getResolutionWidth() {
        return resolutionWidth;
    }

    public void setResolutionWidth(int resolutionWidth) {
        this.resolutionWidth = resolutionWidth;
    }

    public int getResolutionHeight() {
        return resolutionHeight;
    }

    public void setResolutionHeight(int resolutionHeight) {
        this.resolutionHeight = resolutionHeight;
    }

    public String getGraphicsQuality() {
        return graphicsQuality;
    }

    public void setGraphicsQuality(String graphicsQuality) {
        this.graphicsQuality = graphicsQuality;
    }
}
