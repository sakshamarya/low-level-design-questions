import java.util.HashMap;
import java.util.Map;

public class AmountSplit implements SplitService {
    @Override
    public HashMap<User, Integer> getSplitAmounts(Expense expense) {
        HashMap<User, Integer> finalAmounts = expense.getUsersOwe();

        for(Map.Entry<User, Integer> entry: expense.getUsersPaid().entrySet()){
            if(finalAmounts.containsKey(entry.getKey())){
                finalAmounts.put(entry.getKey(), finalAmounts.get(entry.getKey()) - entry.getValue());
            }
            else{
                finalAmounts.put(entry.getKey(), -entry.getValue());
            }
        }

        return finalAmounts;
    }
}
