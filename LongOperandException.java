/**
* Program Name: LongOperandException.java
* Purpose: To handle long operand exception's
* Coder: Joshua Wright, 0777530
* Date: July, 2016
*/
public class LongOperandException extends Exception
{
	// no arg construction
	public LongOperandException()
	{
		// calling supper construction setting the error message
		super("Operand is too large");
	}
}// end class
