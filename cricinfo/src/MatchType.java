public enum MatchType {

    TEST("Test", "Test Match", Integer.MAX_VALUE),
    ODI("ODI", "One Day International", 50),
    T20("T20", "Twenty20", 20);

    private String typeAbbr;
    private String type;
    private int overs;

    MatchType(String typeAbbr, String type, int overs) {
        this.typeAbbr = typeAbbr;
        this.type = type;
        this.overs = overs;
    }

    public String getTypeAbbr() {
        return typeAbbr;
    }

    public String getType() {
        return type;
    }

    public int getOvers() {
        return overs;
    }
}
