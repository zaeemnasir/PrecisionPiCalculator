/**
 * This is a GUI Application that gets user's input for the number of decimal places
 * and the number of Iterations in the program
 *
 * Author: Muhammad Nasir
 * Student ID: 991538047
 *
 */

package application;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MainController {

	// Declaring field variables

	@FXML private TextField _txtPreci; // Represents Precision input from User
	@FXML private TextField _txtIter; // Represents Iteration input from User
	@FXML private Button _btnCalculate;// Represents the Calculate
	@FXML private Label _lblResult; // Represents the Result

	//Calculate Pi Method that prints the result on the Label
	@FXML private void calculatePi() {

		try{//Try block to catch any invalid inputs

			//Declaring all BigDecimal
			BigDecimal finalResult = new BigDecimal(0);// The result (summation of Taylor series)
			BigDecimal oddNum = new BigDecimal(1);// Odd numbers (1, 3, 5, 7 etc.)
			BigDecimal pow5 = new BigDecimal(5);// Odd powers of 5 (5^1, 5^3, 5^5 etc.)
			BigDecimal pow239 = new BigDecimal(239);// Odd powers of 239(239^1, 239^3, 239^5 etc.)
			BigDecimal sign = new BigDecimal(1);// Either 1 or -1 indicating the sign of the next term
			BigDecimal sixteen = new BigDecimal(16); //Represents the number 16
			BigDecimal four = new BigDecimal(4);//Represents the number 4
			BigDecimal two = new BigDecimal(2); //Represents the number 2
			BigDecimal five = new BigDecimal(5);//Represents the number 5
			BigDecimal twoThreeNine = new BigDecimal(239);//Represents the number 239


			//Getting Iteration Value from user and parsing it into an Int
			int iterEntered = Integer.parseInt(_txtIter.getText());
			BigDecimal bigIterVal = BigDecimal.valueOf(iterEntered);

			//Getting Decimal value using Math Context method so we can apply that to the result on the Label
			int preciEntered = Integer.parseInt(_txtPreci.getText());
			MathContext preciVal = new MathContext(preciEntered);



			//For Loop dependent on user's Value
			for (BigDecimal i = BigDecimal.ZERO; i.compareTo(bigIterVal) < 0; i = i.add(BigDecimal.ONE)){



				//represents nextTerm = 16.0/(pow5 * oddNum_-4.0(pow239 * oddNum
				//We are using setScale method to make sure all calculations are done in the preferred decimal number
				BigDecimal nextTerm = sixteen.setScale(preciEntered).divide(
						pow5.setScale(preciEntered).multiply(oddNum.setScale(preciEntered)),RoundingMode.FLOOR)
						.subtract(
								four.setScale(preciEntered).divide((pow239.setScale(preciEntered).multiply(oddNum.setScale(preciEntered))), RoundingMode.FLOOR));




				// this is a big decimal conversation of result += sign*nextTerm
				BigDecimal internResult = (sign.setScale(preciEntered).multiply(nextTerm.setScale(preciEntered)));
				finalResult = finalResult.setScale(preciEntered).setScale(preciEntered).add(internResult.setScale(preciEntered));

				//Update variables for when the loop repeats


				pow5 = (pow5.setScale(preciEntered).multiply(five.setScale(preciEntered))).multiply(five.setScale(preciEntered));
				pow239 = (pow239.setScale(preciEntered).multiply(twoThreeNine.setScale(preciEntered))).multiply(twoThreeNine.setScale(preciEntered));
				oddNum = oddNum.setScale(preciEntered).add(two.setScale(preciEntered));
				sign = sign.negate();//using Negate method to turn the sign into negative



				//Printing the Result on Label. Rounding the answer to the Value user has entered
				_lblResult.setText(String.valueOf(finalResult.round(preciVal)));
			}
		}//End of TryBlock

		catch(NumberFormatException ex){
			//Dialog box to warn user that they can only add number
			Alert alert = new Alert(AlertType.WARNING,"Invalid Input: please use numbers only.");
			// Show the dialog
			Optional<ButtonType> result =alert.showAndWait();
			// End the dialog box if user clicks OK
			if (result.isPresent() && result.get() == ButtonType.OK) {
				System.exit(0);
			}
		}//End of Catch Block

	}//End of calculatePi method

}//End of Main Controller


