package com.mycompany.myapp.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A RankProperties.
 */
@Entity
@Table(name = "rank_properties")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RankProperties implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_rank", nullable = false)
    private Integer rank;

    @NotNull
    @Column(name = "min_exp", nullable = false)
    private Integer minExp;

    @NotNull
    @Column(name = "max_exp", nullable = false)
    private Integer maxExp;

    @NotNull
    @Column(name = "max_stamina", nullable = false)
    private Integer maxStamina;

    @NotNull
    @Column(name = "max_ally", nullable = false)
    private Integer maxAlly;

    @NotNull
    @Column(name = "max_team", nullable = false)
    private Integer maxTeam;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRank() {
        return rank;
    }

    public RankProperties rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getMinExp() {
        return minExp;
    }

    public RankProperties minExp(Integer minExp) {
        this.minExp = minExp;
        return this;
    }

    public void setMinExp(Integer minExp) {
        this.minExp = minExp;
    }

    public Integer getMaxExp() {
        return maxExp;
    }

    public RankProperties maxExp(Integer maxExp) {
        this.maxExp = maxExp;
        return this;
    }

    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }

    public Integer getMaxStamina() {
        return maxStamina;
    }

    public RankProperties maxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
        return this;
    }

    public void setMaxStamina(Integer maxStamina) {
        this.maxStamina = maxStamina;
    }

    public Integer getMaxAlly() {
        return maxAlly;
    }

    public RankProperties maxAlly(Integer maxAlly) {
        this.maxAlly = maxAlly;
        return this;
    }

    public void setMaxAlly(Integer maxAlly) {
        this.maxAlly = maxAlly;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public RankProperties maxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
        return this;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RankProperties)) {
            return false;
        }
        return id != null && id.equals(((RankProperties) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "RankProperties{" +
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
