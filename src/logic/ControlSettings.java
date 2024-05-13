package logic;

public class ControlSettings extends SkillSetting {
    private String controlScheme;
    private String sensitivity;

    public ControlSettings(int aim, int movement, int graf, String crosshair, String controlScheme, String sensitivity) {
        super(aim, movement, graf, crosshair);
        this.controlScheme = controlScheme;
        this.sensitivity = sensitivity;
    }

    public String getControlScheme() {
        return controlScheme;
    }

    public void setControlScheme(String controlScheme) {
        this.controlScheme = controlScheme;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }
}
