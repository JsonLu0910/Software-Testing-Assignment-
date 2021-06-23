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

public class Test_Validation {

	private Object[] validation_data() {
		
		return new Object[] {
			new Object[] {"jason ",1,false},
			new Object[] {" lu",1,false},
			new Object[] {"jason @!",2,false},
			new Object[] {"jason@!",3,false},
			new Object[] {"jason",2,true},
			};
		}

	@Test
	@Parameters(method="validation_data")
	public void test_validation(String data, int choice, boolean expected)  {
		Order o = mock(Order.class);
		Order od = new Customer(o);
		when(o.validation(data, choice)).thenReturn(expected);	
		boolean actual_result = od.validation(data, choice);
		System.out.println(actual_result);
		assertEquals(expected, actual_result);
	}

	private Object[] Int_data() {
		
		return new Object[] {
			new Object[] {-12,1,false},
			new Object[] {-1234,1,true},
			new Object[] {999,2,true},
			new Object[] {1,2,true},
			new Object[] {2,2,true},
			
			};
		}

	@Test(expected=NegativeNumberException.class)
	@Parameters(method="Int_data")
	public void test_Int_input(int data, int choice, boolean expected)  {
		Order o = mock(Order.class);
		Order od = new Customer(o);
		when(o.check_int(data, choice)).thenReturn(expected);	
		// the first set of data is not allow negative number 
		// the second set of data is not allow number smaller than 10000000
		// the third set of data is not allow number bigger than 99999999
		//all the data is invalid, so the actual result should be false
		boolean actual_result = od.check_int(data, choice);
		assertEquals(expected, actual_result);
	}


}
