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

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

@RunWith(JUnitParamsRunner.class)

public class STFinal {

private Object[] getWords() {
		
		return new Object[] {
			new Object[] {"hi","short"},
			new Object[] {"happy","average"},
			new Object[] {"happydayss","long"},
			new Object[] {"h","short"},
			new Object[] {"hey","short"},
			new Object[] {"halo","average"},
			new Object[] {"hahaHAHA","average"},
			new Object[] {"HAPPYDAYS","long"},
			new Object[] {"HAPPYDAYday","long"}
		};
	}
	
	
@Test
@Parameters(method="getWords")
public void paramtest_Combintest(String words,String expected) {
	StringLength sl = new StringLength();
	sl.setStringCategory(words);
	
	assertEquals(expected, sl.getCategory());
}

private Object[] getWords2() {
	
	return new Object[] {
		new Object[] {"","short"},
		new Object[] {"happydayssssssssss","long"},
		
	};
}


@Test(expected=IllegalArgumentException.class)
@Parameters(method="getWords2")
public void InvalidTest(String words, String expected) {
	StringLength sl = new StringLength();
	sl.setStringCategory(words);
	
	assertEquals(expected, sl.getCategory());
}

}
