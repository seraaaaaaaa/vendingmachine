package machine;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;

import internal.moneyChecker;

public class VendingMachine {
	
	private final String admin = "admin";
	private static ArrayList<Drinks> drinks = new ArrayList<Drinks>();

	Scanner s = new Scanner(System.in);
	moneyChecker mc = new moneyChecker();
	 
	public void adminInterface() {
		System.out.println("╔════════════════════════════╗");
		System.out.println("             Menu:");
		System.out.println("         【1】 Add a drink");
		System.out.println("         【2】 Delete a drink");
		System.out.println("         【3】 Update inventory");
		System.out.println("         【4】 Update price");
		System.out.println("         【5】 Check inventory");
		System.out.println("         【0】 Quit");
		System.out.println("╚════════════════════════════╝");
	}
	
	public void userInterface() {
		displayMenu();
		System.out.println("              Balance:"+ "RM " + mc.getBalance());
		System.out.println("******************************");
		System.out.println("【1】 Purchase Drink");
		System.out.println("【0】 Cancel");
		System.out.println("******************************");
	}
	
	public void displayWelcome() {
		
		System.out.println("╔════════════════════════════╗");
		date();
		System.out.println("	  Welcome to ");
		System.out.println("     The Vending Machine!");
		System.out.println();
		System.out.println("   【1】 Press to start!");
		System.out.println();
		System.out.println("		          【0】 Admin");
		System.out.println("╚════════════════════════════╝");
		
	}
	
	public void addDrink() {
		Drinks d = new Drinks();
		try {
			System.out.println("Enter new drink id:");
		    d.setID(s.next());
		    System.out.println("Enter new drink name:");
		    d.setName(s.next());
			System.out.println("Enter new drink price:");
		    d.setPrice(s.nextDouble());
			System.out.println("Enter new drink quantity:");
			d.setQuantity(s.nextInt());
			drinks.add(d);
			System.out.println("Added successfully!");
		}catch (InputMismatchException e) {
			System.out.println("Invalid input!");
		}
		s.nextLine();
	}
	
	public void deleteDrink() {
		System.out.println("Enter drinks id:");
		String id = s.next();
		int index = -1;
		for (int i = 0; i< drinks.size(); i++) {
			if(drinks.get(i).getID().equals(id))
				index = i;
		}
		if (index == -1)
			System.out.println("Item" + '\t' + id + '\t' + "not exists");
		else {
			drinks.remove(index);
			System.out.println("**************************");
			System.out.println("Deleted successfully!");
			System.out.println("**************************");
		}
	}
	
	public void addInventory() {
		System.out.println("Enter drinks id:");
	
		String id = s.next();
		int index = -1;
		for (int i = 0; i< drinks.size(); i++) {
			if(drinks.get(i).getID().equals(id))
				index = i;
		}
		if (index == -1)
			System.out.println("Item" + '\t' + id + '\t' + "not exists");
		else {
			Drinks d = drinks.get(index);
			System.out.println("══════════════════════════════════");
			System.out.println("ID" + '\t' + "Drink" + '\t' + "Price" + '\t' + "Quantity");
			System.out.println(d.getID() + '\t' + d.getName() + '\t' + d.getPrice() + '\t' + d.getQuantity());
			System.out.println("══════════════════════════════════");
			System.out.println("Enter drink quantity:");
			d.setQuantity(s.nextInt());
			drinks.remove(index);
			drinks.add(d);
			System.out.println("**************************");
			System.out.println("Updated successfully!");
			System.out.println("**************************");
		}
	}
	
	public void updatePrice() {
		System.out.println("Enter drinks id:");
	
		String id = s.next();
		int index = -1;
		for (int i = 0; i< drinks.size(); i++) {
			if(drinks.get(i).getID().equals(id))
				index = i;
		}
		if (index == -1)
			System.out.println("Item" + '\t' + id + '\t' + "not exists");
		else {
			Drinks d = drinks.get(index);
			System.out.println("══════════════════════════════════");
			System.out.println("ID" + '\t' + "Drink" + '\t' + "Price" + '\t' + "Quantity");
			System.out.println(d.getID() + '\t' + d.getName() + '\t' + d.getPrice() + '\t' + d.getQuantity());
			System.out.println("══════════════════════════════════");
			System.out.println("Enter drink price:");
			d.setPrice(s.nextDouble());
			drinks.remove(index);
			drinks.add(d);
			System.out.println("**************************");
			System.out.println("Updated successfully!");
			System.out.println("**************************");
		}
	}
	
	public void init() {
		Drinks d1 = new Drinks( "1", "Cola", 2.00 , 10);
		Drinks d2 = new Drinks( "2", "100plus", 2.00 , 10);
		Drinks d3 = new Drinks( "3", "Water", 1.00 , 10);
		Drinks d4 = new Drinks( "4", "Sprite", 2.00 , 10);
		drinks.add(d1);
		drinks.add(d2);
		drinks.add(d3);
		drinks.add(d4);	
	}
	
	public void writeFile() {
		String fileName = "drinks.txt";
		try {
			FileWriter fileWriter = new FileWriter(fileName);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("ID" + '\t' + "Drink" + '\t' + "Price" + '\t' + "Quantity");
			bufferedWriter.newLine();
			
			for (Drinks d : drinks) {
			bufferedWriter.write(d.getID() + '\t' + d.getName() + '\t' + d.getPrice() + '\t' + d.getQuantity());
			bufferedWriter.newLine();
			}

			bufferedWriter.close();
		}
		catch(IOException ex) {
			System.out.println("Error writing to file'" + fileName + "'");
		}
	}
	
