import org.junit.Assert;
import org.junit.Test;
import sims.character.Account;

public class AccountTest {

    @Test
    public void testPositiveAdd() {
        Account account = new Account(100);
        account.deposit(50);
        Assert.assertEquals(150.0, account.getBalance(), 0.0001);
    }

    @Test
    public void testNegativeAdd() {
        Account account = new Account(100);
        try {
            account.deposit(-10);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(100.0, account.getBalance(), 0.0001);
        }
    }

    @Test
    public void TestWithdraw() {
        Account account = new Account(100);
        boolean ok = account.withdraw(40);
        Assert.assertTrue(ok);
        Assert.assertEquals(60.0, account.getBalance(), 0.0001);
        Assert.assertEquals(1, account.getNumWithdrawals());
    }

    @Test
    public void TestWithdrawTooMuch() {
        Account account = new Account(100);
        boolean ok = account.withdraw(200);
        Assert.assertFalse(ok);
        Assert.assertEquals(100.0, account.getBalance(), 0.0001);
        Assert.assertEquals(0, account.getNumWithdrawals());
    }

    @Test
    public void TestWithdrawInvalidAmount() {
        Account account = new Account(100);
        try {
            account.withdraw(0);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(100.0, account.getBalance(), 0.0001);
            Assert.assertEquals(0, account.getNumWithdrawals());
        }
    }
}
