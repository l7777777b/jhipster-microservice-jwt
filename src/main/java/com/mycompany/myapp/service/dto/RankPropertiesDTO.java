package com.mycompany.myapp.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.RankProperties} entity.
 */
public class RankPropertiesDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer rank;

    @NotNull
    private Integer minExp;

    @NotNull
    private Integer maxExp;

    @NotNull
    private Integer maxStamina;

    @NotNull
    private Integer maxAlly;

    @NotNull
    private Integer maxTeam;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getMinExp() {
        return minExp;
    }

    public void setMinExp(Integer minExp) {
        this.minExp = minExp;
    }

    public Integer getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }

    public Integer getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Integer getMaxAlly() {
        return maxAlly;
    }

    public void setMaxAlly(Integer maxAlly) {
        this.maxAlly = maxAlly;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RankPropertiesDTO rankPropertiesDTO = (RankPropertiesDTO) o;
        if (rankPropertiesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rankPropertiesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RankPropertiesDTO{" +
            "id=" + getId() +
            ", rank=" + getRank() +
            ", minExp=" + getMinExp() +
            ", maxExp=" + getMaxExp() +
            ", maxStamina=" + getMaxStamina() +
            ", maxAlly=" + getMaxAlly() +
            ", maxTeam=" + getMaxTeam() +
            "}";
    }
}
