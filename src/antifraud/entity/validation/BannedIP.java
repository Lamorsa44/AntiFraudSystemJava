package antifraud.entity.validation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BannedIP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ip;

    public BannedIP() {}
    public BannedIP(SusIpRequest request) {
        this.ip = request.ip();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
