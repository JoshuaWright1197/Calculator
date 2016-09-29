import java.util.ArrayList;
/**
* Program Name: Calculator.java
* Purpose: To create Calculator objects in memory
* Coder: Joshua Wright, 0777530
* Date: July, 2016
*/
public class Calculator
{
	// class scope variable decoration
	private String operand;
	private String operator;
	private boolean decimalPressed;
	private ArrayList<String> list;
	
	// no arg constructor 
	public Calculator()
	{
		//initializing variables 
		operand = "";
		operator = "";
		decimalPressed = false;
		list = new ArrayList<String>();
	}// end constructor 

	/**
	* Method Name:  getOperand
	* Purpose: allow access to Operand
	* Parameters: none
	* Returns: String
	*/
	public String getOperand()
	{
		return operand;
	}// end method

	/**
	* Method Name: setOperand
	* Purpose: set's Operand
	* Parameters: String
	* Returns: void
	*/
	public void setOperand(String operand)throws LongOperandException
	{
		//testing to see if the string provided is within requirements 
		if(operand.length() > 21)
			throw new LongOperandException();
	
		//creating a temporary char array 
		char[] test = operand.toCharArray();
		
		// loop cycling though temp array
		for(int i = 0 ; i < test.length ; i++)
		{
			// testing for a decimal point
			if(test[i] == '.')
				setDecimalPressed(true);
			
			// testing for negative
			else if(test[i] == '-')
				GUICalculator.negative = true;	
			
		}// end for
		
		// setting the operand
		this.operand = operand;
	}// end method

	/**
	* Method Name:  getOperator
	* Purpose: allow access to Operator
	* Parameters: none
	* Returns: String
	*/
	public String getOperator()
	{
		return operator;
	}// end method

	/**
	* Method Name: setOperator
	* Purpose: set's Operator
	* Parameters: String
	* Returns: void
	*/
	public void setOperator(String operator)
	{
		this.operator = operator;
	}// end method

	/**
	* Method Name: isDecimalPressed
	* Purpose: allows access to DecimalPressed
	* Parameters: none
	* Returns: boolean
	*/
	public boolean isDecimalPressed()
	{
		return decimalPressed;
	}// end method
	
	/**
	* Method Name: setDecimalPressed
	* Purpose: set's DecimalPressed
	* Parameters: boolean
	* Returns: void
	*/
	public void setDecimalPressed(boolean decimalPressed)
	{
		this.decimalPressed = decimalPressed;
	}// end method 
	
	/**
	* Method Name: Clear
	* Purpose: re-set all fields to their default values
	* Parameters: none
	* Returns: void
	*/
	public void Clear()
	{
		operand = "";
		operator = "";
		decimalPressed = false;
		list.clear();
	}// end method
	
	/**
	* Method Name: backspace
	* Purpose: Remove the last number or decimal clicked, if the display has a value
	* Parameters: string
	* Returns: String
	*/
	public String backspace(String value)throws EmptyOperandExeption
	{
		// testing if the operand or value is empty
		if(value.isEmpty() || operand.isEmpty())
			throw new EmptyOperandExeption();
		else
		{
			//testing for a decimal point and reseting decimal pressed field if true
			if(value.charAt(value.length() -1) == '.')
				setDecimalPressed(false);
				
			// removing the last char off the string
			operand  = operand.substring(0,operand.length()-1);	
			
			// removing the last char off the value
			return value.substring(0,value.length()-1);	
		}
	}// end method
	
	/**
	* Method Name: findPercentage
	* Purpose: Display the current value as a percentage in decimal format
	* Parameters: string
	* Returns: String
	*/
	public String findPercentage(String value) throws EmptyOperandExeption
	{
		// testing if the operand or value is empty
		if(value.isEmpty() || operand.isEmpty())
			throw new EmptyOperandExeption();
		else
		{
			// using double parse on operand and value to perform calculator's
			operand  = ""+(Double.parseDouble(operand) /100);	
			return ""+(Double.parseDouble(value) /100);	
		}
	}// end method
	
	/**
	* Method Name: togglePlusMinus
	* Purpose: Toggle between making a number negative or positive
	* Parameters: Boolean
	* Returns: String
	*/
	public String togglePlusMinus(Boolean flag) throws EmptyOperandExeption
	{
		// testing if the operand is empty
		if(operand.isEmpty())
			throw new EmptyOperandExeption();
		else
		{
			// creating a temporary string
			String temp ="";
			
			if(flag  == true)
			{
				// add - to the beginning to the string
				temp = "-"+operand;
			}
			else
			{
				// removes - from the beginning of the string
				temp = operand.substring(1);
			}
			
			//resetting operand to temporary string
			operand  = temp;
			return operand;
		}
	}// end method
	
