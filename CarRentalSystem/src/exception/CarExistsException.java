package exception;

import util.ProjectConstants;

public class CarExistsException extends Exception{

	public CarExistsException() {
		super();
	}
	
	public CarExistsException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return ProjectConstants.CAR_EXISTS;
	}
	
	

}
