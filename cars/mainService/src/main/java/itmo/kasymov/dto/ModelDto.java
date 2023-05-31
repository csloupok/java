package itmo.kasymov.dto;

import itmo.kasymov.entity.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link Model} entity
 */
public class ModelDto implements Serializable {
    private Long id;
    private String name;
    private Integer length;
    private Integer width;
    private Integer height;
    private String type;
    private BrandDto brand;

    public ModelDto() {
    }

    public ModelDto(Long id, String name, Integer length, Integer width, Integer height, String type, BrandDto brand) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.type = type;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public ModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public ModelDto setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ModelDto setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ModelDto setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public String getType() {
        return type;
    }

    public ModelDto setType(String type) {
        this.type = type;
        return this;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public ModelDto setBrand(BrandDto brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDto entity = (ModelDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.width, entity.width) &&
                Objects.equals(this.height, entity.height) &&
                Objects.equals(this.type, entity.type) &&
                Objects.equals(this.brand, entity.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, length, width, height, type, brand);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "length = " + length + ", " +
                "width = " + width + ", " +
                "height = " + height + ", " +
                "type = " + type + ", " +
                "brand = " + brand + ")";
    }
    public Model convertToEntity() throws Exception {
        Model model = new Model(getName(), getLength(), getWidth(), getType(), getBrand().getId(), getId());
        model.setBrand(brand.convertToEntity());
        return model;
    }
}