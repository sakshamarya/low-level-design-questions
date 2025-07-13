import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Expense {
    private AtomicLong expenseId = new AtomicLong(0);
    private String expenseName;
    private Timestamp expenseTs;
    private int amount;
    private HashMap<User, Integer> usersPaid;
    private HashMap<User, Integer> usersOwe;
    private ExpenseDistributionMethod expenseDistributionMethod;

    private int getAmountFromHashmap(HashMap<User, Integer> users) {
        int sum = 0;
        for(Map.Entry<User, Integer> entry : users.entrySet()){
            sum += entry.getValue();
        }
        return sum;
    }

    public Expense(String expenseName, int amount,
                   HashMap<User, Integer> usersPaid,
                   HashMap<User, Integer> usersOwe,
                   ExpenseDistributionMethod expenseDistributionMethod) {

        if(expenseDistributionMethod.equals(ExpenseDistributionMethod.BY_AMOUNT) && getAmountFromHashmap(usersPaid) != amount && getAmountFromHashmap(usersPaid)!=getAmountFromHashmap(usersOwe)) {
            System.out.println("Cannot create expense: Total amount paid by users does not match the expense amount.");
            return;
        }
        this.expenseId = new AtomicLong(expenseId.incrementAndGet());
        this.expenseName = expenseName;
        this.amount = amount;
        this.usersPaid = usersPaid;
        this.usersOwe = usersOwe;
        this.expenseTs = new Timestamp(System.currentTimeMillis());
        this.expenseDistributionMethod = expenseDistributionMethod;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public int getAmount() {
        return amount;
    }

    public HashMap<User, Integer> getUsersPaid() {
        return new HashMap<>(this.usersPaid);
    }

    public HashMap<User, Integer> getUsersOwe() {
        return new HashMap<>(this.usersOwe);
    }

    public ExpenseDistributionMethod getExpenseDistributionMethod() {
        return expenseDistributionMethod;
    }
}