	public void displayMenu() {
		String fileName = "drinks.txt";
		writeFile();
		String line = null;
		System.out.println("╔════════════════════════════╗");
		try {
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file'" + fileName + "'");
		}
		catch(IOException ex) {
			System.out.println("Error reading file'" + fileName + "'");
		}
		System.out.println("╚════════════════════════════╝");
	}
	
	public boolean hasEnoughMoney() {
		return mc.checkSufficientBalance();
	}
	
	public void promptMoney() {
		 try {
			 System.out.println( "The machine accepts only the following money:\n" + "(RM 1, RM 5, RM 10)\n");
			 System.out.println("Insert Money ==>");
			 
			 int money = s.nextInt();
			 while ((money != 1 && money != 5 && money != 10)){           
	             System.out.println("Not accepting RM" + money); 
	             System.out.println("Please insert money again!"); 
	             money = s.nextInt();
	         }
	        System.out.println("Inserted RM" + money);
			mc.addBalance(money);	
		 }catch(InputMismatchException e) {
			 System.out.println("Invalid input!");
		 }
		 s.nextLine();	 
	}
	
	public void selectDrink() {
		System.out.println("Please select drink:");
		
		String id = s.next();
		int index  = -1;
		for (int i = 0; i<drinks.size(); i++) {
		   if(drinks.get(i).getID().equals(id))
				index = i;
		}
		if (index == -1)
			System.out.println("Drink not exists");
		else {
			Drinks d = drinks.get(index);
			System.out.println("Enter quantity:");
			int num = s.nextInt();
			while(num>d.getQuantity()) {
				System.out.println("Not enough stock! Plese enter quantity again!");
			 	num = s.nextInt();	
			}

			double needPay = d.getPrice()*num;
			while(mc.getBalance()<needPay) {
				System.out.println("Insufficeint balance!");
				promptMoney();
			}

				d.setQuantity(d.getQuantity()-num);
				drinks.remove(index);
				drinks.add(d);
				System.out.println("**************************");
				System.out.println("--- Dispensing drink ---");
				mc.cutBalance(needPay);
				System.out.println("**************************");
				System.out.println("Returning RM " + mc.getBalance());
				mc.setBalance(0);
				System.out.println("**************************");
				System.out.println("Thanks for purchasing!");
				System.out.println("**************************");
		}	
     }
	
	public void refundMoney() {
		System.out.println("**************************");
		System.out.println("Returning RM " + mc.getBalance());
		mc.setBalance(0);
		System.out.println("**************************");
		System.out.println("Thanks for using!");
		System.out.println("**************************");
	}
	
	public void date() {
		LocalDate date = LocalDate.now();
		System.out.println(date);
	}
	
	 public static void main(String[] args) {

		 VendingMachine machine = new VendingMachine();
         machine.init();
		 while(true) {
			 machine.displayWelcome();
			 Scanner s = new Scanner(System.in);
			 int user = s.nextInt();
			 while (user != 1 && user != 0) {
				System.out.println("Invalid option");
				user = s.nextInt();
			  }
			 /* 
			  * Admin
			 */
			 if (user == 0) {
				 //Request password
				 System.out.println("Enter password:");
				 String psw = s.next();
				 if(!psw.equals(machine.admin)) {
					 System.out.println("Wrong password");
					 System.out.println("Enter password again:");
					 psw = s.next();
				 }else {
					    System.out.println("**************************");
						System.out.println("Welcome admin!");
						System.out.println("**************************");
				 while(true) {
					 machine.adminInterface();
					 try {
						 System.out.println("Please select an option.");
						 int option = s.nextInt();		 			 
					 
						 switch(option) {
						 
						 case 1:
							 System.out.println("******************************");
							 machine.addDrink();
							 System.out.println("******************************");
							 break;
							
						 case 2:
							 machine.deleteDrink();
							 break;
						
						 case 3:
							 machine.addInventory();
							 break;
							 
						 case 4:
							 machine.updatePrice();
							 break;
							 
						 case 5:
							 System.out.println("******************************");
							 machine.displayMenu();
							 System.out.println("******************************");
							 break;
							 
						 case 0: 
							 System.out.println("**************************");
							 System.out.println("Goodbye!");
							 System.out.println("**************************");
							 break;
							 
						 default:
							 System.out.println("Invalid option! Please try again!");
							 option = s.nextInt();
							 break;
							
						 }
						 if(option == 0) break; 
					 }catch(InputMismatchException e) {
						 System.out.println("Invalid input");
					 }
					 s.nextLine();
			  }
				  
			}	
				 
			}
			  /*
			  * Customer
			  */
			 else if (user == 1) {
				 
				 while(true) {
					 machine.userInterface();
					 try {
						 System.out.println("Please select an option.");
						 int option = s.nextInt();					 
						 
						 switch(option) {
						 
						 case 1:
							 if(machine.hasEnoughMoney()) {
								 machine.selectDrink(); 
							 }else {
								 machine.promptMoney();
								 machine.selectDrink();
							 }
							 break;
							 
						 case 0: 
							 machine.refundMoney();
							 break;
							 
						 default:
							 System.out.println("Invalid option! Please try again!");
							 option = s.nextInt();
							 break; 

					 }
						 if(option == 0) break; 
					 }catch (InputMismatchException e) {
						 System.out.println("Invalid input!");
					 }
					 s.nextLine();
			 }
			 
		 } else break;
	 }
	 }
}
