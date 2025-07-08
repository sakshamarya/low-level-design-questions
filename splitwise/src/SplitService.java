import java.util.HashMap;

public interface SplitService {
    public HashMap<User, Integer> getSplitAmounts(Expense expense);
}
