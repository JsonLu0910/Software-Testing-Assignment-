package assignment;

import java.util.ArrayList;




public abstract class Order {
private String name;
private String id;
private boolean status=true;
private String type;
Order ft;



public Order(Order ft) {
	this.ft =ft;
}
public Order( String id,String name,boolean status,String type) {
	this.name =name;
	this.id=id;
	this.type=type;
	
	this.status=status;
}

public String getName() {
	return name;
}

public String getId() {
	return id;
}
public String getType() {
	return type;
}

public boolean getStatus() {
	return status;
}

public void setName(String aname) {
	name = aname;
}

public void setId(String aid) {
	id = aid;
}

public void setStatus(boolean astatus) {
	status = astatus;
}

public void setType(String typea) {
	type =typea;
}

public void addtoList(String id, String name, boolean stats, String method) {
	ArrayList<Order>oo = new ArrayList<Order>();
	oo.add(new Payment(id, name, stats, method));
}

public String welcome(boolean check) {
	if(check ==true) {
		return "WELCOME";
	}
	else {
		return "RETRY.....";
	}
}

public String found() {
	return "Item Found";
}

public void confirm() {
	System.out.println("DONE");
}

public String checkState(String state) {
	
	if(state.contentEquals("MELAKA")) {
	pass();	
	return "Available";
		
	}
	else {
		return "UnAvailable";
	}

}

public  boolean validation(String data,int section) {
if(section==1) {
	data= data.toUpperCase();
	char[] charArray = data.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
         char ch = charArray[i];
         if  (!(ch>='A' && ch<='Z') && data.charAt(i) !=' '  || data.charAt(i) ==' ') {
            System.out.println("Name enter can not have space or other numeric letter!!");
        	 return false;
         }
      }
      return true;
}
else if(section==2){
	int len = data.length();
	char[] charArray = data.toCharArray();
      for (int i = 0; i < len; i++) {
         char ch = charArray[i];
         if ((Character.isLetterOrDigit(data.charAt(i)) == false) && data.charAt(i) ==' ') {
        	 System.out.println("Input enter can not have space!!");
             return false;
          }
      }
     
}
else if(section ==3) {
	int len = data.length();
	char[] charArray = data.toCharArray();
      for (int i = 0; i < len; i++) {
         char ch = charArray[i];
         if ((Character.isLetterOrDigit(data.charAt(i)) == false) && data.charAt(i) !=' ' || data.charAt(i) ==' ' ) {
        	 System.out.println("Input enter can not have space or unicode presence!!");
             return false;
          }
      }
}
	
	
	return true;
	
	
}

public boolean check_int(int number, int choice) {
	
	if(choice==1) {
		if(number<0){
			System.out.println("Negative Number is not available");
			throw new NegativeNumberException("Input insert is not available");
			
			
		}
	}
	else if(choice ==2) {
		if(number <100000000 || number>999999999) {
			System.out.println("Input insert is not available");
			throw new NegativeNumberException("Input insert is not available");
			
			
		}
	}
	if ( new NegativeNumberException("Negative Number is not available") != null) {
		return false;
	}
	else {
		return true;
	}
}


public void pass() {
	System.out.println("In Delivery Area");
}

public void ViewOrderstatus(int choice) {
	if(choice ==1) {
		System.out.println("Unpaid, order hold");
	}
	else if(choice ==2) {
		System.out.println("Paid, order ready to deliver");
	}
	else if(choice ==3) {
		System.out.println("----------Exitig----------");
	}
}

public void creaAccDone() {
	System.out.println("Accout succesfully created");
}


public abstract void showinfo();
public abstract String totostring();
public abstract int getPhone();




}
