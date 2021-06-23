package assignment;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

@RunWith(JUnitParamsRunner.class)

public class Test_Order {
	@Test

	public void test_Order() throws IOException {
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
		
		Order o = mock(Order.class);
		Scanner in = new Scanner(System.in);
		Orderdetails od = new Orderdetails(o);
		
		od.order(order, Item, customer, in, p, o);
		
		InOrder imock = inOrder(o);
		//if user successfully log in this method should be invoke
		imock.verify(o,times(1)).welcome(true);
		//if user insert MELAKA as their state this method should invoke
		imock.verify(o,times(1)).checkState("MELAKA");
		// if user confirm the order, no matter the order is Paid or Unpaid this method should invoke
		imock.verify(o, times(1)).confirm();
		
		
		
	}




}
