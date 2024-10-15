package app.model;

public class Record {
    private String customerId;
    private String contractId;
    private String geozone;
    private String teamCode;
    private String projectCode;
    private int buildDuration; // Store build duration in seconds

    public Record(String customerId, String contractId, String geozone, String teamCode, String projectCode, int buildDuration) {
        this.customerId = customerId;
        this.contractId = contractId;
        this.geozone = geozone;
        this.teamCode = teamCode;
        this.projectCode = projectCode;
        this.buildDuration = buildDuration;
    }

    public String getCustomerId() { return customerId; }
    public String getContractId() { return contractId; }
    public String getGeozone() { return geozone; }
    public int getBuildDuration() { return buildDuration; }
    public String getTeamCode() { return teamCode; }
    public String getProjectCode() { return projectCode; }
}
