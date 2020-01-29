package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class RankPropertiesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RankProperties.class);
        RankProperties rankProperties1 = new RankProperties();
        rankProperties1.setId(1L);
        RankProperties rankProperties2 = new RankProperties();
        rankProperties2.setId(rankProperties1.getId());
        assertThat(rankProperties1).isEqualTo(rankProperties2);
        rankProperties2.setId(2L);
        assertThat(rankProperties1).isNotEqualTo(rankProperties2);
        rankProperties1.setId(null);
        assertThat(rankProperties1).isNotEqualTo(rankProperties2);
    }
}
