package ait.cohort46.person.service;

import ait.cohort46.person.dto.ChildDto;
import ait.cohort46.person.dto.EmployeeDto;
import ait.cohort46.person.dto.PersonDto;
import ait.cohort46.person.model.Child;
import ait.cohort46.person.model.Employee;
import ait.cohort46.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonModelDtoMapper {
    private final ModelMapper modelMapper;

    public PersonDto mapToDto(Person person) {
        Class<?> dtoClass = person.getClass();
        String className = dtoClass.getName();
        String[] packages = className.split("\\.");
        packages[packages.length - 2] = "dto";
        packages[packages.length - 1] = packages[packages.length - 1] + "Dto";
        className = String.join(".", packages);
        Class<? extends PersonDto> destClass = null;
        try {
            destClass = Class.forName(className).asSubclass(PersonDto.class);
        } catch (ClassNotFoundException var7) {
            ClassNotFoundException e = var7;
            e.printStackTrace();
        }

        return modelMapper.map(person, destClass);
    }

    public Person mapToModel(PersonDto personDto) {
        Class<?> dtoClass = personDto.getClass();
        String className = dtoClass.getName();
        String[] packages = className.split("\\.");
        packages[packages.length - 2] = "model";
        packages[packages.length - 1] = packages[packages.length - 1].substring(0, className.length() - 3);
        className = String.join(".", packages);
        Class<? extends Person> destClass = null;
        try {
            destClass = Class.forName(className).asSubclass(Person.class);
        } catch (ClassNotFoundException var7) {
            ClassNotFoundException e = var7;
            e.printStackTrace();
        }

        return modelMapper.map(personDto, destClass);
    }
}
