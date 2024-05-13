package data;

import java.io.Serializable;

public class UserData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;

    public UserData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
