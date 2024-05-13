package logic;

public class SkillSetting extends Skill{
    private int aim;
    private int movement;
    private int graf;
    private String crosshair;

    public SkillSetting(int aim, int movement, int graf, String crosshair) {
        this.aim += aim;
        this.movement += movement;
        this.graf *= graf;
        this.crosshair += crosshair;
    }
    
    public int getAim() {
        return aim;
    }

    public int getMovement() {
        return movement;
    }

    public int getGraf() {
        return graf;
    }

    public String getCrosshair() {
        return crosshair;
    }
}
