package items;

import items.ItemDatabase;

public class ItemDriver {
	
	static ItemDatabase database = new ItemDatabase();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		database.ConnectItemDatabase("@PantsBobby25361337");
		//database.CreateItemDatabase();
		database.AddItem("Apples", 0.75, 100, "Juicy Red Apples");
		database.AddItem("Bananas", 0.23, 80, "Yummy bananas");
		database.AddItem("Pears", 0.50, 50, "Juicy Green Pears");
		database.AddItem("Oranges", 0.12, 75, "Ripe Oranges");
	}
	
}
