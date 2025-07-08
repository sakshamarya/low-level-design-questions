import java.util.HashMap;
import java.util.Map;

public class PercentSplit implements SplitService{
    @Override
    public HashMap<User, Integer> getSplitAmounts(Expense expense) {
        HashMap<User, Integer> finalAmounts = new HashMap<>();
        int amount = expense.getAmount();
        int distributedAmountSum = 0;

        for(Map.Entry<User, Integer> entry: expense.getUsersOwe().entrySet()){
            if(entry.equals(expense.getUsersOwe().entrySet().toArray()[expense.getUsersOwe().size() - 1])){
                finalAmounts.put(entry.getKey(), amount - distributedAmountSum);
            } else {
                int splitAmount = (int) Math.round((entry.getValue() * amount) / 100.0);
                finalAmounts.put(entry.getKey(), splitAmount);
                distributedAmountSum += splitAmount;
            }
        }

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
