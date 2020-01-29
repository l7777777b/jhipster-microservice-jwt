package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.RankPropertiesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.RankPropertiesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.RankProperties}.
 */
@RestController
@RequestMapping("/api")
public class RankPropertiesResource {

    private final Logger log = LoggerFactory.getLogger(RankPropertiesResource.class);

    private static final String ENTITY_NAME = "jhipsterMicroserviceJwtRankProperties";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RankPropertiesService rankPropertiesService;

    public RankPropertiesResource(RankPropertiesService rankPropertiesService) {
        this.rankPropertiesService = rankPropertiesService;
    }

    /**
     * {@code POST  /rank-properties} : Create a new rankProperties.
     *
     * @param rankPropertiesDTO the rankPropertiesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankPropertiesDTO, or with status {@code 400 (Bad Request)} if the rankProperties has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rank-properties")
    public ResponseEntity<RankPropertiesDTO> createRankProperties(@Valid @RequestBody RankPropertiesDTO rankPropertiesDTO) throws URISyntaxException {
        log.debug("REST request to save RankProperties : {}", rankPropertiesDTO);
        if (rankPropertiesDTO.getId() != null) {
            throw new BadRequestAlertException("A new rankProperties cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RankPropertiesDTO result = rankPropertiesService.save(rankPropertiesDTO);
        return ResponseEntity.created(new URI("/api/rank-properties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rank-properties} : Updates an existing rankProperties.
     *
     * @param rankPropertiesDTO the rankPropertiesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankPropertiesDTO,
     * or with status {@code 400 (Bad Request)} if the rankPropertiesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rankPropertiesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rank-properties")
    public ResponseEntity<RankPropertiesDTO> updateRankProperties(@Valid @RequestBody RankPropertiesDTO rankPropertiesDTO) throws URISyntaxException {
        log.debug("REST request to update RankProperties : {}", rankPropertiesDTO);
        if (rankPropertiesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RankPropertiesDTO result = rankPropertiesService.save(rankPropertiesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankPropertiesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rank-properties} : get all the rankProperties.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankProperties in body.
     */
    @GetMapping("/rank-properties")
    public List<RankPropertiesDTO> getAllRankProperties() {
        log.debug("REST request to get all RankProperties");
        return rankPropertiesService.findAll();
    }

    /**
     * {@code GET  /rank-properties/:id} : get the "id" rankProperties.
     *
     * @param id the id of the rankPropertiesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankPropertiesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rank-properties/{id}")
    public ResponseEntity<RankPropertiesDTO> getRankProperties(@PathVariable Long id) {
        log.debug("REST request to get RankProperties : {}", id);
        Optional<RankPropertiesDTO> rankPropertiesDTO = rankPropertiesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rankPropertiesDTO);
    }

    /**
     * {@code DELETE  /rank-properties/:id} : delete the "id" rankProperties.
     *
     * @param id the id of the rankPropertiesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rank-properties/{id}")
    public ResponseEntity<Void> deleteRankProperties(@PathVariable Long id) {
        log.debug("REST request to delete RankProperties : {}", id);
        rankPropertiesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
