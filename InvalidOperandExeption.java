/**
* Program Name:InvalidOperandExeption.java
* Purpose: To handle invalid operand exception's
* Coder: Joshua Wright, 0777530
* Date: July, 2016
*/
public class InvalidOperandExeption extends Exception
{
	// no arg construction
	public InvalidOperandExeption()
	{
		// calling supper construction setting the error message
		super("Invalid Operand");
	}
}// end class
