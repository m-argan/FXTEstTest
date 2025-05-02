package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.CalcModel;
import model.Operation;

class CalcTest
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void test()
	{
		// test toString
		Operation testToString = new Operation(1,"+",2,3);
		assertEquals(testToString.toString(),1.0 + "+"+ 2.0+" = "+ 3.0);
		
		// ** test for add ** //
		CalcModel model = new CalcModel();
		DoubleProperty num1 = new SimpleDoubleProperty(1);
		DoubleProperty num2 = new SimpleDoubleProperty(2);
		DoubleProperty result = new SimpleDoubleProperty(3);
		model.setNum1(num1);
		model.setNum2(num2);
		model.add();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		num1 = new SimpleDoubleProperty(3);
		num2 = new SimpleDoubleProperty(50);
		result = new SimpleDoubleProperty(53);
		model.setNum1(num1);
		model.setNum2(num2);
		model.add();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// ** test for subtract ** //
		// positive result
		num1 = new SimpleDoubleProperty(10);
		num2 = new SimpleDoubleProperty(5);
		result = new SimpleDoubleProperty(5);
		model.setNum1(num1);
		model.setNum2(num2);
		model.subtract();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// negative result
		num1 = new SimpleDoubleProperty(5);
		num2 = new SimpleDoubleProperty(55);
		result = new SimpleDoubleProperty(-50);
		model.setNum1(num1);
		model.setNum2(num2);
		model.subtract();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// ** test for multiply ** //
		num1 = new SimpleDoubleProperty(0);
		num2 = new SimpleDoubleProperty(1);
		result = new SimpleDoubleProperty(0);
		model.setNum1(num1);
		model.setNum2(num2);
		model.multiply();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		num1 = new SimpleDoubleProperty(5);
		num2 = new SimpleDoubleProperty(10);
		result = new SimpleDoubleProperty(50);
		model.setNum1(num1);
		model.setNum2(num2);
		model.multiply();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// ** test for divide ** //
		// whole result
		num1 = new SimpleDoubleProperty(20);
		num2 = new SimpleDoubleProperty(4);
		result = new SimpleDoubleProperty(5);
		model.setNum1(num1);
		model.setNum2(num2);
		model.divide();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// not whole result
		num1 = new SimpleDoubleProperty(6);
		num2 = new SimpleDoubleProperty(5);
		result = new SimpleDoubleProperty(1.2);
		model.setNum1(num1);
		model.setNum2(num2);
		model.divide();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		num1 = new SimpleDoubleProperty(5);
		num2 = new SimpleDoubleProperty(8);
		result = new SimpleDoubleProperty(0.625);
		model.setNum1(num1);
		model.setNum2(num2);
		model.divide();
		assertEquals(model.getResult().getValue(),result.getValue());
		
		// cannot be divided
		num1 = new SimpleDoubleProperty(1);
		num2 = new SimpleDoubleProperty(0);
		//result = new SimpleDoubleProperty("Infinity");
		model.setNum1(num1);
		model.setNum2(num2);
		model.divide();
		assertTrue(model.getResult().getValue().isInfinite());
		
		// 
		
		
	}

}
