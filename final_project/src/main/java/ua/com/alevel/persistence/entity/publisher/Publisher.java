package ua.com.alevel.persistence.entity.publisher;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "publishers")
public class Publisher extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public Publisher() {
        super();
    }
}
