package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterMicroserviceJwtApp;
import com.mycompany.myapp.domain.RankProperties;
import com.mycompany.myapp.repository.RankPropertiesRepository;
import com.mycompany.myapp.service.RankPropertiesService;
import com.mycompany.myapp.service.dto.RankPropertiesDTO;
import com.mycompany.myapp.service.mapper.RankPropertiesMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link RankPropertiesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterMicroserviceJwtApp.class)
public class RankPropertiesResourceIT {

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final Integer DEFAULT_MIN_EXP = 1;
    private static final Integer UPDATED_MIN_EXP = 2;

    private static final Integer DEFAULT_MAX_EXP = 1;
    private static final Integer UPDATED_MAX_EXP = 2;

    private static final Integer DEFAULT_MAX_STAMINA = 1;
    private static final Integer UPDATED_MAX_STAMINA = 2;

    private static final Integer DEFAULT_MAX_ALLY = 1;
    private static final Integer UPDATED_MAX_ALLY = 2;

    private static final Integer DEFAULT_MAX_TEAM = 1;
    private static final Integer UPDATED_MAX_TEAM = 2;

    @Autowired
    private RankPropertiesRepository rankPropertiesRepository;

    @Autowired
    private RankPropertiesMapper rankPropertiesMapper;

    @Autowired
    private RankPropertiesService rankPropertiesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restRankPropertiesMockMvc;

