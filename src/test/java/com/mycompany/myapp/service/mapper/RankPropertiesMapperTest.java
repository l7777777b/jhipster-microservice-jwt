package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class RankPropertiesMapperTest {

    private RankPropertiesMapper rankPropertiesMapper;

    @BeforeEach
    public void setUp() {
        rankPropertiesMapper = new RankPropertiesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(rankPropertiesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(rankPropertiesMapper.fromId(null)).isNull();
    }
}
