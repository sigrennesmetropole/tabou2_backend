package rm.tabou2.service.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @param <E> entity
 * @param <D> DTO
 */
public interface AbstractMapper<E, D> {

    /**
     * @param dto dto to transform to entity
     * @return entity
     */
    E dtoToEntity(D dto);

    List<E> dtoToEntities(List<D> dtos);

    /**
     * @param entity entity to transform to dto
     * @return dto
     */
    D entityToDto(E entity);

    List<D> entitiesToDto(List<E> entities);

    default Page<D> entitiesToDto(Page<E> entities, Pageable pageable){
        return new PageImpl<>(entitiesToDto(entities.getContent()),pageable, entities.getTotalElements());
    };
}
