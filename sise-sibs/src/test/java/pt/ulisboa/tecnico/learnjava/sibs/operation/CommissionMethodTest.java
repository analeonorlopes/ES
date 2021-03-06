package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.PaymentOperation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.domain.TransferOperation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.transferOperationData;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class CommissionMethodTest {
	private static final String SOURCE_IBAN = "SourceIban";
	private static final String TARGET_IBAN = "TargetIban";
	private Sibs sibs;

	@Before
	public void setUp() {
		Services services = new Services();
		sibs = new Sibs(100, services);
	}

	@Test
	public void transferOperation1000() throws OperationException {
		transferOperationData data = new transferOperationData(new Services(), SOURCE_IBAN, TARGET_IBAN, 1000);
		Operation operation = new TransferOperation(sibs, data);

		assertEquals(51, operation.commission());
	}

	@Test
	public void transferOperation15() throws OperationException {
		transferOperationData data = new transferOperationData(new Services(), SOURCE_IBAN, TARGET_IBAN, 15);
		Operation operation = new TransferOperation(sibs, data);

		assertEquals(2, operation.commission());
	}

	@Test
	public void transferOperation1() throws OperationException {
		transferOperationData data = new transferOperationData(new Services(), SOURCE_IBAN, TARGET_IBAN, 1);
		Operation operation = new TransferOperation(sibs, data);

		assertEquals(1, operation.commission());
	}

	@Test
	public void paymentOperation1000() throws OperationException {
		Operation operation = new PaymentOperation(TARGET_IBAN, 1000);

		assertEquals(101, operation.commission());
	}

	@Test
	public void paymentOperation15() throws OperationException {
		Operation operation = new PaymentOperation(TARGET_IBAN, 15);

		assertEquals(3, operation.commission());
	}

	@Test
	public void paymentOperation1() throws OperationException {
		Operation operation = new PaymentOperation(TARGET_IBAN, 1);

		assertEquals(1, operation.commission());
	}

	@After
	public void tearDown() {
		sibs = null;
	}

}
