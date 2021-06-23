package assignment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Orderdetails extends Order {
	private double price;
	private int quantity;
	private String address;
	 Random randomGenerator = new Random();
		public Orderdetails(String id,String name,String item, boolean status, double price,int quantity, String address) {
			super(id,name,status,item);
			this.price=price;
			this.quantity=quantity;
			this.address=address;
		}

		
		
		Order ft;
		public Orderdetails(Order ft) {
		super(ft);
		this.ft =ft;
	}

		@Override
		public void showinfo() {
			String status = null;	
			if(getStatus() == true) {
				 status = "PAID & READY TO DELIVER";
			}
			else {
				status="PENDING FOR PAYMENT";
			}
			System.out.println("Name :" + getName()+" ||Order ID :"+getId()+" ||STATUS :"+status+" ||Item :" +getType()+ " ||Quantity :"+getQuantity()+ " ||Price (including delivery fee) : RM"+getPrice()+ " ||Address -"+getAddress());
			
		}
		
		public int getQuantity() {
			return quantity;
		}
		
		public double getPrice() {
			return price;
		}
		
		public String getAddress() {
			return address;
		}
		
		public String totostring() {
			return getId()+","+getName()+","+getType()+","+getQuantity()+","+getPrice()+","+getAddress()+","+getStatus();
		}

		@Override
		public int getPhone() {
			// TODO Auto-generated method stub
			return 0;
		}

		public   void order(ArrayList<Order>order, ArrayList<Item>item, ArrayList<Customer>customer,Scanner in,ArrayList<Payment>p,Order ORDER) throws FileNotFoundException {
			int counter=-1, i =0,quantity=0,confirm=0,stop=0,count=0, Counte=-1, counterstop=0, confirmation=0, k=0, q=0, no=0, n=-1;
			double total=0;
			String Area =null;
			double delivery_fee =0;
			Customer cc= new Customer(ORDER);
			Orderdetails od = new Orderdetails(ORDER);
			
			
			
			
			boolean checkacc = false; boolean uu=false;boolean aa=false; boolean ff =false;
			String memeber_name = null , pw =null; String address=null, Itemname=null, name=null, order_ID =null;
			do {
				
			System.out.println("Are you a member of Ms Kiah Bakery? (if Yes press 1, if No press 2) :");
			int choice = Integer.parseInt(in.nextLine());
			ff =ft.check_int(choice, 1);
			if(choice ==1) {
				int randomInt = randomGenerator.nextInt(10000);

				counter=-1;
				while(counter==-1) {
					
					System.out.println("Please enter your id :");
					 memeber_name = in.nextLine();
					System.out.println("Please enter your password :");
					 pw = in.nextLine();
					  uu = ft.validation(memeber_name, 3);
					  aa = ft.validation(pw, 3);
					 for(Order ooo :order) {
						 if(memeber_name.contentEquals(ooo.getId()) && pw.contentEquals(ooo.getType()) && ooo instanceof Customer) {
							 name =ooo.getId();
						 }
					 }
					 checkacc=cc.checkLogin(memeber_name, pw, order);
					 if(checkacc==true) {
						 counter=1;
						 checkacc=true;
						 
					 }
					 else {
						 System.out.println("ID or password enter is invalid, do you want to retry? (If yes press 1, else press 2) :");
						 int c = Integer.parseInt(in.nextLine());
						 if(c==1) {
							 counter=-1;
						 }else {
							 counter=1;
							 checkacc=false;
						 }
					 }
				}
				
				if(checkacc==true && counter!=-1 && uu ==true && aa ==true) {
					
					do {
						try {
						System.out.println(name);
					// this is to display the item with the normal price 
					System.out.println("---Item List---\n"+"1.) Normal Price item\n"+"2.) Promotional Item\n"+"3.) Type: Cake\n"+"4.) Type: Pastry"+"\n5.) Exit");
					System.out.println("Please select your option (1-5)");
					int options = Integer.parseInt(in.nextLine());
					boolean c=false;
					switch(options) {
					case 1: 
						while(c==false) {
						for(Order o:order) {
							if(o instanceof Item && o.getStatus())
							System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
						}
						System.out.println("Please enter the Item ID to order(exp:AX) :");
						String s2= in.nextLine();
						String itemid = s2.toUpperCase();
						
						for(Order o:order) {
							if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getStatus()) {
								o.showinfo();
								System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
								confirmation = Integer.parseInt(in.nextLine());
								counter=-1;
								while(confirmation ==1) {
									if(counter==-1) {
									System.out.println("Please enter the quantity you want :");
									quantity = Integer.parseInt(in.nextLine());
									for(Item s :item) {
										if(itemid.contentEquals(s.getId())) {
											Itemname = s.getName();
											total = quantity*s.getMprice();
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
										}
									}
									System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
									int Confirmation = Integer.parseInt(in.nextLine());
									if(Confirmation ==1) {
										confirmation=2;
										//this id is enter by the staff once the customer confirm to order
										n=-1;
										while(n==-1) {
										System.out.println("Please enter the Order ID :");
										order_ID = in.nextLine();
										order_ID = order_ID.toUpperCase();
										order_ID = order_ID+"|"+memeber_name+"|"+itemid;
										if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
											System.out.println("Order id already exist, pls retry");
											n=-1;
										}
										else {
											n=1;
										}
										}
										c=true;
									}
									else {
										counter=-1;
										c=false;
									}
								}
							}
								
								
							}
							
						}
						
						
						if(c==true) {
							od.MemberCreateRecipt(order,name,order_ID,Itemname,false,total,quantity,in);
							
						}
						else {
							
								c=false;
						
						}
					}
						break;
					case 2:
						k=0;
						// this is to display the currently promotional item
						System.out.println("----Promotional Item-----");
						c =false; counter=-1;Itemname=null;
						while(c==false) {
							for(Order o:order) {
								if(o instanceof Item && !o.getStatus())
								System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
							}
							System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
							int look = Integer.parseInt(in.nextLine());
							if(look ==1) {
							System.out.println("Please enter the Item ID to order(exp:AX) :");
							String s2= in.nextLine();
							String itemid = s2.toUpperCase();
							for(Order o:order) {
								if(itemid.contentEquals(o.getId()) && o instanceof Item && !o.getStatus()) {
									o.showinfo();
									System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
									confirmation = Integer.parseInt(in.nextLine());
									counter=-1;
									while(confirmation ==1) {
										if(counter==-1) {
										System.out.println("Please enter the quantity you want :");
										quantity = Integer.parseInt(in.nextLine());
										for(Item s :item) {
											if(itemid.contentEquals(s.getId())) {
												Itemname = s.getName();
												total = quantity*s.getMprice()*0.95;
												System.out.println("Total Price : RM"+total);
												if(total<25) {
													total+=3;
													System.out.println("An Additional RM3 is added, because our minimum consume is 25");
												}
												System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
											}
										}
										System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
										int Confirmation = Integer.parseInt(in.nextLine());
										if(Confirmation ==1) {
											confirmation=2;
											//this id is enter by the staff once the customer confirm to order
											n=-1;
											while(n==-1) {
											System.out.println("Please enter the Order ID :");
											order_ID = in.nextLine();
											order_ID = order_ID.toUpperCase();
											order_ID = order_ID+"|"+memeber_name+"|"+itemid;
											if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
												System.out.println("Order id already exist, pls retry");
												n=-1;
											}
											else {
												n=1;
											}
											}
											c=true;
										}
										else {
											counter=-1;
											c=false;
										}
									}
								}
									
								}
								
							}
							
							
							if(c==true) {
								od.MemberCreateRecipt(order,name,order_ID,Itemname,false,total,quantity,in);
							}
							else {
								System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
								int trying = Integer.parseInt(in.nextLine());
								if(trying== 1) {
									c=false;
								}
								else {
									c=true;
								}
							}
							}
							else {
								c=true;
							}
						}
						break;
						//this is to display the item with type of cake
					case 3:
						k=0;
						c =false; counter=-1;Itemname=null;
						System.out.println("----Cake-----");
						while(c==false) {
							for(Order o:order) {
								if(o instanceof Item && o.getType().contentEquals("cake"))
								System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
							}
							System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
							int look = Integer.parseInt(in.nextLine());
							if(look ==1) {
							System.out.println("Please enter the Item ID to order(exp:AX) :");
							String s2= in.nextLine();
							String itemid = s2.toUpperCase();
							for(Order o:order) {
								if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getType().contentEquals("cake")) {
									o.showinfo();
									System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
									confirmation = Integer.parseInt(in.nextLine());
									counter=-1;
									while(confirmation ==1) {
										if(counter==-1) {
										System.out.println("Please enter the quantity you want :");
										quantity = Integer.parseInt(in.nextLine());
										for(Item s :item) {
											if(itemid.contentEquals(s.getId()) && s.getStatus()) {
												Itemname = s.getName();
												total = quantity*s.getMprice();
												System.out.println("Total Price : RM"+total);
												if(total<25) {
													total+=3;
													System.out.println("An Additional RM3 is added, because our minimum consume is 25");
												}
												System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
											}
											else if(itemid.contentEquals(s.getId()) && !s.getStatus()) {
												Itemname = s.getName();
												total = quantity*s.getMprice()*0.95;
												System.out.println("Total Price : RM"+total);
												if(total<25) {
													total+=3;
													System.out.println("An Additional RM3 is added, because our minimum consume is 25");
												}
												System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
												
												
											}
										}
										System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
										int Confirmation = Integer.parseInt(in.nextLine());
										if(Confirmation ==1) {
											confirmation=2;
											//this id is enter by the staff once the customer confirm to order
											n=-1;
											while(n==-1) {
											System.out.println("Please enter the Order ID :");
											order_ID = in.nextLine();
											order_ID = order_ID.toUpperCase();
											order_ID = order_ID+"|"+memeber_name+"|"+itemid;
											if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
												System.out.println("Order id already exist, pls retry");
												n=-1;
											}
											else {
												n=1;
											}
											}
											c=true;
										}
										else {
											counter=-1;
											c=false;
										}
									}
								}
									
								}
								else {
									c =false;
								}
								
							}
							
							
							if(c==true) {
								od.MemberCreateRecipt(order,name,order_ID,Itemname,false,total,quantity,in);
							}
							else {
								System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
								int trying = Integer.parseInt(in.nextLine());
								if(trying== 1) {
									c=false;
								}
								else {
									c=true;
								}
							}
							}
							else {
								c=true;
							}
						}
						
						break;
						//this is to display the item with type of pastry
						case 4:	System.out.println("----Pastry-----");
						k=0;
						c =false; counter=-1;Itemname=null;
						System.out.println("----Pastry-----");
						while(c==false) {
							for(Order o:order) {
								if(o instanceof Item && o.getType().contentEquals("pastry"))
								System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
							}
							System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
							int look = Integer.parseInt(in.nextLine());
							if(look ==1) {
							System.out.println("Please enter the Item ID to order(exp:AX) :");
							String s2= in.nextLine();
							String itemid = s2.toUpperCase();
							for(Order o:order) {
								if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getType().contentEquals("pastry")) {
									o.showinfo();
									System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
									confirmation = Integer.parseInt(in.nextLine());
									counter=-1;
									while(confirmation ==1) {
										if(counter==-1) {
										System.out.println("Please enter the quantity you want :");
										quantity = Integer.parseInt(in.nextLine());
										for(Item s :item) {
											if(itemid.contentEquals(s.getId())) {
												Itemname = s.getName();
												total = quantity*s.getMprice();
												System.out.println("Total Price : RM"+total);
												if(total<25) {
													total+=3;
													System.out.println("An Additional RM3 is added, because our minimum consume is 25");
												}
												System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
											}
											else if(itemid.contentEquals(s.getId()) && !s.getStatus()) {
												Itemname = s.getName();
												
												total = quantity*s.getMprice()*0.95;System.out.println("Total Price : RM"+total);
												if(total<25) {
													total+=3;
													System.out.println("An Additional RM3 is added, because our minimum consume is 25");
												}
												System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
												
												
											}
										}
										System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
										int Confirmation = Integer.parseInt(in.nextLine());
										if(Confirmation ==1) {
											confirmation=2;
											//this id is enter by the staff once the customer confirm to order
											n=-1;
											while(n==-1) {
											System.out.println("Please enter the Order ID :");
											order_ID = in.nextLine();
											order_ID = order_ID.toUpperCase();
											order_ID = order_ID+"|"+memeber_name+"|"+itemid;
											if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
												System.out.println("Order id already exist, pls retry");
												n=-1;
											}
											else {
												n=1;
											}
											}
											c=true;
										}
										else {
											counter=-1;
											c=false;
										}
									}
								}
									
								}
								else {
									c =false;
								}
								
							}
							
							
							if(c==true) {
								od.MemberCreateRecipt(order,name,order_ID,Itemname,false,total,quantity,in);
							}
							else {
								System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
								int trying = Integer.parseInt(in.nextLine());
								if(trying== 1) {
									c=false;
								}
								else {
									c=true;
								}
							}
							}
							else {
								c=true;
							}
						}
						
						break;
						case 5: 
							counterstop=2;
							break;
						default:{
							System.out.println(" Input out of range, pls retry");
						}
					}
					System.out.println("Is there any more item to look for? (if yes press 1, else press 2) :");
					counterstop= Integer.parseInt(in.nextLine());
						}
						catch(RuntimeException ex ) {
							System.out.println("Error input, pls retry");
						}
					}while(counterstop !=2);

			
			}	
				
				
			}	
				
			
			else  if (choice ==2){
				int a=-1;
				int checkPhoneNo [] = new int[customer.size()];
				for(int w=0; w<customer.size(); w++) {
						Customer o = customer.get(w);
						checkPhoneNo[w] = o.getphone();
						System.out.println(checkPhoneNo[w]);
				}
				int lll=0; boolean c=false;
				while(a==-1) {
					
				System.out.println("Please enter your first name :");
				String f_name = in.nextLine();
				System.out.println("Please enter your last name :");
				String l_name = in.nextLine();
				String non_memebername = f_name.toUpperCase()+" "+l_name.toUpperCase();
				System.out.println("Please enter your phoneNo :");
				int phoneNO = Integer.parseInt(in.nextLine());
				boolean c1=ft.validation(f_name, 1);
				boolean c2=ft.validation(l_name, 1);
				
				lll=0;
				while(a==-1) {
					if(lll<checkPhoneNo.length) {
					 if( phoneNO == checkPhoneNo[lll]){
						c=false;
						a=1;
					}
					else {
						a=-1;
					}
				
				}
					else if(c1 ==true && c2==true) {
					c=true;
					a=1;
				}
				
				else {
					c=false;
				}
					lll++;
				}
				
				
				if(c==true) {
					od.NonMemberCreateRecipt(order,in,non_memebername,item);
					a=1;
				}
				else {
				
					System.out.println("Erro input, press 1 to retry 2 to exit");
					int ca = Integer.parseInt(in.nextLine());
					if(ca==1) {
						a=-1;
					}
					else {
						a=1;
					}
				}
				
				}
			}
					
				System.out.println("Is there any more item you want to book for? (Press 2 to exit, else press 1) :");
				stop =Integer.parseInt(in.nextLine());
			}while(stop!=2);
			
		}
		
		//This method is for Member only
		// This function is to create a receipt when the booking is done by the user, and it also can prove that the user order the item	
		public  void MemberCreateRecipt(ArrayList<Order>order,String name, String id,String item, boolean stats, double price, int Quantity,Scanner in) throws FileNotFoundException {
			String location =null, Area=null;
			double delivery_fee=0; boolean check=false;
			int counter=-1, confirmation=1;
			
			Payment pen = new Payment(ft);
			System.out.println("Please enter address to deliver");
			
			while(counter==-1) {
				
				System.out.println("Please enter State :");
				String s1 = in.nextLine();
				String state = s1.toUpperCase();
				if(state.contentEquals("MELAKA")) {
					System.out.println(ft.checkState(state));
					System.out.println("Please enter District :");
					String District = in.nextLine();
					System.out.println("Please enter unit :");
					int unit = Integer.parseInt(in.nextLine());
					System.out.println("Please enter Stree_no :");
					int street_no = Integer.parseInt(in.nextLine());
					System.out.println("Please enter Postal Code :");
					String postal_code = in.nextLine();
				
					int control=-1; boolean aa=false;
					while(control==-1) {
						System.out.println("----Area----");
						System.out.println("1.) Alor Gajah-Delivery Fee: RM2.5\n"+"2.) Asahan-Delivery Fee: RM4.00\n"+"3.) Ayer Keroh-Delivery Fee: RM5.00\n"+"4.) Batu Berendam-Delivery Fee: RM3.5\n"+"5.) Bemban-Delivery Fee: RM4.00");
						System.out.println("6.) Bukit Beruang-Delivery Fee: RM3.5\n"+"7.) Durian Tunggal-Delivery Fee: RM3.5\n"+"8.) Jasin-Delivery Fee: RM4.00\n"+"9.) Kuala Linggi-Delivery Fee: RM3.00\n"+"10.) Kuala Sungai Baru-Delivery Fee: RM3.00");
						System.out.println("11.) Lubok China-Delivery Fee: RM3.00\n"+"12.) Masjid Tanah-Delivery Fee: RM2.5\n"+"13.) Melaka Tengah-Delivery Fee: RM5.00\n"+"14.) Merlimau-Delivery Fee: RM4.00\n"+"15.) Selandar-Delivery Fee: RM4.00");
						System.out.println("16.) Sungai Rambai-Delivery Fee: RM2.5\n"+"17.) Sungai Udang-Delivery Fee: RM2.5\n"+"18.) Tanjong Kling-Delivery Fee: RM2.5\n"+"19.) Ujong Pasir-Delivery Fee: RM2.5\n");
						System.out.println("Please select the Area you stay in(1-19) :");
						int choices = Integer.parseInt(in.nextLine());
						switch(choices) {
						case 1 : 
							Area ="Alor Gajah";
							delivery_fee =2.5;
							control=1;
							aa=true;
							break;
						case 2 : 
							Area ="Asahan";
							delivery_fee =4.00;
							control=1;aa=true;
							break;
						case 3: 
							Area ="Ayer Keroh";
							delivery_fee =5.00;
							control=1;aa=true;
							break;
						case 4 : 
							Area ="Batu Berendam";
							delivery_fee =3.5;
							control=1;aa=true;
							break;
						case 5 : 
							Area ="Bemban";
							delivery_fee =4.00;
							control=1;aa=true;
							break;
						case 6 : 
							Area ="Bukit Beruang";
							delivery_fee =3.5;
							control=1;aa=true;
							break;
						case 7 : 
							Area ="Durian Tunggal";
							delivery_fee =3.5;
							control=1;aa=true;
							break;
						case 8 : 
							Area ="Jasin";
							delivery_fee =4.00;
							control=1;aa=true;
							break;
						case 9 : 
							Area ="Kuala Linggi";
							delivery_fee =3.00;
							control=1;aa=true;
							break;
						case 10 : 
							Area ="Kuala Sungai Baru";
							delivery_fee =3.00;
							control=1;aa=true;
							break;
						case 11 : 
							Area ="Lubok China";
							delivery_fee =3.00;
							control=1;aa=true;
							break;
						case 12 : 
							Area ="Masjid Tanah";
							delivery_fee =2.5;
							control=1;aa=true;
							break;
						case 13 : 
							Area ="Melaka Tengah";
							delivery_fee =5.00;
							control=1;aa=true;
							break;
						case 14 : 
							Area ="Merlimau";
							delivery_fee =4.00;
							control=1;aa=true;
							break;
						case 15 : 
							Area ="Selandar";
							delivery_fee =4.00;
							control=1;aa=true;
							break;
						case 16 : 
							Area ="Sungai Rambai";
							delivery_fee =2.5;
							control=1;aa=true;
							break;
						case 17 : 
							Area ="Sungai Udang";
							delivery_fee =2.5;
							control=1;aa=true;
							break;
						case 18: 
							Area ="Tanjong Kling";
							delivery_fee =2.5;
							control=1;aa=true;
							break;
						case 19 : 
							Area ="Ujong Pasir";
							delivery_fee =2.5;
							control=1;aa=true;
							break;
							default:
								control=-1;
								aa=false;
							System.out.println("Error input, pls retry");
						}
						
					}
					
					
					location = "State: "+state+"- Area: "+Area+"- District: "+District+"- Postal Code: "+postal_code+"- Street No.:"+street_no+"- Unit:"+unit;
					counter=1;
					check =true;
				}
				else {
					System.out.println("State which is not in Melaka is not able to deliver, pls retry...");					
					counter=-1;
					check=false;
				}
			}
			int count=0;
			if(check==true) {
				price += delivery_fee;
				System.out.println(price);
				
					
					count=pen.Paid(order,in,id,name,stats,price);
					
			System.out.println(count);
			if(count ==1) {
			order.add(new Orderdetails(id,name,item,true,price,Quantity,location));
			
			}
			else if(count ==-1){
				order.add(new Orderdetails(id,name,item,false,price,Quantity,location));
			}
			
			PrintWriter update = new PrintWriter("Cake.txt");
			for(Order o :order)
				
				update.println(o.totostring());
				update.close();
				System.out.println("Order Made");
				ft.confirm();
		}
			else {
				System.out.println("No order made");
			}
		}
	
	
		//This method is for Non-member only
		// This function is to create a receipt when the booking is done by the user, and it also can prove that the user order the item	
		public  void NonMemberCreateRecipt(ArrayList<Order>order,Scanner in, String name, ArrayList<Item>item) throws FileNotFoundException {
		String state =null,Area=null, location =null;
		boolean check =false;
		int counter=-1; 
		double delivery_fee=0;
		Payment pen = new Payment(ft);
		while(counter==-1) {
		System.out.println("For Your Information, we only deliver in Melaka state only, Other State unavailable to deliver....");
		System.out.println("Please Enter Your Address"+"\n State :");
		String s1 = in.nextLine();
		state = s1.toUpperCase();
		if(state.contentEquals("MELAKA")) {
			System.out.println(ft.checkState(state));
			System.out.println("Please enter District :");
			String District = in.nextLine();
			System.out.println("Please enter unit :");
			int unit = Integer.parseInt(in.nextLine());
			System.out.println("Please enter Stree_no :");
			int street_no = Integer.parseInt(in.nextLine());
			System.out.println("Please enter Postal Code :");
			String postal_code = in.nextLine();
			int control=-1; boolean aa=false;
			while(control==-1) {
				System.out.println("----Area----");
				System.out.println("1.) Alor Gajah-Delivery Fee: RM2.5\n"+"2.) Asahan-Delivery Fee: RM4.00\n"+"3.) Ayer Keroh-Delivery Fee: RM5.00\n"+"4.) Batu Berendam-Delivery Fee: RM3.5\n"+"5.) Bemban-Delivery Fee: RM4.00");
				System.out.println("6.) Bukit Beruang-Delivery Fee: RM3.5\n"+"7.) Durian Tunggal-Delivery Fee: RM3.5\n"+"8.) Jasin-Delivery Fee: RM4.00\n"+"9.) Kuala Linggi-Delivery Fee: RM3.00\n"+"10.) Kuala Sungai Baru-Delivery Fee: RM3.00");
				System.out.println("11.) Lubok China-Delivery Fee: RM3.00\n"+"12.) Masjid Tanah-Delivery Fee: RM2.5\n"+"13.) Melaka Tengah-Delivery Fee: RM5.00\n"+"14.) Merlimau-Delivery Fee: RM4.00\n"+"15.) Selandar-Delivery Fee: RM4.00");
				System.out.println("16.) Sungai Rambai-Delivery Fee: RM2.5\n"+"17.) Sungai Udang-Delivery Fee: RM2.5\n"+"18.) Tanjong Kling-Delivery Fee: RM2.5\n"+"19.) Ujong Pasir-Delivery Fee: RM2.5\n");
				System.out.println("Please select the Area you stay in(1-19) :");
				int choices = Integer.parseInt(in.nextLine());
				switch(choices) {
				case 1 : 
					Area ="Alor Gajah";
					delivery_fee =2.5;
					control=1;
					aa=true;
					break;
				case 2 : 
					Area ="Asahan";
					delivery_fee =4.00;
					control=1;aa=true;
					break;
				case 3: 
					Area ="Ayer Keroh";
					delivery_fee =5.00;
					control=1;aa=true;
					break;
				case 4 : 
					Area ="Batu Berendam";
					delivery_fee =3.5;
					control=1;aa=true;
					break;
				case 5 : 
					Area ="Bemban";
					delivery_fee =4.00;
					control=1;aa=true;
					break;
				case 6 : 
					Area ="Bukit Beruang";
					delivery_fee =3.5;
					control=1;aa=true;
					break;
				case 7 : 
					Area ="Durian Tunggal";
					delivery_fee =3.5;
					control=1;aa=true;
					break;
				case 8 : 
					Area ="Jasin";
					delivery_fee =4.00;
					control=1;aa=true;
					break;
				case 9 : 
					Area ="Kuala Linggi";
					delivery_fee =3.00;
					control=1;aa=true;
					break;
				case 10 : 
					Area ="Kuala Sungai Baru";
					delivery_fee =3.00;
					control=1;aa=true;
					break;
				case 11 : 
					Area ="Lubok China";
					delivery_fee =3.00;
					control=1;aa=true;
					break;
				case 12 : 
					Area ="Masjid Tanah";
					delivery_fee =2.5;
					control=1;aa=true;
					break;
				case 13 : 
					Area ="Melaka Tengah";
					delivery_fee =5.00;
					control=1;aa=true;
					break;
				case 14 : 
					Area ="Merlimau";
					delivery_fee =4.00;
					control=1;aa=true;
					break;
				case 15 : 
					Area ="Selandar";
					delivery_fee =4.00;
					control=1;aa=true;
					break;
				case 16 : 
					Area ="Sungai Rambai";
					delivery_fee =2.5;
					control=1;aa=true;
					break;
				case 17 : 
					Area ="Sungai Udang";
					delivery_fee =2.5;
					control=1;aa=true;
					break;
				case 18: 
					Area ="Tanjong Kling";
					delivery_fee =2.5;
					control=1;aa=true;
					break;
				case 19 : 
					Area ="Ujong Pasir";
					delivery_fee =2.5;
					control=1;aa=true;
					break;
					default:
						control=-1;
						aa=false;
					System.out.println("Error input, pls retry");
				}
				
			}
			
			location = "State: "+state+"- Area: "+Area+"- District: "+District+"- Postal Code: "+postal_code+"- Street No.:"+street_no+"- Unit:"+unit;
			counter=1;
			check =true;
			counter=1;
		}
		else {
			check=false;
			counter=-1;
			System.out.println("State which is not in Melaka is not able to deliver, pls retry...");					
		}
	}
		
		
		if(check == true) {
			int counterstop=0;
			do {
				int count=0;
				int confirmation =0, quantity=0, k=0;
				String Itemname =null;
				double total=0;
				String order_ID =null;
				// this is to display the item with the normal price 
				System.out.println("---Item List---\n"+"1.) Normal Price item\n"+"2.) Promotional Item\n"+"3.) Type: Cake\n"+"4.) Type: Pastry"+"\n5.) Exit");
				System.out.println("Please select your option (1-5)");
				int options = Integer.parseInt(in.nextLine());
				boolean c=false; int n=-1;
				switch(options) {
				case 1: 
					while(c==false) {
					for(Order o:order) {
						if(o instanceof Item && o.getStatus())
						System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
					}
					System.out.println("Please enter the Item ID to order(exp:AX) :");
					String s2= in.nextLine();
					String itemid = s2.toUpperCase();
					for(Order o:order) {
						if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getStatus()) {
							o.showinfo();
							System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
							confirmation = Integer.parseInt(in.nextLine());
							counter=-1;
							while(confirmation ==1) {
								if(counter==-1) {
								System.out.println("Please enter the quantity you want :");
								quantity = Integer.parseInt(in.nextLine());
								for(Item s :item) {
									if(itemid.contentEquals(s.getId())) {
										Itemname = s.getName();
										total = quantity*s.getNMprice();
										if(total<25) {
											total+=3;
											System.out.println("An Additional RM3 is added, because our minimum consume is 25");
										}
										System.out.println("Item :"+ Itemname+" Price :RM"+s.getNMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
									}
								}
								System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
								int Confirmation = Integer.parseInt(in.nextLine());
								if(Confirmation ==1) {
									
									confirmation=2;
									//this id is enter by the staff once the customer confirm to order
									n=-1;
									while(n==-1) {
									System.out.println("Please enter the Order ID :");
									order_ID = in.nextLine();
									order_ID = order_ID.toUpperCase();
									order_ID = order_ID+"|"+name+"|"+itemid;
									if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
										System.out.println("Order id already exist, pls retry");
										n=-1;
									}
									else {
										n=1;
									}
									}
									c=true;
								}
								else {
									counter=-1;
									c=false;
								}
							}
						}
							
						}
						
					}
					
					
					if(c==true) {
						count=pen.Paid(order, in, order_ID, name, false, total);
						if(count==1) {
							order.add(new Orderdetails(order_ID, name, Itemname, true, total, quantity, location));	
						}
						else {
							order.add(new Orderdetails(order_ID, name, Itemname, false, total, quantity, location));	
						}
						
						PrintWriter update = new PrintWriter("Cake.txt");
						for(Order o :order)
							update.println(o.totostring());
							update.close();
							System.out.println("Order Made");
							ft.confirm();
							}
					else {
						System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
						int trying = Integer.parseInt(in.nextLine());
						if(trying== 1) {
							c=false;
						}
						else {
							c=true;
						}
					}
				}
					break;
				case 2:
					k=0;
					// this is to display the currently promotional item
					System.out.println("----Promotional Item-----");
					c =false; counter=-1;Itemname=null;
					while(c==false) {
						for(Order o:order) {
							if(o instanceof Item && !o.getStatus())
							System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
						}
						System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
						int look = Integer.parseInt(in.nextLine());
						if(look ==1) {
						System.out.println("Please enter the Item ID to order(exp:AX) :");
						String s2= in.nextLine();
						String itemid = s2.toUpperCase();
						for(Order o:order) {
							if(itemid.contentEquals(o.getId()) && o instanceof Item && !o.getStatus()) {
								o.showinfo();
								System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
								confirmation = Integer.parseInt(in.nextLine());
								counter=-1;
								while(confirmation ==1) {
									if(counter==-1) {
									System.out.println("Please enter the quantity you want :");
									quantity = Integer.parseInt(in.nextLine());
									for(Item s :item) {
										if(itemid.contentEquals(s.getId())) {
											Itemname = s.getName();
											total = quantity*s.getNMprice()*0.95;
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getNMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
										}
									}
									System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
									int Confirmation = Integer.parseInt(in.nextLine());
									if(Confirmation ==1) {
										confirmation=2;
										//this id is enter by the staff once the customer confirm to order
										n=-1;
										while(n==-1) {
										System.out.println("Please enter the Order ID :");
										order_ID = in.nextLine();
										order_ID = order_ID.toUpperCase();
										order_ID = order_ID+"|"+name+"|"+itemid;
										if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
											System.out.println("Order id already exist, pls retry");
											n=-1;
										}
										else {
											n=1;
										}
										}
										c=true;
									}
									else {
										counter=-1;
										c=false;
									}
								}
							}
								
							}
							
						}
						
						
						if(c==true) {
							count=pen.Paid(order, in, order_ID, name, false, total);
							if(count==1) {
								order.add(new Orderdetails(order_ID, name, Itemname, true, total, quantity, location));	
							}
							else {
								order.add(new Orderdetails(order_ID, name, Itemname, false, total, quantity, location));	
							}
							PrintWriter update = new PrintWriter("Cake.txt");
							for(Order o :order)
								update.println(o.totostring());
								update.close();
								System.out.println("Order Made");
								ft.confirm();
						}
						else {
							System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
							int trying = Integer.parseInt(in.nextLine());
							if(trying== 1) {
								c=false;
							}
							else {
								c=true;
							}
						}
						}
						else {
							c=true;
						}
					}
					break;
					//this is to display the item with type of cake
				case 3:
					k=0;
					c =false; counter=-1;Itemname=null;
					System.out.println("----Cake-----");
					while(c==false) {
						for(Order o:order) {
							if(o instanceof Item && o.getType().contentEquals("cake"))
							System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
						}
						System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
						int look = Integer.parseInt(in.nextLine());
						if(look ==1) {
						System.out.println("Please enter the Item ID to order(exp:AX) :");
						String s2= in.nextLine();
						String itemid = s2.toUpperCase();
						for(Order o:order) {
							if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getType().contentEquals("cake")) {
								o.showinfo();
								System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
								confirmation = Integer.parseInt(in.nextLine());
								counter=-1;
								while(confirmation ==1) {
									if(counter==-1) {
									System.out.println("Please enter the quantity you want :");
									quantity = Integer.parseInt(in.nextLine());
									for(Item s :item) {
										if(itemid.contentEquals(s.getId()) &&  s.getStatus()) {
											Itemname = s.getName();
											total = quantity*s.getNMprice();
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getNMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
										}
										else if(itemid.contentEquals(s.getId()) && !s.getStatus()) {
											Itemname = s.getName();
											total = quantity*s.getMprice()*0.95;
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
											
											
										}
									}
									System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
									int Confirmation = Integer.parseInt(in.nextLine());
									if(Confirmation ==1) {
										confirmation=2;
										//this id is enter by the staff once the customer confirm to order
										n=-1;
										while(n==-1) {
										System.out.println("Please enter the Order ID :");
										order_ID = in.nextLine();
										order_ID = order_ID.toUpperCase();
										order_ID = order_ID+"|"+name+"|"+itemid;
										if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
											System.out.println("Order id already exist, pls retry");
											n=-1;
										}
										else {
											n=1;
										}
										}
										c=true;
									}
									else {
										counter=-1;
										c=false;
									}
								}
							}
								
							}
							else {
								c =false;
							}
							
						}
						
						
						if(c==true) {
							count=pen.Paid(order, in, order_ID, name, false, total);
							if(count==1) {
								order.add(new Orderdetails(order_ID, name, Itemname, true, total, quantity, location));	
							}
							else {
								order.add(new Orderdetails(order_ID, name, Itemname, false, total, quantity, location));	
							}							PrintWriter update = new PrintWriter("Cake.txt");
							for(Order o :order)
								update.println(o.totostring());
								update.close();
								System.out.println("Order Made");
								ft.confirm();
						}
						else {
							System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
							int trying = Integer.parseInt(in.nextLine());
							if(trying== 1) {
								c=false;
							}
							else {
								c=true;
							}
						}
						}
						else {
							c=true;
						}
					}
					
					break;
					//this is to display the item with type of pastry
					case 4:	System.out.println("----Pastry-----");
					k=0;
					c =false; counter=-1;Itemname=null;
					System.out.println("----Pastry-----");
					while(c==false) {
						for(Order o:order) {
							if(o instanceof Item && o.getType().contentEquals("pastry"))
							System.out.println("Item ID :"+o.getId()+" || Item Name : "+o.getName());
						}
						System.out.println("Is there any item you want to book for? (if yes press 1, else press 2");
						int look = Integer.parseInt(in.nextLine());
						if(look ==1) {
						System.out.println("Please enter the Item ID to order(exp:AX) :");
						String s2= in.nextLine();
						String itemid = s2.toUpperCase();
						for(Order o:order) {
							if(itemid.contentEquals(o.getId()) && o instanceof Item && o.getType().contentEquals("pastry")) {
								o.showinfo();
								System.out.println("Is this the item you want to order? (if yes press 1, else press 2)");
								confirmation = Integer.parseInt(in.nextLine());
								counter=-1;
								while(confirmation ==1) {
									if(counter==-1) {
									System.out.println("Please enter the quantity you want :");
									quantity = Integer.parseInt(in.nextLine());
									for(Item s :item) {
										if(itemid.contentEquals(s.getId()) &&  s.getStatus()) {
											Itemname = s.getName();
											total = quantity*s.getNMprice();
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getNMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
										}
										else if(itemid.contentEquals(s.getId()) && !s.getStatus()) {
											Itemname = s.getName();
											total = quantity*s.getMprice()*0.95;
											System.out.println("Total Price : RM"+total);
											if(total<25) {
												total+=3;
												System.out.println("An Additional RM3 is added, because our minimum consume is 25");
											}
											System.out.println("Item :"+ Itemname+" Price :RM"+s.getMprice()+ " Quantity :"+quantity+" Total Price :RM"+ total);
											
											
										}
									}
									System.out.println("Is this the quantity you want? (if yes press 1, else press 2) :");
									int Confirmation = Integer.parseInt(in.nextLine());
									if(Confirmation ==1) {
										confirmation=2;
										//this id is enter by the staff once the customer confirm to order
										n=-1;
										while(n==-1) {
										System.out.println("Please enter the Order ID :");
										order_ID = in.nextLine();
										order_ID = order_ID.toUpperCase();
										order_ID = order_ID+"|"+name+"|"+itemid;
										if(order_ID.contentEquals(o.getId()) && o instanceof Orderdetails) {
											System.out.println("Order id already exist, pls retry");
											n=-1;
										}
										else {
											n=1;
										}
										}
										c=true;
									}
									else {
										counter=-1;
										c=false;
									}
								}
							}
								
							}
							else {
								c =false;
							}
							
						}
						
						
						if(c==true) {
							count=pen.Paid(order, in, order_ID, name, false, total);
							if(count==1) {
								order.add(new Orderdetails(order_ID, name, Itemname, true, total, quantity, location));	
							}
							else {
								order.add(new Orderdetails(order_ID, name, Itemname, false, total, quantity, location));	
							}							PrintWriter update = new PrintWriter("Cake.txt");
							for(Order o :order)
								update.println(o.totostring());
								update.close();
								System.out.println("Order Made");
								ft.confirm();
						}
						else {
							System.out.println("This item is not found,do you want to retry? (if yes press 1, else press 2) :");
							int trying = Integer.parseInt(in.nextLine());
							if(trying== 1) {
								c=false;
							}
							else {
								c=true;
							}
						}
						}
						else {
							c=true;
						}
					}
					
					break;
					case 5: 
						counterstop=2;
						break;
					default:{
						System.out.println("Error Input, pls retry");
					}
				}
				System.out.println("Is there any more item to look for? (if yes press 1, else press 2) :");
				counterstop= Integer.parseInt(in.nextLine());
				}while(counterstop !=2);
		}
		else {
			System.out.println("No Order made");
		}
	}

	
		// this function allow the user to view their own order, by insert their USERNAME and password
		public  void ViewOrder(ArrayList<Order>order, Scanner in,ArrayList<Orderdetails>details) throws FileNotFoundException {
			try {
			System.out.println("Are you member of Ms Kiah Bakery> (If yes press 1, else press 2) :");
			int ch = Integer.parseInt(in.nextLine());
			if(ch==1) {
			System.out.println("Please enter your username :");
			String username = in.nextLine();
			System.out.println("Please enter your password :");
			String pw = in.nextLine();
			
			int counter=-1,  control=-1;String method;double amount=0; int hold=-1, look=0;
			for(Order o:order) {
				if(o.getId().contentEquals(username) &&o.getType().contentEquals(pw)) {
					do {
					System.out.println("Pls select which order to view\n"+"1.) Paid"+"\n2.) Unpaid"+"\n3.) Exit");
					look = Integer.parseInt(in.nextLine());
					for(Order p :order) {
						if(look==1) {
						if(p.getName().contentEquals(o.getId()) && p instanceof Orderdetails && p.getStatus()) {
							p.showinfo();
							counter=1;
								}
						}
						else {
							if(p.getName().contentEquals(o.getName())  && !p.getStatus()) {
								p.showinfo();
								counter=1;
								System.out.println("Do you want to paid the order? (if yess press 1, else press 2) :");
								int choices = Integer.parseInt(in.nextLine());
								if(choices ==1) {
									System.out.println("Please enter the order id you want to paid for :");
									String order_ID = in.nextLine();
									if(order_ID.contentEquals(p.getId()) && p instanceof Orderdetails){
										for(Orderdetails d:details) {
											if(p.getId().contentEquals(d.getId()) && p.getStatus()==false) {
												System.out.println("Total Price :" + d.getPrice());
												System.out.println("-----Online Bank Transfer-----");
												System.out.println("1.) Maybank"+"\n2.) Public Bank"+"\n3.) Hong Leong Bank"+"\n4.) AM Bank"+"\n Please enter your choice (1-4) :");
												System.out.println("------------------------------");
												control=1;	
												int choice= Integer.parseInt(in.nextLine());
												switch(choice) {
												case 1:  
													
													method = "Maybank";
												while(hold==-1) {
												System.out.println("Please enter the amount :");
												 amount = Double.parseDouble(in.nextLine());
												 if(amount-d.getPrice()>=0) {
													ft.ViewOrderstatus(2);
													 p.setStatus(true);
													 d.setStatus(true);
													 for(Order k :order) {
														 if(p.getId().contentEquals(k.getId()) && k instanceof Payment) {
															 k.setType(method);
															 k.setStatus(true);
														 }
													 }
													 hold=1;
												 }
												 else {
													 System.out.println("Amount enter is insufficient, do you want to hold or paid again");
													 System.out.println("If hold press 1, else press 2");
													 int c= Integer.parseInt(in.nextLine());
													 if(c==1) {
														 ft.ViewOrderstatus(1);
														 hold=1;
													 }
													 else if(c==2) {
														 hold=-1;
													 }
												 }
												}
												 
													break;
												case 2: method ="Public Bank";
												while(hold==-1) {
													System.out.println("Please enter the amount :");
													 amount = Double.parseDouble(in.nextLine());
													 if(amount-d.getPrice()>=0) {
														ft.ViewOrderstatus(2);
														 p.setStatus(true);
														 d.setStatus(true);
														 for(Order k :order) {
															 if(p.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														 hold=1;
													 }
													 else {
														 System.out.println("Amount enter is insufficient, do you want to hold or paid again");
														 System.out.println("If hold press 1, else press 2");
														 int c= Integer.parseInt(in.nextLine());
														 if(c==1) {
															 ft.ViewOrderstatus(1);
															 hold=1;
														 }
														 else if(c==2) {
															 hold=-1;
														 }
													 }
													}
													break;
												case 3: method ="Hong Leong Bank";
												while(hold==-1) {
													System.out.println("Please enter the amount :");
													 amount = Double.parseDouble(in.nextLine());
													 if(amount-d.getPrice()>=0) {
														 ft.ViewOrderstatus(2);
														 p.setStatus(true);
														 d.setStatus(true);
														 for(Order k :order) {
															 if(p.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														 hold=1;
													 }
													 else {
														 System.out.println("Amount enter is insufficient, do you want to hold or paid again");
														 System.out.println("If hold press 1, else press 2");
														 int c= Integer.parseInt(in.nextLine());
														 if(c==1) {
															 ft.ViewOrderstatus(1);
															 hold=1;
														 }
														 else if(c==2) {
															 hold=-1;
														 }
													 }
													}
													break;
												case 4: method ="AM Bank";
												while(hold==-1) {
													System.out.println("Please enter the amount :");
													 amount = Double.parseDouble(in.nextLine());
													 if(amount-d.getPrice()>=0) {
														 ft.ViewOrderstatus(2);
														 p.setStatus(true);
														 d.setStatus(true);
														 for(Order k :order) {
															 if(p.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														 hold=1;
													 }
													 else {
														 System.out.println("Amount enter is insufficient, do you want to hold or paid again");
														 System.out.println("If hold press 1, else press 2");
														 int c= Integer.parseInt(in.nextLine());
														 if(c==1) {
															 ft.ViewOrderstatus(1);
															 hold=1;
														 }
														 else if(c==2) {
															 hold=-1;
														 }
													 }
													}
													break;
													default:
														System.out.println("Error input, pls retry");
												}
											}
											else {
												control=-1;
											}
											
										}
										
									}
								}
								else {
									System.out.println("----------------*Ms Kiah Bakery*---------------");
								}
									}
						}
						
					}
						
					
				}while(look!=3);
					if(look ==3) {
						ft.ViewOrderstatus(3);
					}
				}
				
			}
			
			if(counter==-1) {
				System.out.println("No Order record found\n");
			}
			if(control==1) {
				System.out.println("This order had already been paid");
			}
			PrintWriter update = new PrintWriter("Cake.txt");
			for(Order o :order)
				
				update.println(o.totostring());
				update.close();
			}
			else if(ch==2) {
				System.out.println("Please enter your name :");
				String name = in.nextLine();
				name = name.toUpperCase();
				System.out.println("Please enter your order ID :");
				String od = in.nextLine();
				od =od.toUpperCase();
				int count=-1;
				for(Order o :order) {
					if(name.contentEquals(o.getName()) && od.contentEquals(o.getId())) {
						count=1;
						
					}
				}
				
				if(count==1) {
					//variables
					int control =0, controller=0;
					//Do while loop
					do {
						System.out.println("----Order Details----");
						System.out.println("1.) Paid"+"\n2.) Unpaid"+"\n3.) Exit"+"\nPlease enter your choice (1-3) :");
						int choice = Integer.parseInt(in.nextLine());
						
						switch(choice) {
						case 1:
							for(Order o :order) {
								if(o.getStatus() && o.getName().contentEquals(name) && o instanceof Orderdetails) {
								o.showinfo();
								controller=1;
							}
							
							}
							if(controller==0) {
								System.out.println("No record found");
							}
							
							break;
						case 2:
							controller=0;
							for(Order o :order) {
								if(!o.getStatus() && o.getName().contentEquals(name) && o instanceof Orderdetails) {
								o.showinfo();
								controller=1;
							}
							
							}
							if(controller==0) {
								System.out.println("No record found");
							}
							else {
								System.out.println("Do you want to paid the order ? if yes press 1, else press 2 :");
								int choices = Integer.parseInt(in.nextLine());
								//if want to pay the order...
								if(choices ==1) {
									System.out.println("Please enter the order ID you want to paid for :");
									String order_Id = in.nextLine();
									for(Order o:order) {
										if(o.getId().contentEquals(order_Id) && o instanceof Orderdetails) {
											o.showinfo();
											System.out.println("1.) Maybank"+"\n2.) Public Bank"+"\n3.) Hong Leong Bank"+"\n4.) Credit Card"+"\n5.) Exit"+"\nPlease select the method to pay :");
											int paymenthod = Integer.parseInt(in.nextLine());
											// Pay method choose
											String method= null; double amount=0;double price =0;int hold=-1;int yes=0;boolean c=false;
											switch(paymenthod) {
											// if choose Maybank
											case 1:
												while(hold==-1) {
													method ="Maybank";
													System.out.println("Please enter the amount to paid for :");
													amount = Double.parseDouble(in.nextLine());
													for(Orderdetails dt :details) {
														if(dt.getId().contentEquals(o.getId()) && dt.getName().contentEquals(o.getName())) {
															price = dt.getPrice();
														}
														
														if(amount-price>=0) {
														
														o.setStatus(true);
														dt.setStatus(true);
														 for(Order k :order) {
															 if(o.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
																 
															 }
														 }
														hold=1;c=true;
														}
														else {
															System.out.println("Amount enter is insufficient, do you want to retry> if yes press 1 else press 2");
															if(yes==1) {
																hold=-1;
															}
															else {
																hold=1;
																c=false;
															}
															
														}
													}
													
												}
												if(hold ==1 && c==true) {
													ft.ViewOrderstatus(2);
												}
												if(hold ==1 && c ==false) {
													ft.ViewOrderstatus(1);
												}
												PrintWriter update = new PrintWriter("Cake.txt");
													for(Order oo :order)
					
													update.println(oo.totostring());
													update.close();
											
												break;
												// if choose Public Bank
											case 2:
												hold=-1; price =0; amount=0; yes =0; c =false;
												while(hold==-1) {
													method ="Public Bank";
													System.out.println("Please enter the amount to paid for :");
													amount = Double.parseDouble(in.nextLine());
													for(Orderdetails dt :details) {
														if(dt.getId().contentEquals(o.getId()) && dt.getName().contentEquals(o.getName())) {
															price = dt.getPrice();
														}
														
														if(amount-price>=0) {
														
														o.setStatus(true);
														dt.setStatus(true);
														 for(Order k :order) {
															 if(o.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														hold=1;c=true;
														}
														else {
															System.out.println("Amount enter is insufficient, do you want to retry> if yes press 1 else press 2");
															if(yes==1) {
																hold=-1;
															}
															else {
																hold=1;
																c=false;
															}
															
														}
													}
													
												}
												if(hold ==1 && c==true) {
													ft.ViewOrderstatus(2);
												}
												if(hold ==1 && c ==false) {
													ft.ViewOrderstatus(1);
												}
											PrintWriter Pupdate = new PrintWriter("Cake.txt");
												for(Order oo :order)
				
												Pupdate.println(oo.totostring());
												Pupdate.close();
												break;
												//if choose Hong Leong bank
											case 3:
												hold=-1; price =0; amount=0; yes =0; c =false;
												while(hold==-1) {
													method ="Hong Leong Bank";
													System.out.println("Please enter the amount to paid for :");
													amount = Double.parseDouble(in.nextLine());
													for(Orderdetails dt :details) {
														if(dt.getId().contentEquals(o.getId()) && dt.getName().contentEquals(o.getName())) {
															price = dt.getPrice();
														}
														
														if(amount-price>=0) {
														
														o.setStatus(true);
														dt.setStatus(true);
														 for(Order k :order) {
															 if(o.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														hold=1;c=true;
														}
														else {
															System.out.println("Amount enter is insufficient, do you want to retry> if yes press 1 else press 2");
															if(yes==1) {
																hold=-1;
																
															}
															else {
																hold=1;
																c=false;
															}
															
														}
													}
													
												}
												if(hold ==1 && c==true) {
													ft.ViewOrderstatus(2);
												}
												if(hold ==1 && c ==false) {
													ft.ViewOrderstatus(1);
												}
											PrintWriter Hupdate = new PrintWriter("Cake.txt");
												for(Order oo :order)
				
												Hupdate.println(oo.totostring());
												Hupdate.close();
												break;
											
											//if choose Credit Card
											case 4:
												hold=-1; price =0; amount=0; yes =0; c =false;
												while(hold==-1) {
													method ="Credit Card";
													System.out.println("Please enter the amount to paid for :");
													amount = Double.parseDouble(in.nextLine());
													for(Orderdetails dt :details) {
														if(dt.getId().contentEquals(o.getId()) && dt.getName().contentEquals(o.getName())) {
															price = dt.getPrice();
														}
														
														if(amount-price>=0) {
														
														o.setStatus(true);
														dt.setStatus(true);
														 for(Order k :order) {
															 if(o.getId().contentEquals(k.getId()) && k instanceof Payment) {
																 k.setType(method);
																 k.setStatus(true);
															 }
														 }
														hold=1;c=true;
														}
														else {
															System.out.println("Amount enter is insufficient, do you want to retry> if yes press 1 else press 2");
															if(yes==1) {
																hold=-1;
																
															}
															else {
																hold=1;
																c=false;
															}
															
														}
													}
													
												}
												if(hold ==1 && c==true) {
													ft.ViewOrderstatus(2);
												}
												if(hold ==1 && c ==false) {
													ft.ViewOrderstatus(1);
												}
											PrintWriter Cupdate = new PrintWriter("Cake.txt");
												for(Order oo :order)
				
												Cupdate.println(oo.totostring());
												Cupdate.close();
												break;
											
											case 5:
												System.out.println("--Exiting--");
												break;
												default:
													System.out.println("Error input, pls retry");
												
											}
										}
									}
									
										
								}
								// if not paying...
								else {
									System.out.println("Order Hold");
									ft.ViewOrderstatus(1);
								}
								
							}
							break;
						case 3:
							control =3;
							ft.ViewOrderstatus(3);
						}
						
						
					}while(control!=3);
				}
				
				
				
				
				
			}
			}
			catch(RuntimeException ex) {
				System.out.println("Error input, pls retry");
			}
		}
		
		

		
}
