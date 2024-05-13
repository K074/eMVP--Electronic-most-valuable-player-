package logic;

import data.PlayerType;


public class ProfessionalPlayer extends Candidate {
    private String teamName;
    private int salary;

    public ProfessionalPlayer(String name,  PlayerType type, String teamName, int salary) {
        super(name, type);
        this.teamName = teamName;
        this.salary = salary;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    
}
	