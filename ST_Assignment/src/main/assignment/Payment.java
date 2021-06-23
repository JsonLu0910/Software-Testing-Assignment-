package assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Payment extends Order {

	Order ft;
	public Payment(Order ft) {
		super(ft);
		this.ft= ft;
		
	}
	
	
	public Payment(String orderid,String name, boolean status, String PaidMethod) {
		super(orderid,name,status,PaidMethod);
		
		
	}
	
	
	
	
	@Override
	public void showinfo() {
		System.out.println("Name :" + getName()+" ||Order ID :" +getId()+" ||Paid by:"+getType()+" ||Status:"+getStatus());
		
	}
	

	
	
	@Override
	public String totostring() {
		return getId()+","+getName()+","+getStatus()+","+getType();
	}



	@Override
	public int getPhone() {
		// TODO Auto-generated method stub
		return 0;
	}



	
	public  int Paid(ArrayList<Order>order,Scanner in,String id, String name, boolean stats,double price) {
	
		String method;int counter=-1; int count=0;int option=0, control=0; boolean checkint=false; int ii=0;
		do {
		try {
		System.out.println("Total Price :" + price);
		System.out.println("-----Payment Transfer-----");
		System.out.println("1.) Maybank"+"\n2.) Public Bank"+"\n3.) Hong Leong Bank"+"\n4.) Credit Card"+"\n Please enter your choice (1-4) :");
		System.out.println("------------------------------");
		int choice = Integer.parseInt(in.nextLine());
		switch(choice) {
		case 1: method ="Maybank";
	    counter=-1;
		while(counter==-1) {
		System.out.println("Please enter the amount :");
		double amount = Double.parseDouble(in.nextLine());
		ii=(int)amount;
		checkint =ft.check_int(ii, 1);
		if(amount -price >=0) {
			System.out.println("Payment Succesfully Done!!");
			
			ft.addtoList(id, name, true, method);
			order.add(new Payment(id,name,true,method));
			counter=1;
			count=1;
			control=1;
		}
		else if(amount<price || checkint ==false) {
			
			System.out.println("Amount enter is insufficient, pls retry");
			System.out.println("Do you want to paid again or hold the order first? (If hold press 1, else press 2 to paid again) :");
			option = Integer.parseInt(in.nextLine());
			if(option==1) {
				//Hold the order//
				counter=1;
				count=-1;
				ft.addtoList(id, name, false, "NONE");
				order.add(new Payment(id,name,false,"NONE"));
				control=1;
			}
			else {
				//Paid Again
				counter=-1;
				
			}
		}
		}
		break;
		case 2:method ="Public Bank";
		counter=-1;
		while(counter==-1) {
		System.out.println("Please enter the amount :");
		double amount = Double.parseDouble(in.nextLine());
		ii=(int)amount;
		checkint =ft.check_int(ii, 1);
		if(amount -price >=0) {
			System.out.println("Payment Succesfully Done!!");
			ft.addtoList(id, name, true, method);
			counter=1;
			count=1;
			control=1;
		}
		else if(amount<price || checkint==false) {
			System.out.println("Amount enter is insufficient, pls retry");
			System.out.println("Do you want to paid again or hold the order first? (If hold press 1, else press 2 to paid again) :");
			option = Integer.parseInt(in.nextLine());
			if(option==1) {
				//Hold the order
				counter=1;
				count=-1;
				ft.addtoList(id, name, false, "NONE");
				control=1;
			}
			else {
				//Paid Again
				counter=-1;
				
			}
		}
		}
		break;
		case 3:
			method ="Hong Leong Bank";
			counter=-1;
			while(counter==-1) {
			System.out.println("Please enter the amount :");
			double amount = Double.parseDouble(in.nextLine());
			ii=(int)amount;
			checkint =ft.check_int(ii, 1);
			if(amount -price >=0) {
				System.out.println("Payment Succesfully Done!!");
				ft.addtoList(id, name, true, method);
				order.add(new Payment(id,name,true,method));
				counter=1;
				count=1;
				control=1;
			}
			else if(amount<price || checkint==false) {
				System.out.println("Amount enter is insufficient, pls retry");
				System.out.println("Do you want to paid again or hold the order first? (If hold press 1, else press 2 to paid again) :");
				option = Integer.parseInt(in.nextLine());
				if(option==1) {
					//Hold the order
					counter=1;
					count=-1;
					order.add(new Payment(id,name,false,"NONE"));			
					ft.addtoList(id, name, false, "NONE");
					control=1;
				}
				else {
					//Paid Again
					counter=-1;
					
				}
			}
			}
			break;
		case 4:
			method ="Credit Card";
			counter=-1;
			while(counter==-1) {
			System.out.println("Please enter the amount :");
			double amount = Double.parseDouble(in.nextLine());
			ii=(int)amount;
			checkint =ft.check_int(ii, 1);
			if(amount -price >=0) {
				System.out.println("Payment Succesfully Done!!");
				order.add(new Payment(id,name,true,method));
				ft.addtoList(id, name, true, method);

				counter=1;
				count=1;
				control=1;
			}
			else if(amount<price || checkint==false) {
				System.out.println("Amount enter is insufficient, pls retry");
				System.out.println("Do you want to paid again or hold the order first? (If hold press 1, else press 2 to paid again) :");
				option = Integer.parseInt(in.nextLine());
				if(option==1) {
					//Hold the order
					counter=1;
					count=-1;
					order.add(new Payment(id,name,false,"NONE"));
					ft.addtoList(id, name, false, "NONE");
					control=1;

				}
				else {
					//Paid Again
					counter=-1;
					
				}
			}
			}
			break;
			default :System.out.println("Error input, pls retry....");
		}
	
	

	

	
	
	
	
	
}
		catch(NegativeNumberException ex) {
			System.out.println("Input insert is invalid");
		}
		catch(RuntimeException ex) {
			System.out.println("Input insert is invalid");
		}
		
		}while(control!=1);
		return count;
	}
}
