package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RankPropertiesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RankProperties} and its DTO {@link RankPropertiesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RankPropertiesMapper extends EntityMapper<RankPropertiesDTO, RankProperties> {



    default RankProperties fromId(Long id) {
        if (id == null) {
            return null;
        }
        RankProperties rankProperties = new RankProperties();
        rankProperties.setId(id);
        return rankProperties;
    }
}
