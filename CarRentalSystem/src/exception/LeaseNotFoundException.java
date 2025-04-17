package exception;

import util.ProjectConstants;

public class LeaseNotFoundException extends Exception{

	public LeaseNotFoundException() {
		super();
	}
	
	public LeaseNotFoundException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return ProjectConstants.LEASE_NOT_FOUND;
	}
	
	

}
