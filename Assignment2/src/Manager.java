import java.util.Scanner;

public class Manager {
	private static Manager manager =null;
	private static String mgrId = "mid@eventshire.com";
    private static String pw = "MEvent@111";
    private static Scanner scanner;
   
    private Manager(String logId, String pwd) {
    	if((logId.equals(mgrId)) && (pwd.equals(pw))) {
    		System.out.println("Successfully logged in."); 
    	}
    	else{
    		System.out.println("Incorrect Username/Password. Please try again.");
    		main(null);
    	}    	
    }
    public static synchronized Manager getInstance (String logId, String pwd) {
    	if(manager == null) 
    		manager = new Manager(logId, pwd);
    	return manager;		
    	} 
    	public static void main (String[] args) {
    		scanner = new Scanner(System.in);
    		do{
    		System.out.print("Enter Username :");
    		String managerId = scanner.nextLine();
            System.out.print("Enter Password :");
            String managerpwd = scanner.nextLine();
            Manager.getInstance(managerId,managerpwd); 
    		} while(false);

    } 
    }

