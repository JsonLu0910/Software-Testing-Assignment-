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

public class Test_cases {

private Object[] getData() {
		
		return new Object[] {
			new Object[] {"jason0910","abc123"},
			new Object[] {"jacklien5588","nnn222"},
			new Object[] {"john2231","hjk234"},
			new Object[] {"janeL123","lmn555"},
			
			};
		}

private Object[] getPaymentData() {
	
	return new Object[] {
		new Object[] {"jason0910|A7","JASON LU",true,704.0,"Carrot Cake","MaybankBank"},
		
		
		};
	}


@Test
@Parameters(method="getData")
public void test_LoginMethod(String ID, String password) throws IOException {
		Scanner in = new Scanner(System.in);
		ArrayList<Order> order= new ArrayList<Order>();
		ArrayList<Item> Item= new ArrayList<Item>();
		ArrayList<Customer> customer= new ArrayList<Customer>();
		ArrayList<Orderdetails>details = new ArrayList<Orderdetails>();
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
							
							
						}
					

			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		breader.close();
		
		
		Order o = mock(Order.class);
		
		Customer cs = new Customer(o);
		boolean check= cs.checkLogin(ID, password, order);
	
		
		assertTrue("Name or Password enter is wrong",check);
		
		
	}

@Test
@Parameters(method="getPaymentData")
public void test_Paid(String id, String name, boolean stats, double price, String item, String method) throws FileNotFoundException {
		ArrayList<Order>order = new ArrayList<Order>();
		Order o = mock(Order.class);
		Payment pt = new Payment(o);
		
		Scanner in = new Scanner(System.in);
		//If the user enter the amount is less than price//
		//It will ask user to choose hold the order or paid again//
		//If hold the order, then Paid() method will return -1
		//Else if paid again, and paid with sufficient amount, then it will return 1
		
		int result = pt.Paid(order, in, id, name, stats, price);
		//if return 1 means the payment done, then the test will pass
		//if return -1 means the payment undone, then the test will fail
		
		InOrder imock = inOrder(o);
		imock.verify(o, times(1)).addtoList(id, name, stats, method);
		
		
	}

@Test
public void test_search_item() throws IOException {
	ArrayList<Order> order= new ArrayList<Order>();
	ArrayList<Item> Item= new ArrayList<Item>();
	ArrayList<Customer> customer= new ArrayList<Customer>();
	ArrayList<Orderdetails>details = new ArrayList<Orderdetails>();
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
						
						
					}
				

		}
	}
	catch (FileNotFoundException e) {
		System.out.println("File not found");
	}
	breader.close();
	
	Order o = mock(Order.class);
	Scanner in = new Scanner(System.in);
	Item i = new Item(o);
	
	when(o.found()).thenReturn("Item Found");	
	i.search_item(order, in, o);
	//If item not found, then found() method wont be invoke, the test will fail//
	InOrder imock = inOrder(o);
	imock.verify(o, times(1)).found();
	
	
}


}