	/**
	* Method Name: findSquared
	* Purpose: Display the current value as squared
	* Parameters: String
	* Returns: String
	*/
	public String findSquared(String value)
	{
		//using double parse to perform calculations 
		return  ""+Double.parseDouble(value)* Double.parseDouble(value);
	}// end method
	
	/**
	* Method Name: findSquared
	* Purpose: Display the current value as square root
	* Parameters: String
	* Returns: String
	*/
	public String findSquareRoot(String value) throws InvalidOperandExeption
	{
		// testing if the value passed to greater than zero
		if(Double.parseDouble(value) < 0)
			throw new InvalidOperandExeption();
		
		//using double parse to perform calculations 
		return  ""+Math.sqrt(Double.parseDouble(value));	
	}// end method
	
	/**
	* Method Name: buildOperand
	* Purpose: Concatenate numbers as clicked to the current operand
	* Parameters: String
	* Returns: String
	*/
	public String buildOperand(String value) throws LongOperandException
	{
		//testing to see if the string provided is within requirements 
		if(operand.length() > 21)
			throw new LongOperandException();
	
		//Concatenate operand with value
		operand+=value;
		return operand;
	}// end method
	
	/**
	* Method Name:  buildExpression
	* Purpose: add operand and operator to the list
	* Parameters: String
	* Returns: void
	*/
	public void buildExpression(String value) throws EmptyOperandExeption
	{
		// setting operator
		operator = value;
		
		// testing if the operand is empty
		if(operand.isEmpty())
			throw new EmptyOperandExeption();
		else
		{
			//adding operand and operator to list
			list.add(operand);
			list.add(operator);
			
			//resetting all field except list
			operand = "";
			operator = "";
			decimalPressed = false;
		}
	}// end method
	
	/**
	* Method Name: calculate
	* Purpose: finds an answer applying BEDMAS
	* Parameters: none
	* Returns: double
	*/
	public double calculate() throws EmptyOperandExeption,InvalidOperandExeption
	{
		// testing if the operand is empty
		if(operand.isEmpty())
			throw new EmptyOperandExeption();
		else
		{
			// adding last operand to list
			list.add(operand);
			// creating a double
			double finalResult = 0;
			
			//testing if there is a / in the list
			if(list.indexOf("/") != -1)
			{
				// loop going though list
				for(int i = 0;i < list.size();i++)
				{
					// creating a temporary variable
					double result= 0;
					
					//testing for multiple /
					if(list.get(i)== "/")
					{
						// testing for a divide by zero exception
						if(list.get(i+1).equals("0"))
							throw new InvalidOperandExeption();
						else
						{
							// using double parse to perform calculations on the element before the operator 
							//and after
							result = Double.parseDouble(list.get(i-1)) / Double.parseDouble(list.get(i+1));
							
							//setting the position the operator to result
							list.set(i,""+result);
							list.remove(i+1);
							list.remove(i-1);
							
							// resetting i to prevent a ArrayOutOfBound Exception
							i = 0;
							
						}// if
					}// end loop
				}// end inner if
			}// end if
			
			//testing if there is a x in the list
			if(list.indexOf("x") != -1)
			{
				// loop going though list
				for(int i = 0;i < list.size();i++)
				{
					// creating a temporary variable
					double result= 0;
	
					// testing for multiple x
					if(list.get(i)== "x")
					{
						// using double parse to perform calculations on the element before the operator 
						//and after
						result = Double.parseDouble(list.get(i-1)) * Double.parseDouble(list.get(i+1));
						list.set(i,""+result);
						list.remove(i+1);
						list.remove(i-1);
						
						// resetting i to prevent a ArrayOutOfBound Exception
						i = 0;
						
					}// end if
				}// end for
			}// end if
			
			//testing if there is a + in the list
			if(list.indexOf("+") != -1)
			{
				// loop going though list
				for(int i = 0;i < list.size();i++)
				{
					// creating a temporary variable
					double result= 0;
					
					// testing for multiple +
					if(list.get(i)== "+")
					{
						// using double parse to perform calculations on the element before the operator 
						//and after
						result = Double.parseDouble(list.get(i-1)) + Double.parseDouble(list.get(i+1));
						list.set(i,""+result);
						list.remove(i+1);
						list.remove(i-1);
						
						// resetting i to prevent a ArrayOutOfBound Exception
						i = 0;
						
					}// end if
				}// end for
			}// end if
			
			//testing if there is a - in the list
			if(list.indexOf("-") != -1)
			{
				// loop going though list
				for(int i = 0;i < list.size();i++)
				{
					// creating a temporary variable
					double result= 0;
					
					if(list.get(i)== "-")
					{
						// using double parse to perform calculations on the element before the operator 
						//and after
						result = Double.parseDouble(list.get(i-1)) - Double.parseDouble(list.get(i+1));
						list.set(i,""+result);
						list.remove(i+1);
						list.remove(i-1);
						
						// resetting i to prevent a ArrayOutOfBound Exception
						i = 0;
						
					}// end if
				}// end for
			}// end if
			
			// using double parse string to get the only element in the list
			finalResult = Double.parseDouble(list.get(0));
			
			//calling clear to reset all fields
			list.clear();
			
			return finalResult;
		}//end if
	}// end method
	
