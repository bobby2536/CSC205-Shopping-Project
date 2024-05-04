

public class LoginDriver {
	
	static LoginDatabase database = new LoginDatabase();
	static ShoppingDatabase database2 = new ShoppingDatabase();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*database.ConnectLoginDatabase("@PantsBobby25361337");*/
		//database.ConnectLoginDatabase("password");
		database2.ConnectShoppingDatabase("password");
		
		//database.CreateLoginDatabase();
		database2.CreateShoppingDatabase();
		//database.AddLoginDatabase("Test", "Test", "Customer", "Test", "tea", "tea", "tea");
		
		//database2.InsertShoppingDatabase();
		
		
		//database2.SelectShoppingDatabase();
		
		//database2.UpdateShoppingDatabase();
		
		//database2.RefreshShoppingDatabase();
		
		//database.CloseDatabase();
		database2.CloseDatabase();
	}
	

}
