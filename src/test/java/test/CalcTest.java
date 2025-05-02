package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		Operation add = new Operation(1,"+",2,3);
		assertEquals(add.toString(),1.0 + "+"+ 2.0+" = "+ 3.0);
	}

}
