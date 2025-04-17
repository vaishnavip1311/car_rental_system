package exception;

import util.ProjectConstants;

public class CustomerNotFoundException extends Exception{
	
	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return ProjectConstants.CUSTOMER_NOT_FOUND;
	}
	
}
