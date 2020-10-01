package rm.tabou2.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rm.tabou2.service.dto.Programme;
import rm.tabou2.storage.tabou.entity.ProgrammeEntity;

@Mapper(componentModel = "spring")
public interface ProgrammeMapper extends AbstractMapper<ProgrammeEntity, Programme> {

    @Mapping(source = "etape", target = "etapeProgramme")
    ProgrammeEntity dtoToEntity(Programme dto);

    @Mapping(source = "etapeProgramme", target = "etape")
    Programme entityToDto(ProgrammeEntity entity);

}