	/**
	* Method Name: convertHex
	* Purpose: Convert the current display to its hexadecimal equivalent
	* Parameters: string
	* Returns: string
	*/
	public String convertHex(String value) throws EmptyOperandExeption
	{
		// testing if the value is empty
		if(value.isEmpty())
			throw new EmptyOperandExeption();
		else if(Double.parseDouble(value) < 1)
			return "0";
		else
		{
			// creating a string and and integer to hold value after double parse
			String result = "";
			int num = (int)Double.parseDouble(value);
			
			// temp variable
			int temp = 0;
			
			// char array holding decimal 10 -15 hex equivalent
			char hex[] = {'A','B','C','D','E','F'};
	
				//loop while num != 0
				while(num != 0)
				{
					
					temp = (int) (num % 16);
					
					// testing if hex array is required
					if(temp > 9)
					{
						//loop traversing hex array
						for(int  i = 0 ; i < hex.length; i++)
						{
							// testing for a match
							if((char)temp == (i+10))
							{
								result = hex[i]+result;
							}
						}
					}
					else
					{
						result = temp+result;
					}
					// resetting num;
					num = (int)num / 16;
				}
			return result;
		}
		
	}
	
	/**
	* Method Name: convertOct
	* Purpose: Convert the current display to its octal equivalent
	* Parameters: string
	* Returns: string
	*/
	public String convertOct(String value) throws EmptyOperandExeption, InvalidOperandExeption
	{
		// create a temporary char array
		char[] test = value.toCharArray();
		
		//loop testing test for a letter
		for(int i = 0 ; i < test.length; i++)
		{
			if(Character.isLetter(test[i]))
			throw new InvalidOperandExeption();
		}
		
		// testing if the value is empty
		if(value.isEmpty())
			throw new EmptyOperandExeption();
		else if( Double.parseDouble(value) < 1)
			return "0";
		else
		{
			// creating a string and and integer to hold value after double parse
			String result = "";
			int num = (int)Double.parseDouble(value);
			
			//loop while num != 0
			while(num != 0)
			{
				//result concatenation holding the modulus of num and 8
				result = (int)(num % 8) +result;
				// resetting num;
				num = (int)num / 8;
			}
			return result;	
			
		}// end if
	}// end method
	
	/**
	* Method Name:  convertBin
	* Purpose: Convert the current display to its binary equivalent
	* Parameters: string
	* Returns: string
	*/
	public String convertBin(String value) throws EmptyOperandExeption, InvalidOperandExeption
	{
		// create a temporary char array
		char[] test = value.toCharArray();
		
		//loop testing test for a letter
		for(int i = 0 ; i < test.length; i++)
		{
			if(Character.isLetter(test[i]))
			throw new InvalidOperandExeption();
		}
		
		// testing if the value is empty
		if(value.isEmpty())
			throw new EmptyOperandExeption();
		else if( Double.parseDouble(value) < 1)
			return "0";
		else
		{
			// creating a string and and integer to hold value after double parse
			int temp = (int)Double.parseDouble(value);
			String result = "";
			
			//loop while num != 0
			while(temp != 0)
			{
				// testing if value has a remainder
				if(temp % 2 == 0)
				{
					result = "0"+result;
				}
				else
				{
					result = "1"+result;
				}
				// resetting temp;
				temp = (int)(temp/2);
			}
			return result;
			
		}// end if
	}// end method
	
	/**
	* Method Name: toString
	* Purpose: returns a string representation of the object
	* Parameters: void
	* Returns: string
	*/
	public String toString()
	{
		return "operaotr : " + operator+"\n"+"operand : "+operand+"\n"+"list : "+list.toString()
		+"\ndecimal pressed : "+decimalPressed;
	}// end method
	
	
}// end of class
