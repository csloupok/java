package itmo.kasymov.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Entity
@Table(name = "MODEL")
public class Model {
    @Id
    @Column(name = "MODEL_ID")
    private Long id;
    @Column(name = "MODEL_NAME")
    private String name;
    @Column(name = "LENGTH")
    private Integer length;
    @Column(name = "WIDTH")
    private Integer width;
    @Column(name = "TYPE")
    private String type;
    @Transient
    private Long brand;
    @ManyToOne
    @JoinColumn(name = "BRAND", referencedColumnName = "BRAND_ID")
    private Brand fk_brand;

    public Model() {
    }

    public Model(String name, Integer length, Integer width, String type, Long brand, Long... id) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("String is empty!");
        } else {
            if (id.length > 0) {
                this.id = id[0];
            } else {
                this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
            }

            this.name = name;
            this.length = length;
            this.width = width;
            this.type = type.toUpperCase();
            this.brand = brand;
        }
    }

    public Brand getFk_brand() {
        return this.fk_brand;
    }

    public void setFk_brand(Brand fk_brand) {
        this.fk_brand = fk_brand;
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

    public Integer getLength() {
        return this.length;
    }

    public Integer getWidth() {
        return this.width;
    }

    public String getType() {
        return this.type;
    }

    public Long getBrand() {
        return this.brand;
    }
}