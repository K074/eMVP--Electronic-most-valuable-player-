package data;

import java.util.HashMap;
import java.util.Map;
import javafx.concurrent.Task;
import logic.Candidate;

public class UserDatabase {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Candidate> selectedCandidates = new HashMap<>();
    public static Map<String, Candidate> userVotes = new HashMap<>();
    private static boolean isAdminMode = false;
    private static UserDatabase instance;
    
    
    
    
    static {
        register("admin", "admin");
    }
    
    
    
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }
    
    public static boolean registerInBackground(String username, String password) {
        Task<Boolean> registerTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return register(username, password);
            }
        };
        //explicitné použitie viacniťovosti 
        registerTask.setOnSucceeded(event -> {
            boolean success = registerTask.getValue();
            if (success) {
                System.out.println("User registered successfully: " + username);
            } else {
                System.out.println("Username already exists: " + username);
            }
        });

        new Thread(registerTask).start();

        return true;
    }
   
    
    
    public static boolean register(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, password);
            return true;
        }
        return false;
    }

    public static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
        	if (username.equals("admin") && password.equals("admin")) {
            isAdminMode = true;

        }
        return true;
    }
    return false;
}
    
    public static boolean isAdminMode() {
        return isAdminMode;
    }

    public static void exitAdminMode() {
        isAdminMode = false;
    }

    public static void setSelectedCandidate(String username, Candidate candidates) {
        selectedCandidates.put(username, candidates);
        userVotes.put(username, candidates);
        System.out.print(", check" + username + " - " + candidates);

    }
    
    
    public static Map<String, Candidate> getUserVotes() {
        return userVotes;
    }

    public static void addUserCandidate(Candidate candidate) {
        selectedCandidates.put("admin", candidate);
        userVotes.put("admin", candidate);
        Candidate.getCandidates().add(candidate);
    }
    
    public static Candidate getSelectedCandidate(String username) {
        return selectedCandidates.get(username);
    }
    
    public static void printDatabase() {      
    	 for (Map.Entry<String, String> entry : users.entrySet()) {
    	        String username = entry.getKey();
    	        String password = entry.getValue();
    	        Candidate selectedCandidate = selectedCandidates.get(username);
    	        System.out.print(", Username: " + username + ", Password: " + password);
    	        if (selectedCandidate != null) {
    	            System.out.println(", Selected Candidate Name: " + " : ");
    	            for (Map.Entry<String, Candidate> voteEntry : userVotes.entrySet()) {
    	                if (voteEntry.getKey().equals(username)) {
    	                    System.out.print(voteEntry.getValue().getName() + ", ");
    	                }
    	            }
    	        } else {
    	            System.out.println(", No selected candidate");
    	        }
    	    }
    	}       
    }

