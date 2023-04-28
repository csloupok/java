package itmo.kasymov.entity;


import jakarta.persistence.*;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "ENGINE")
public class Engine {
    @Id
    @Column(name = "ENGINE_ID")
    private Long id;
    @Column(name = "ENGINE_NAME")
    private String name;
    @Column(name = "VOLUME")
    private Integer volume;
    @Column(name = "CYLINDERS")
    private Integer cylinders;
    @Column(name = "HEIGHT")
    private Integer height;
    @ManyToOne
    @JoinColumn(name = "MODEL", referencedColumnName = "MODEL_ID")
    private Model model;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getVolume() {
        return volume;
    }

    public Integer getCylinders() {
        return cylinders;
    }

    public Integer getHeight() {
        return height;
    }

    public Model getModel() {
        return model;
    }

    public void setName(String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            throw new Exception("String is empty!");
        }
        this.name = name;
    }

    public void setVolume(Integer volume) throws Exception {
        if (volume <= 0) {
            throw new Exception("Incorrect value!");
        }
        this.volume = volume;
    }

    public void setCylinders(Integer cylinders) throws Exception {
        if (cylinders <= 0) {
            throw new Exception("Incorrect value!");
        }
        this.cylinders = cylinders;
    }

    public void setHeight(Integer height) throws Exception {
        if (height <= 0) {
            throw new Exception("Incorrect value!");
        }
        this.height = height;
    }
}
