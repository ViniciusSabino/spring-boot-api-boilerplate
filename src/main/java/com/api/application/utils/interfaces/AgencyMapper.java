package com.api.application.utils.interfaces;

import com.api.application.dto.AgencyDTO;
import com.api.infrastructure.entity.AgencyEntity;
import org.mapstruct.factory.Mappers;

public interface AgencyMapper {
    AgencyMapper INSTANCE = Mappers.getMapper(AgencyMapper.class);

    AgencyDTO entityToDTO(AgencyEntity entity);
}
