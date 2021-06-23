package assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Item extends Order {
private double Mprice;
private double NMprice;

	public Item(String id,String name, String type, double Mprice,double NMprice, boolean status) {
		super( id,name,status,type);
		this.Mprice= Mprice;
		this.NMprice= NMprice;

	}
	
	Order ft;
	public Item(Order ft) {
		super(ft);
		this.ft= ft;
		
	}
	
	public void showinfo() {
			System.out.println("Item ID:"+getId()+"||Item Name :"+getName()+"|| Item type :"+getType()+"|| Price (Member) : RM"+getMprice()+" ||Price (Non-member) : RM"+getNMprice());
	
	}

	public double getMprice() {
		return Mprice;
	}
	
	public double getNMprice() {
		return NMprice;
	}
	
	public void setMPrice(double NewMPrice) {
		if(NewMPrice <=0){
			throw new NegativeNumberException("Item Price is invalid");
		}
		else {
			this.Mprice = NewMPrice;
		}
	}
	
	public void setNMPrice(double NewNMPrice) {
		if(NewNMPrice <=0){
			throw new NegativeNumberException("Item Price is invalid");
		}
		else {
			this.NMprice = NewNMPrice;
		}
	}
	
	public String totostring() {
		
		return getId()+","+getName()+","+getType()+","+getMprice()+","+getNMprice()+","+getStatus();
	}


	@Override
	public int getPhone() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void search_item(ArrayList<Order>order,Scanner in,Order ORDER) {
		int counter=-1;int c=-1;
		while(counter==-1) {
			c=-1;
			for(Order o :order) {
				if(o instanceof Item) {
					o.showinfo();
				}
			}
		System.out.println("Please enter the Item ID (exp:AXX) :");
		String item_id = in.nextLine();
		item_id = item_id.toUpperCase();
		boolean check=ft.validation(item_id, 3);
		if(check ==true) {
			for(Order oo :order) {
				if(item_id.contentEquals(oo.getId()) && oo instanceof Item ) {
					
					oo.showinfo();
					System.out.println(ft.found());
					c=1;
					counter=1;
					System.out.println("Do you want to search for another? (If yes press 1, else press 2");
					int choices = Integer.parseInt(in.nextLine());
					if(choices ==1) {
						counter=-1;
					}
					else {
						counter=1;
					}
				}
				
			}
			if(c==-1) {
				System.out.println("Item not found, do you want to retry? (if yes press 1, else press 2) :");
				int choice = Integer.parseInt(in.nextLine());
				if(choice ==1) {
					counter=-1;
				}
				else {
					counter=1;
				}
			}
			counter=1;
		}
		else {
			counter=-1;
		}
		
		}
	}


	
	
	
}
