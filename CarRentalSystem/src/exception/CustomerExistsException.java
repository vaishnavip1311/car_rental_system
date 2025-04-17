package exception;

import util.ProjectConstants;

public class CustomerExistsException extends Exception{

	public CustomerExistsException() {
		super();
	}
	
	public CustomerExistsException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return ProjectConstants.CUSTOMER_EXISTS;
	}
	
	

}
