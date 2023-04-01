package itmo.kasymov.model;

import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

@Entity
@Table(name = "MODEL")
public class Model {
    @Id
    @Column(name = "MODEL_ID")
    private long id;
    @Column(name = "MODEL_NAME")
    private String name;
    @Column(name = "LENGTH")
    private int length;
    @Column(name = "WIDTH")
    private int width;
    @Column(name = "TYPE")
    private String type;
    @Transient
    private long brand;
    @ManyToOne
    @JoinColumn(name = "BRAND", referencedColumnName = "BRAND_ID")
    private Brand fk_brand;

    public Model() {
    }

    public Model(String name, int length, int width, String type, long brand, long... id) throws Exception {
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

    public long getId() {
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

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public String getType() {
        return this.type;
    }

    public long getBrand() {
        return this.brand;
    }
}
