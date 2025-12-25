package antifraud.entity.validation;

public record SusIpResponse(Long id, String ip) {
    public SusIpResponse(BannedIP bannedIP) {
        this(bannedIP.getId(), bannedIP.getIp());
    }
}
