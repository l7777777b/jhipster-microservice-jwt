package com.mycompany.myapp.service.dto;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Car} entity.
 */
public class CarDTO implements Serializable {

    private Long id;


    private Set<DriverDTO> drivers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DriverDTO> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<DriverDTO> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarDTO carDTO = (CarDTO) o;
        if (carDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), carDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CarDTO{" +
            "id=" + getId() +
            "}";
    }
}
