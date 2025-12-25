package antifraud.entity.validation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BannedCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String number;

    public BannedCard() {}
    public BannedCard(CardRequest request) {
        this.number = request.status();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
