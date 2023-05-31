package itmo.kasymov.dto;

import itmo.kasymov.entity.Brand;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link Brand} entity
 */
public class BrandDto implements Serializable {
    private Long id;
    private String name;
    private LocalDate date;

    public BrandDto() {
    }

    public BrandDto(Long id, String name, LocalDate date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public BrandDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public BrandDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandDto entity = (BrandDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.date, entity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "date = " + date + ")";
    }

    public Brand convertToEntity() throws Exception {
        return new Brand(getName(), getDate(), getId());
    }
}