    private RankProperties rankProperties;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RankPropertiesResource rankPropertiesResource = new RankPropertiesResource(rankPropertiesService);
        this.restRankPropertiesMockMvc = MockMvcBuilders.standaloneSetup(rankPropertiesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankProperties createEntity(EntityManager em) {
        RankProperties rankProperties = new RankProperties()
            .rank(DEFAULT_RANK)
            .minExp(DEFAULT_MIN_EXP)
            .maxExp(DEFAULT_MAX_EXP)
            .maxStamina(DEFAULT_MAX_STAMINA)
            .maxAlly(DEFAULT_MAX_ALLY)
            .maxTeam(DEFAULT_MAX_TEAM);
        return rankProperties;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankProperties createUpdatedEntity(EntityManager em) {
        RankProperties rankProperties = new RankProperties()
            .rank(UPDATED_RANK)
            .minExp(UPDATED_MIN_EXP)
            .maxExp(UPDATED_MAX_EXP)
            .maxStamina(UPDATED_MAX_STAMINA)
            .maxAlly(UPDATED_MAX_ALLY)
            .maxTeam(UPDATED_MAX_TEAM);
        return rankProperties;
    }

    @BeforeEach
    public void initTest() {
        rankProperties = createEntity(em);
    }

    @Test
    @Transactional
    public void createRankProperties() throws Exception {
        int databaseSizeBeforeCreate = rankPropertiesRepository.findAll().size();

        // Create the RankProperties
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);
        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isCreated());

        // Validate the RankProperties in the database
        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeCreate + 1);
        RankProperties testRankProperties = rankPropertiesList.get(rankPropertiesList.size() - 1);
        assertThat(testRankProperties.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testRankProperties.getMinExp()).isEqualTo(DEFAULT_MIN_EXP);
        assertThat(testRankProperties.getMaxExp()).isEqualTo(DEFAULT_MAX_EXP);
        assertThat(testRankProperties.getMaxStamina()).isEqualTo(DEFAULT_MAX_STAMINA);
        assertThat(testRankProperties.getMaxAlly()).isEqualTo(DEFAULT_MAX_ALLY);
        assertThat(testRankProperties.getMaxTeam()).isEqualTo(DEFAULT_MAX_TEAM);
    }

    @Test
    @Transactional
    public void createRankPropertiesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rankPropertiesRepository.findAll().size();

        // Create the RankProperties with an existing ID
        rankProperties.setId(1L);
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RankProperties in the database
        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkRankIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setRank(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setMinExp(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxExpIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setMaxExp(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxStaminaIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setMaxStamina(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxAllyIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setMaxAlly(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaxTeamIsRequired() throws Exception {
        int databaseSizeBeforeTest = rankPropertiesRepository.findAll().size();
        // set the field null
        rankProperties.setMaxTeam(null);

        // Create the RankProperties, which fails.
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        restRankPropertiesMockMvc.perform(post("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRankProperties() throws Exception {
        // Initialize the database
        rankPropertiesRepository.saveAndFlush(rankProperties);

        // Get all the rankPropertiesList
        restRankPropertiesMockMvc.perform(get("/api/rank-properties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rankProperties.getId().intValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].minExp").value(hasItem(DEFAULT_MIN_EXP)))
            .andExpect(jsonPath("$.[*].maxExp").value(hasItem(DEFAULT_MAX_EXP)))
            .andExpect(jsonPath("$.[*].maxStamina").value(hasItem(DEFAULT_MAX_STAMINA)))
            .andExpect(jsonPath("$.[*].maxAlly").value(hasItem(DEFAULT_MAX_ALLY)))
            .andExpect(jsonPath("$.[*].maxTeam").value(hasItem(DEFAULT_MAX_TEAM)));
    }
    
    @Test
    @Transactional
    public void getRankProperties() throws Exception {
        // Initialize the database
        rankPropertiesRepository.saveAndFlush(rankProperties);

        // Get the rankProperties
        restRankPropertiesMockMvc.perform(get("/api/rank-properties/{id}", rankProperties.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rankProperties.getId().intValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.minExp").value(DEFAULT_MIN_EXP))
            .andExpect(jsonPath("$.maxExp").value(DEFAULT_MAX_EXP))
            .andExpect(jsonPath("$.maxStamina").value(DEFAULT_MAX_STAMINA))
            .andExpect(jsonPath("$.maxAlly").value(DEFAULT_MAX_ALLY))
            .andExpect(jsonPath("$.maxTeam").value(DEFAULT_MAX_TEAM));
    }

    @Test
    @Transactional
    public void getNonExistingRankProperties() throws Exception {
        // Get the rankProperties
        restRankPropertiesMockMvc.perform(get("/api/rank-properties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRankProperties() throws Exception {
        // Initialize the database
        rankPropertiesRepository.saveAndFlush(rankProperties);

        int databaseSizeBeforeUpdate = rankPropertiesRepository.findAll().size();

        // Update the rankProperties
        RankProperties updatedRankProperties = rankPropertiesRepository.findById(rankProperties.getId()).get();
        // Disconnect from session so that the updates on updatedRankProperties are not directly saved in db
        em.detach(updatedRankProperties);
        updatedRankProperties
            .rank(UPDATED_RANK)
            .minExp(UPDATED_MIN_EXP)
            .maxExp(UPDATED_MAX_EXP)
            .maxStamina(UPDATED_MAX_STAMINA)
            .maxAlly(UPDATED_MAX_ALLY)
            .maxTeam(UPDATED_MAX_TEAM);
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(updatedRankProperties);

        restRankPropertiesMockMvc.perform(put("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isOk());

        // Validate the RankProperties in the database
        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeUpdate);
        RankProperties testRankProperties = rankPropertiesList.get(rankPropertiesList.size() - 1);
        assertThat(testRankProperties.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testRankProperties.getMinExp()).isEqualTo(UPDATED_MIN_EXP);
        assertThat(testRankProperties.getMaxExp()).isEqualTo(UPDATED_MAX_EXP);
        assertThat(testRankProperties.getMaxStamina()).isEqualTo(UPDATED_MAX_STAMINA);
        assertThat(testRankProperties.getMaxAlly()).isEqualTo(UPDATED_MAX_ALLY);
        assertThat(testRankProperties.getMaxTeam()).isEqualTo(UPDATED_MAX_TEAM);
    }

    @Test
    @Transactional
    public void updateNonExistingRankProperties() throws Exception {
        int databaseSizeBeforeUpdate = rankPropertiesRepository.findAll().size();

        // Create the RankProperties
        RankPropertiesDTO rankPropertiesDTO = rankPropertiesMapper.toDto(rankProperties);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankPropertiesMockMvc.perform(put("/api/rank-properties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rankPropertiesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RankProperties in the database
        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRankProperties() throws Exception {
        // Initialize the database
        rankPropertiesRepository.saveAndFlush(rankProperties);

        int databaseSizeBeforeDelete = rankPropertiesRepository.findAll().size();

        // Delete the rankProperties
        restRankPropertiesMockMvc.perform(delete("/api/rank-properties/{id}", rankProperties.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RankProperties> rankPropertiesList = rankPropertiesRepository.findAll();
        assertThat(rankPropertiesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
