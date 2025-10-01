package dev.jlucasbs.study.mapper.custom;

import dev.jlucasbs.study.data.dto.v2.PersonDTOV2;
import dev.jlucasbs.study.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person entity) {
        PersonDTOV2 dto =  new PersonDTOV2();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setBithDate(new Date());
        dto.setAddress(entity.getAddress());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 dto) {
        Person entity =  new Person();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
//        entity.setBithDate(new Date());
        entity.setAddress(dto.getAddress());
        entity.setGender(dto.getGender());
        return entity;
    }
}
