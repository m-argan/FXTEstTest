package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CalcModel;
import model.Operation;
import view.MainController;

@ExtendWith(ApplicationExtension.class)
class CalcTest
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	void testModel()
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
		model.setNum1(num1);
		model.setNum2(num2);
		model.divide();
		assertTrue(model.getResult().getValue().isInfinite());
	}
	
	
	@Start  //Before
	  private void start(Stage stage)
	  {
		FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(MainController.class.getResource("main.fxml"));
	    BorderPane view = null;
		try
		{
			view = loader.load();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	    MainController cont = loader.getController();
	    CalcModel model =new CalcModel(); 
	    cont.setModel(model);
	    
	    Scene s = new Scene(view);
	    stage.setScene(s);
	    stage.show();
	  
	  }
	
	private void enterAmt(FxRobot robot, String num1, String num2)
	{
		robot.clickOn("#NumOneTF");
		robot.write(num1);
		robot.clickOn("#NumTwoTF");
		robot.write(num2);
	}
	
	private void checkAns(FxRobot robot,String ans)
	  {
	    Assertions.assertThat(robot.lookup("#ResultLabel")
	        .queryAs(Label.class)).hasText(ans);    
	  }
	
	@Test
	  public void testCalculations(FxRobot robot)
	  {
		// check add
		enterAmt(robot, "5", "10");
		robot.clickOn("#addButton");
		checkAns(robot, "15");
		
		// check subtract
		enterAmt(robot, "5", "10");
		robot.clickOn("#subButton");
		checkAns(robot, "-5");
	    
		// check multiply
		enterAmt(robot, "10", "2");
		robot.clickOn("#multButton");
		checkAns(robot, "20");
	    
		// check divide
		enterAmt(robot, "5", "10");
		robot.clickOn("#divButton");
		checkAns(robot, "0.5");
		
		// check divide again
		enterAmt(robot, "10", "5");
		robot.clickOn("#divButton");
		checkAns(robot, "2");
	    
	    
	  }

}
