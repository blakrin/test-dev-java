package ca.logmein.pokergameapi.service.mapper;

import java.util.List;

/**
 * 
 * @author Blaise Siani
 * @Date	May 31, 2019
 *
 * @param <D>
 * @param <E>
 */

public interface EntityMapper <D, E> {

    D toDto(E entity);

    List <D> toDto(List<E> entityList);

    E toEntity(D dto);

    List <E> toEntity(List<D> dtoList);
}
