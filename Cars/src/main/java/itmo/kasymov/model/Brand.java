package itmo.kasymov.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @Column(name = "BRAND_ID")
    private Long id;
    @Column(name = "BRAND_NAME")
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "FOUND_DATE")
    private LocalDate date;

    public Brand() {
    }

    public Brand(String name, LocalDate date, Long... id) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("String is empty!");
        } else {
            if (id.length > 0) {
                this.id = id[0];
            } else {
                this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            }

            this.name = name;
            this.date = date;
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("String is empty!");
        } else {
            this.name = name;
        }
    }

    public LocalDate getDate() {
        return this.date;
    }
}
