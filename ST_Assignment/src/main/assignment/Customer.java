package assignment;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends Order {
	private int phone;
	private String state;
	private String district;
	private String postal_code;
	private String Area;
	private int street_no;
	private int unit;
	
	Order ft;
	public Customer(Order ft) {
		super(ft);
		this.ft =ft;
	}
	private String name;
	private String password;
	public Customer(String id,String name,String password,  int phone, boolean status,String state,String district,String postal_code,String Area,int street_no,int unit) {
		super(id,name, status,password);
		this.password =password;
		this.name =name;
		this.phone = phone;
		this.Area=Area;
		this.district=district;
		this.postal_code=postal_code;
		this.state=state;
		this.street_no=street_no;
		this.unit=unit;
	}

	
	
	@Override
	public void showinfo() {
		System.out.println("ID:"+getId()+"|| Name :"+getName()+"|| Password :"+getType()+"|| Phone :"+getphone());
		
	}

	public int getphone() {
		return phone;
	}
	
	public String getstate() {
		return state;
	}
	
	public String getdistrict() {
		return district;
	}
	
	public String getpostal() {
		return postal_code;
	}
	
	public String getarea() {
		return Area;
	}
	
	public int getstreetno() {
		return street_no;
	}
	
	public int getunit() {
		return unit;
	}
	
	
	@Override
	public String totostring() {
		return getId()+","+getName()+","+getType()+","+getphone()+","+getStatus()+","+getstate()+","+getdistrict()+","+getpostal()+","+getarea()+","+getstreetno()+","+getunit();
		
	}

	@Override
	public int getPhone() {
		// TODO Auto-generated method stub
		return getphone() ;
	}

	
	public boolean checkLogin(String ID, String password, ArrayList<Order>order) {
		// TODO Auto-generated method stub
		int count=-1;
		boolean check =false;
		for(Order o :order) {
			if(ID.contentEquals(o.getId()) && password.contentEquals(o.getType()) && o instanceof Customer) {
				check =true;
				count=1;
				
				break;
			}
		}
		
		if(count==1 && check ==true) {
			check =true;
			System.out.println(ft.welcome(check));
		}
		
		
		return check;
	
	
	}


	public  void CreateAccount(ArrayList<Order>order,ArrayList<Customer>customer,Scanner in,Order ORDER) throws FileNotFoundException {
		//--------Variables-------//
		int stop=0;
		do {
			//User Name Information//
		String username =null, f_name =null, l_name =null, password=null, Name =null;
		int phone_no= 0;
		//User Address Information//
		String state = null, Area =null, District =null, postal_code=null; int Street_no=0, unit=0;
		// Loop variables//
		int counter=-1, k=0, i=0;
		// Array variables//
		String [] user_id= new String [order.size()];
		String [] name= new String [order.size()];
		String [] pw= new String [order.size()];
		int [] Ph = new int [order.size()];
		
		// Insert data into Array//
		for(i =0; i<order.size();i++) {
			Order o = order.get(i);
			
			user_id[i]= o.getId();
			name[i]= o.getName();
			pw[i]= o.getType();
			Ph[i] = o.getPhone();
		
		}
		
		
		//Boolean//
		boolean check =false;
		// Require User Enter Value//
		k=0;
		while(counter==-1) {
			System.out.println("Please enter your username :");
			username = in.nextLine();
			boolean a = ORDER.validation(username, 2);
				k=0;
				while(counter ==-1) {
				if(k <user_id.length) {
				if(username.contentEquals(user_id[k])) {
						System.out.println("This Username has already been taken, pls retry");
						counter=1;
						check = true;
				}
				else {
					counter =-1;
				}
				}
				else if(a==true) {
					check=false;
					counter=1;
				}
				else {
					check= false;
					counter =1;
					
				}
				k++;
			}
			if(check == true) {
				counter=-1;
			}
			else {
				System.out.println("Username available");
				counter=1;
			}
		
		}
		
		// Check Name of the User
		k=0;
		counter=-1;
		check =false;
		while(counter==-1) {
			System.out.println("Please enter your First name :");
			f_name = in.nextLine();
			System.out.println("Please enter your Last name :");
			l_name = in.nextLine();
			boolean b= ORDER.validation(f_name, 1);
			boolean c=ORDER.validation(l_name, 1);
			String s1 = f_name +" "+ l_name;
			Name = s1.toUpperCase();
			System.out.println(Name);
				k=0;
				while(counter ==-1) {
				if(k <name.length) {
				if(Name.contentEquals(name[k])) {
						System.out.println("This Name has already been taken, pls retry");
						counter=1;
						check = true;
				}
				else {
					counter =-1;
				}
				}
				else if(b==true && c==true) {
					check =false;
					counter=1;
				}
				else {
					check= false;
					counter =1;
					
				}
				k++;
			}
			if(check == true) {
				counter=-1;
			}
			else {
				System.out.println("Name available");
				counter=1;
			}
		
		}
		
		// Check Password of the User
			k=0;
			counter=-1;
			check =false;
			while(counter==-1) {
				System.out.println("Please enter your Password :");
				password = in.nextLine();
				boolean d = ORDER.validation(password, 2);
					k=0;
					while(counter ==-1) {
					if(k <pw.length) {
					if(password.contentEquals(pw[k])) {
							System.out.println("This Password has already been taken, pls retry");
							counter=1;
							check = true;
					}
					else {
						counter =-1;
					}
					}
					else if(d==true) {
						check=false;
						counter=1;
					}
					else {
						check= false;
						counter =1;
						
					}
					k++;
				}
				if(check == true) {
					counter=-1;
				}
				else {
					System.out.println("Password available");
					counter=1;
				}
			
			}
			
			// Check Phone No of the User
					k=0;
					counter=-1;
					check =false;
					while(counter==-1) {
						System.out.println("Please enter your Phone No. :");
						phone_no = Integer.parseInt(in.nextLine());
						boolean d = ORDER.check_int(phone_no,2);
						k=0;
							while(counter ==-1) {
							if(k <Ph.length) {
							if(phone_no == Ph[k]) {
									System.out.println("This Phone No. has already been taken, pls retry");
									counter=1;
									check = true;
							}
							else {
								counter =-1;
							}
							}
							else if(d==true) {
								check =false;
								counter=1;
							}
							else {
								check= false;
								counter =1;
								
							}
							k++;
						}
						if(check == true) {
							counter=-1;
						}
						else {
							System.out.println("Phone No. available");
							counter=1;
						}
					
					}
					// Address Information//
					check =false;
					counter=-1;
					while(counter==-1) {
						System.out.println("Please Enter Your Address"+"\n State :");
						String s1 = in.nextLine();
						state = s1.toUpperCase();
						boolean e = ORDER.validation(state, 1);
						if(state.contentEquals("MELAKA")) {
							System.out.println("Please enter District :");
							District = in.nextLine();
							System.out.println("Please enter unit :");
							unit = Integer.parseInt(in.nextLine());
							System.out.println("Please enter Stree_no :");
							Street_no = Integer.parseInt(in.nextLine());
							System.out.println("Please enter Postal Code :");
							postal_code = in.nextLine();
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
								
								break;
							case 2 : 
								Area ="Asahan";
								
								break;
							case 3: 
								Area ="Ayer Keroh";
								
								break;
							case 4 : 
								Area ="Batu Berendam";
								
								break;
							case 5 : 
								Area ="Bemban";
								
								break;
							case 6 : 
								Area ="Bukit Beruang";
								
								break;
							case 7 : 
								Area ="Durian Tunggal";
								
								break;
							case 8 : 
								Area ="Jasin";
								
								break;
							case 9 : 
								Area ="Kuala Linggi";
								
								break;
							case 10 : 
								Area ="Kuala Sungai Baru";
								
								break;
							case 11 : 
								Area ="Lubok China";
								
								break;
							case 12 : 
								Area ="Masjid Tanah";
								
								break;
							case 13 : 
								Area ="Melaka Tengah";
								
								break;
							case 14 : 
								Area ="Merlimau";
								
								break;
							case 15 : 
								Area ="Selandar";
								
								break;
							case 16 : 
								Area ="Sungai Rambai";
								
								break;
							case 17 : 
								Area ="Sungai Udang";
								
								break;
							case 18: 
								Area ="Tanjong Kling";
								
								break;
							case 19 : 
								Area ="Ujong Pasir";
								
								break;
								default:
								System.out.println("Error input, pls retry");
							}
							
							
							counter=1;
							check =true;
							counter=1;
						}
						else if(e ==false) {
							check =true;
							counter=-1;
						}
						else {
							check=false;
							counter=-1;
							System.out.println("State which is not in Melaka is not able to deliver, pls retry...");					
						}
					}
					
					if(check ==true) {
						System.out.println("Press 1 to confirmation, 2 to cancel");
						int confirmation = Integer.parseInt(in.nextLine());
						if(confirmation ==1) {
							order.add(new Customer(username,Name,password,phone_no,true,state,District,postal_code,Area,Street_no,unit));
							ft.creaAccDone();
							PrintWriter newacc = new PrintWriter("Cake.txt");
							
							for(Order b : order)
							newacc.println(b.totostring());
							newacc.close();
						}
						else {
							System.out.println("Account cancel");
							
							
						}
					}
					System.out.println("Is there any more account to create? (If yess press 1, else press 2)");
					stop = Integer.parseInt(in.nextLine());

					
		}while(stop!=2);
		}

		

	
	
}
