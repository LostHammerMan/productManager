package kr.co.hanbit.product_management.presentation.dto;

import org.mapstruct.Mapper;


public interface EntityMapper<D, E> {

    E toEntity(final D dto);

    D toDto(final E entity);
}
