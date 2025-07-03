import java.util.Arrays;

public enum BallEvent {

    /*EXTRAS*/
    WIDE("W", "Wide Ball", 1, false),
    NO_BALL("NB", "No Ball", 1, false),
    BYE_ONE_RUN("B", "Bye", 1, true),
    BYE_TWO_RUNS("B", "Bye", 2, true),
    BYE_THREE_RUNS("B", "Bye", 3, true),
    BYE_FOUR_RUNS("B", "Bye", 4, true),
    OVER_THROW_ONE_RUN("OTW", "Overthrow", 1, false),
    OVER_THROW_TWO_RUNS("OTW", "Overthrow", 2, false),
    OVER_THROW_THREE_RUNS("OTW", "Overthrow", 3, false),
    OVER_THROW_FOUR_RUNS("OTW", "Overthrow", 4, false),
    LEG_BYE_ONE_RUN("LB", "Leg Bye", 1, true),
    LEG_BYE_TWO_RUNS("LB", "Leg Bye", 2, true),
    LEG_BYE_THREE_RUNS("LB", "Leg Bye", 3, true),
    LEG_BYE_FOUR_RUNS("LB", "Leg Bye", 4, true),

    /*NON EXTRAS*/
    ZERO("DOT", "Dot Ball", 0, true),
    ONE("1", "One Run", 1, true),
    TWO("2", "Two Runs", 2, true),
    THREE("3", "Three Runs", 3, true),
    FOUR("4", "Four Runs", 4, true),
    SIX("6", "Six Runs", 6, true),

    /*OUT*/
    BOWLED("W", "Wicket (Bowled)", 0, true),
    CAUGHT_BEHIND("W", "Wicket (Caught Behind)", 0, true),
    CAUGHT("W", "Wicket (Caught)", 0, true),
    HIT_WICKET("W", "Wicket (Hit Wicket)", 0, true);



    private String typeAbbr;
    private String type;
    private int runs;
    private boolean isBallCounted;

    BallEvent(String typeAbbr, String type, int runs, boolean isBallCounted) {
        this.typeAbbr = typeAbbr;
        this.type = type;
        this.runs = runs;
        this.isBallCounted = isBallCounted;
    }
    public String getTypeAbbr() {
        return typeAbbr;
    }
    public String getType() {
        return type;
    }
    public int getRuns() {
        return runs;
    }
    public boolean isBallCounted() {
        return isBallCounted;
    }

    public boolean isExtras(){
        return Arrays.asList(WIDE, NO_BALL, BYE_ONE_RUN, BYE_TWO_RUNS, BYE_THREE_RUNS, BYE_FOUR_RUNS,
                       OVER_THROW_ONE_RUN, OVER_THROW_TWO_RUNS, OVER_THROW_THREE_RUNS, OVER_THROW_FOUR_RUNS,
                       LEG_BYE_ONE_RUN, LEG_BYE_TWO_RUNS, LEG_BYE_THREE_RUNS, LEG_BYE_FOUR_RUNS).contains(this);
    }

    public boolean isBatsmanFour(){
        return this == FOUR;
    }

    public boolean isBatsmanSix(){
        return this == SIX;
    }

    public  boolean isBatsmanOut(){
        return Arrays.asList(BOWLED, CAUGHT_BEHIND, CAUGHT, HIT_WICKET).contains(this);
    }

}
