package logic;



public class Skill {
    public String nickname;
    public int graf;
    public int aim;
    public int movement;
   
    
    public int getAim() {
        return aim;
    }

    public void setAim(int aim) {
        this.aim = aim;
    }
 
 public void SkillCalculator() {
      aim += 2;
      movement += 3;
      graf -= 2;
      if (aim >= 2 || movement >= 3  || graf < 2) {
    	  System.out.println("Skillfull");
      } else {
    	  System.out.println("Skillless");
    	  
      }
 }

}
