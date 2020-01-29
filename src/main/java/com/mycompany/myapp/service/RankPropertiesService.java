package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.RankProperties;
import com.mycompany.myapp.repository.RankPropertiesRepository;
import com.mycompany.myapp.service.dto.RankPropertiesDTO;
import com.mycompany.myapp.service.mapper.RankPropertiesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RankProperties}.
 */
@Service
@Transactional
public class RankPropertiesService {

    private final Logger log = LoggerFactory.getLogger(RankPropertiesService.class);

    private final RankPropertiesRepository rankPropertiesRepository;

    private final RankPropertiesMapper rankPropertiesMapper;

    public RankPropertiesService(RankPropertiesRepository rankPropertiesRepository, RankPropertiesMapper rankPropertiesMapper) {
        this.rankPropertiesRepository = rankPropertiesRepository;
        this.rankPropertiesMapper = rankPropertiesMapper;
    }

    /**
     * Save a rankProperties.
     *
     * @param rankPropertiesDTO the entity to save.
     * @return the persisted entity.
     */
    public RankPropertiesDTO save(RankPropertiesDTO rankPropertiesDTO) {
        log.debug("Request to save RankProperties : {}", rankPropertiesDTO);
        RankProperties rankProperties = rankPropertiesMapper.toEntity(rankPropertiesDTO);
        rankProperties = rankPropertiesRepository.save(rankProperties);
        return rankPropertiesMapper.toDto(rankProperties);
    }

    /**
     * Get all the rankProperties.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RankPropertiesDTO> findAll() {
        log.debug("Request to get all RankProperties");
        return rankPropertiesRepository.findAll().stream()
            .map(rankPropertiesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one rankProperties by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RankPropertiesDTO> findOne(Long id) {
        log.debug("Request to get RankProperties : {}", id);
        return rankPropertiesRepository.findById(id)
            .map(rankPropertiesMapper::toDto);
    }

    /**
     * Delete the rankProperties by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RankProperties : {}", id);
        rankPropertiesRepository.deleteById(id);
    }
}
