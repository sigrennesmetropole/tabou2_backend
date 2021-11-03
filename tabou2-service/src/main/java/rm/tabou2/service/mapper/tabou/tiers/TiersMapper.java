package rm.tabou2.service.mapper.tabou.tiers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rm.tabou2.service.dto.Tiers;
import rm.tabou2.service.mapper.AbstractMapper;
import rm.tabou2.storage.tabou.entity.tiers.TiersEntity;

@Mapper(componentModel = "spring")
public interface TiersMapper extends AbstractMapper<TiersEntity, Tiers> {

    @Override
    @Mapping(target = "adresse", ignore = true)
    TiersEntity dtoToEntity(Tiers dto);

    @Override
    @Mapping(target = "adresse", ignore = true)
    Tiers entityToDto(TiersEntity entity);
}
