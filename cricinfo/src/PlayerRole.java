public enum PlayerRole {
    RBATSMAN("RHB", "Right Handed Batsman"),
    LBATSMAN("LHB", "Left Handed Batsman"),
    ALLROUNDER("AR", "All Rounder"),
    WK("WK", "Wicket Keeper"),
    RBOWLER("RBWL", "Right Handed Bowler"),
    LBOWLER("LBWL", "Left Handed Bowler");

    private String abbr;
    private String description;

    PlayerRole(String abbr, String description) {
        this.abbr = abbr;
        this.description = description;
    }
    public String getAbbr() {
        return abbr;
    }
    public String getDescription() {
        return description;
    }
}
