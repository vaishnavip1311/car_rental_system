package entity;

import java.time.LocalDate;

public class Lease {
	
	private int leaseID;
    private Car car; 
    private Customer customer; 
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    
	public Lease() {
		super();
	} 
    
	public Lease(int leaseID, Car car, Customer customer, LocalDate startDate, LocalDate endDate, String type) {
        this.leaseID = leaseID;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

	public int getLeaseID() {
		return leaseID;
	}

	public void setLeaseID(int leaseID) {
		this.leaseID = leaseID;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Lease [leaseID=" + leaseID + ", car=" + car + ", customer=" + customer + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", type=" + type + "]";
	}
	
	

    
}
