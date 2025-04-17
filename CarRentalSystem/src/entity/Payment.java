package entity;

import java.util.Date;

public class Payment {
	
	private int paymentID;
    private Lease lease; 
    private Date paymentDate;
    private double amount;
    
	public Payment() {
		super();
	}
	
	public Payment(int paymentID, Lease lease, Date paymentDate, double amount) {
        this.paymentID = paymentID;
        this.lease = lease;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

	public int getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}

	public Lease getLease() {
		return lease;
	}

	public void setLease(Lease lease) {
		this.lease = lease;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [paymentID=" + paymentID + ", lease=" + lease + ", paymentDate=" + paymentDate + ", amount="
				+ amount + "]";
	}
    
}
