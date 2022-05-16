package fr.mns.jee.projet.service.dto;

public interface IConverter<TDto, T> {

    T convert(TDto dto);
    T convert(T entity, TDto dto);
}
