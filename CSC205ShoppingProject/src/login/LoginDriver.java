package login;

public class LoginDriver {
	
	static LoginDatabase database = new LoginDatabase();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		database.ConnectLoginDatabase("@PantsBobby25361337");
		database.AddLoginDatabase("Test", "Test", "Customer", "Test", "tea", "tea", "tea");
		
		database.CloseDatabase();
	}

}
