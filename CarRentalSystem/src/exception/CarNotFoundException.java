package exception;

import util.ProjectConstants;

public class CarNotFoundException extends Exception{
	
	public CarNotFoundException() {
		super();
	}

	public CarNotFoundException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return ProjectConstants.CAR_NOT_FOUND;
	}
	
}
