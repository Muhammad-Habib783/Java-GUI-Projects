import javax.swing.*;
public class ShoppingSystem 
{
    String[] categories = {"Shoes", "Computers", "Vehicles", "Makeup Goods", "Games", "Clothes"};
    String[][] products =
    	{
            {"Sneakers - $50", "shoes - $80", "Brogues- $20"},
            {"Laptops - $500", "PC - $200", "LED - $70"},
            {"Car - $15000", "Bike - $2000", "Cycle - $500"},
            {"Lipstick - $10", "Foundation - $20", "Fair lovely - $5"},
            {"Android games - $30", "PC Games - $50", "PS5 games - $100"},
            {"Jeans - $30", "Shirt - $25", "Kurta Shalwar - $40"}
    };
    static int totalPrice = 0;
    static StringBuilder cart = new StringBuilder();
     public ShoppingSystem() 
    {
        startShopping();
    }
    public void startShopping() 
    {
        while(true) 
        {
            String category = (String) JOptionPane.showInputDialog(null, "Choose a category:", "Riphah Lootlo",
                    JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);

            if (category == null) break; // Exit if user cancels

            // Get the index of the selected category
            int categoryIndex = java.util.Arrays.asList(categories).indexOf(category);
            
            // Prompt user to choose a product from the selected category
            String product = (String) JOptionPane.showInputDialog(null, "Choose a product from " + category + ":", category,
                    JOptionPane.QUESTION_MESSAGE, null, products[categoryIndex], products[categoryIndex][0]);

            if(product!=null) 
            {
                int price = Integer.parseInt(product.split(" - \\$")[1]);
                totalPrice=totalPrice+price;
                cart.append(product).append("\n"); 
            }
        }
        showPaymentOptions();
    }
    public static void showPaymentOptions() 
    {
        String[] paymentMethods = {"EasyPaisa", "Bank Transfer"};
         // Prompt user to choose a payment method
        int choice = JOptionPane.showOptionDialog(null, "Choose a payment method:", "Payment",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, paymentMethods, paymentMethods[0]);
          // Get payment details based on the selected method
        String paymentDetails= " ";
        if(choice == 0) 
        { 
        	// EasyPaisa
            paymentDetails = JOptionPane.showInputDialog("Enter your EasyPaisa number:");
        } 
        else if(choice == 1)
        {
        	// Bank Transfer
            paymentDetails=JOptionPane.showInputDialog("Enter your bank account number:");
        }
          // Validate payment details
        if(paymentDetails==null||paymentDetails.trim().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Payment details cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
            // Exit if payment details are invalid
        }
          // Show the receipt with purchase summary
        String receipt="Purchase Summary:\n" + cart + "Total Price: $" + totalPrice + "\nPayment Method: " + paymentMethods[choice] + "\nPayment Details: " + paymentDetails;
        JOptionPane.showMessageDialog(null, receipt, "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }    
}



