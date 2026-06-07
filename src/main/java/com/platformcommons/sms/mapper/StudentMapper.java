package com.platformcommons.sms.mapper;

import com.platformcommons.sms.dto.AddressDto;
import com.platformcommons.sms.dto.StudentRequestDto;
import com.platformcommons.sms.entity.Address;
import com.platformcommons.sms.entity.Student;

import java.util.List;

public final class StudentMapper {

    private StudentMapper() {
    }

    public static Student toEntity(StudentRequestDto dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .studentCode(dto.getStudentCode())
                .build();

        applyAddresses(student, dto.getAddresses());

        return student;
    }

    public static void replaceAddresses(
            Student student,
            List<AddressDto> addressDtos
    ) {
        student.getAddresses().clear();
        applyAddresses(student, addressDtos);
    }

    private static void applyAddresses(
            Student student,
            List<AddressDto> addressDtos
    ) {
        if (addressDtos == null) {
            return;
        }

        addressDtos.forEach(addressDto -> {
            Address address = Address.builder()
                    .addressType(addressDto.getAddressType())
                    .addressLine(addressDto.getAddressLine())
                    .student(student)
                    .build();

            student.getAddresses().add(address);
        });
    }
}
