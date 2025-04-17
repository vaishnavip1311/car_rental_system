package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.ICarLeaseRepositoryImpl;
import entity.Lease;

public class LeaseRetrievalTest {
	
    private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() {
        repo = new ICarLeaseRepositoryImpl();
    }

    @Test
    public void testLeaseRetrievedSuccessfully1() {
        int leaseIdToCheck = 6; 

        List<Lease> leases = repo.listLeaseHistory();
        boolean found = false;

        for (Lease l : leases) {
            if (l.getLeaseID() == leaseIdToCheck) {
                found = true;
                System.out.println("Lease retrieved successfully: " + l);
                break;
            }
        }

        assertTrue("Lease with ID " + leaseIdToCheck + " should exist", found);
    }
    

}
