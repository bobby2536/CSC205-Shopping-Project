 public class Products {
        private int id;
        private String itemName;
        private String description;
        private double price;
        private int quantity;
    
        public Products(int id, String itemName, String description, double price, int quantity) {
            this.id = id;
            this.itemName = itemName;
            this.description = description;
            this.price = price;
            this.quantity = quantity;
        }
        // Getter for id
        public int getId() {
            return id;
        }

        // Setter for id
        public void setId(int id) {
            this.id = id;
        }

        // Getter for itemName
        public String getItemName() {
            return itemName;
        }

        // Setter for itemName
        public void setItemName(String itemName) {
            this.itemName = itemName;
        }
        // Getter for description
        public String getDescription() {
        	return description;
        }
        
        // Setter for description
        public void setDescription(String description) {
        	this.description = description;
        }
        // Getter for price
        public double getPrice() {
        	return price;
        }
        // Setter for price
        public void setPrice(double price) {
        	this.price = price;
        }
        // Getter for quantity
        public int getQuantity() {
        	return quantity;
        }
        // Setter for quantity
        public void setQuantity(int quantity) {
        	this.quantity = quantity;
        }
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", itemName='" + itemName + '\'' +
                    ", description='" + description + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }
  