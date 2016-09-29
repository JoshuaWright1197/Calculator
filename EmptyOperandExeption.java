/**
* Program Name: EmptyOperandExeption.java
* Purpose: To handle empty operand exception's
* Coder: Joshua Wright, 0777530
* Date: July, 2016
*/
public class EmptyOperandExeption extends Exception
{
	// no arg construction
	public EmptyOperandExeption()
	{
		// calling supper construction setting the error message
		super("There must be an Operand");
	}	
}// end class
