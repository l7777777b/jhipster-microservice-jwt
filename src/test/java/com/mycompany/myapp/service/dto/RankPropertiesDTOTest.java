package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RankPropertiesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankPropertiesDTO.class);
        RankPropertiesDTO rankPropertiesDTO1 = new RankPropertiesDTO();
        rankPropertiesDTO1.setId(1L);
        RankPropertiesDTO rankPropertiesDTO2 = new RankPropertiesDTO();
        assertThat(rankPropertiesDTO1).isNotEqualTo(rankPropertiesDTO2);
        rankPropertiesDTO2.setId(rankPropertiesDTO1.getId());
        assertThat(rankPropertiesDTO1).isEqualTo(rankPropertiesDTO2);
        rankPropertiesDTO2.setId(2L);
        assertThat(rankPropertiesDTO1).isNotEqualTo(rankPropertiesDTO2);
        rankPropertiesDTO1.setId(null);
        assertThat(rankPropertiesDTO1).isNotEqualTo(rankPropertiesDTO2);
    }
}
