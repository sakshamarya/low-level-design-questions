import java.util.HashMap;

public class SplitFactory {

    private static HashMap<ExpenseDistributionMethod, SplitService> splitServices = new HashMap<ExpenseDistributionMethod, SplitService>() {{
        put(ExpenseDistributionMethod.BY_PERCENTAGE, new PercentSplit());
        put(ExpenseDistributionMethod.BY_AMOUNT, new AmountSplit());
    }};

    public static SplitService getSplitService(ExpenseDistributionMethod method){
        return splitServices.get(method);
    }
}
