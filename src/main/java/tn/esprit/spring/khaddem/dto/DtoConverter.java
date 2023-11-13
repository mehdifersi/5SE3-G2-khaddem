package tn.esprit.spring.khaddem.dto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component

public class DtoConverter {
    private final ModelMapper modelMapper = new ModelMapper();


    public <D, E> D convertEntityToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <D, E> E convertDtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
