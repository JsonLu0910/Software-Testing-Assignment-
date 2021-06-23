package assignment;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

import com.sun.jdi.CharType;



public class OrderSystem {
	static Order ft;
	public OrderSystem(Order ft) {
		this.ft =ft;
	}
	static OrderSystem os = new OrderSystem(ft);
	
	
	public static  void main(String [] args) throws IOException, NegativeNumberException {

		Scanner in = new Scanner(System.in);
		ArrayList<Order> order= new ArrayList<Order>();
		ArrayList<Item> Item= new ArrayList<Item>();
		ArrayList<Customer> customer= new ArrayList<Customer>();
		ArrayList<Orderdetails>details = new ArrayList<Orderdetails>();
		ArrayList<Payment>p = new ArrayList<Payment>();

		BufferedReader breader = null;
		try {
			breader = new BufferedReader (new FileReader("Cake.txt"));
			String line =null;
			while((line= breader.readLine()) !=null) {
				String[] userinfo = line.split(",");
				if(userinfo.length ==11) {
					String id = userinfo[0];
					String name = userinfo[1];
					String pw =userinfo[2];
					int phone = Integer.parseInt(userinfo[3]);
					boolean stats = Boolean.parseBoolean(userinfo[4]);
					String state= userinfo[5];
					String district= userinfo[6];
					String postal_code = userinfo[7];
					String area = userinfo[8];
					int street_no = Integer.parseInt(userinfo[9]);
					int unit = Integer.parseInt(userinfo[10]);
					
					order.add(new Customer(id,name,pw,phone,stats,state,district,postal_code,area,street_no,unit));
					customer.add(new Customer(id,name,pw,phone,stats,state,district,postal_code,area,street_no,unit));
				}
				else if(userinfo.length ==6) {
						String id= userinfo[0];
						String name = userinfo[1];
						String type = userinfo[2];
						double mprice = Double.parseDouble(userinfo[3]);
						double nmprice = Double.parseDouble(userinfo[4]);
						boolean stats = Boolean.parseBoolean(userinfo[5]);
					
						order.add(new Item(id,name,type,mprice,nmprice,stats));
						Item.add(new Item(id,name,type,mprice,nmprice,stats));

					}
				else 
					if(userinfo.length == 7) {
						String id = userinfo[0];
						String name = userinfo[1];
						String item = userinfo[2];
						boolean stats= Boolean.parseBoolean(userinfo[6]);
						double price = Double.parseDouble(userinfo[4]);
						int quantity= Integer.parseInt(userinfo[3]);
						String address= userinfo[5];
						
						order.add(new Orderdetails(id,name,item,stats,price,quantity,address));
						details.add(new Orderdetails(id,name,item,stats,price,quantity,address));
						
					}
					else 
						if(userinfo.length == 4) {
							String id = userinfo[0];
							String name = userinfo[1];
							boolean stats =  Boolean.parseBoolean(userinfo[2]);
							String method = userinfo[3];
						
							
							order.add(new Payment(id,name,stats,method));
							p.add(new Payment(id,name,stats,method));
							
						}
					

			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		breader.close();
		for(Order ORDER :order) {
		int stop=0;
		do {
				try {
			System.out.println("Welcome to Ms Kiah Bakery\n"+"1.) Order\n"+"2.) View Order\n"+"3.) Sign up\n"+"4.) Search Item"+"\n5.) Exit");
			System.out.println("Please enter your option (1-4) :");
			int option = Integer.parseInt(in.nextLine());
			switch(option) {
			
			case 1:os.order(order,Item,customer,in,p,ORDER);
				break;
			case 2:os.ViewOrder(order,in,details,ORDER);
				break;
			case 3:os.CreateAccount(order,customer,in,ORDER);
				break;
			case 4: os.check_item(order, ORDER, in);
				break;
			case 5:os.Exit();
				break;
			default :System.out.println("Invalid input, pls retry");
			}
				}
				catch(RuntimeException ex ) {
					System.out.println("Error input, pls retry");
				}
		}while(stop!=1);
		
		
		}
}
	
	public   void order(ArrayList<Order>order, ArrayList<Item>item, ArrayList<Customer>customer,Scanner in,ArrayList<Payment>p,Order ORDER) throws FileNotFoundException {
		int counter=-1, i =0,quantity=0,confirm=0,stop=0,count=0, Counte=-1, counterstop=0, confirmation=0, k=0, q=0, no=0;
		double total=0;
		String Area =null;
		double delivery_fee =0;
		Customer cc= new Customer(ORDER);
		Orderdetails od = new Orderdetails(ORDER);
		try {
		od.order(order, item, customer, in, p, ORDER);
		}
		catch(RuntimeException ex) {
			System.out.println("Error Input, pls retry");
		}
		
		
	}
	
	public void check_item(ArrayList<Order>order, Order ORDER,Scanner in) {
		Item ii= new Item(ORDER);
		String item_id= null;
		ii.search_item(order, in,ORDER);
	}
		
	
	// this function allow the user to view their own order, by insert their USERNAME and password
	public  void ViewOrder(ArrayList<Order>order, Scanner in,ArrayList<Orderdetails>details,Order ORDER) throws FileNotFoundException {
	try {
		Orderdetails od = new Orderdetails(ORDER);
		od.ViewOrder(order, in, details);
	}
	catch(RuntimeException ex) {
		System.out.println("Error input, pls retry");
	}
	}
	
	public  void CreateAccount(ArrayList<Order>order,ArrayList<Customer>customer,Scanner in,Order ORDER) throws FileNotFoundException {
		try {
		Customer cc = new Customer(ORDER);
	cc.CreateAccount(order, customer, in, ORDER);
	}
		catch(RuntimeException ex) {
			System.out.println("Error input, pls retry.........");
		}
	}

	
	
	public  void Exit() {

		System.out.println("Thanks for using this application, have a nice day goodbye :)");
		System.exit(0);
	}
	

	}




