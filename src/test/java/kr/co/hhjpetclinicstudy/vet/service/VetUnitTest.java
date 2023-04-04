package kr.co.hhjpetclinicstudy.vet.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.service.VetService;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.vet.domain.SpecialtyCreators;
import kr.co.hhjpetclinicstudy.vet.domain.VetCreators;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VetUnitTest {

    @InjectMocks
    private VetService vetService;

    @Mock
    private VetRepository vetRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Test
    @DisplayName("Create Vet - Success")
    public void createVet_success(){

        //given
        String firstName = "test1";
        String lastName = "test2";
        List<Long> specialtiesId = new ArrayList<>(Arrays.asList(1L, 2L));
        VetReqDTO.CREATE create = VetCreators.createVetReqDto(firstName, lastName, specialtiesId);

        String name1 = "specName1";
        String name2 = "specName2";
        List<Specialty> specialties = SpecialtyCreators.createSpecialties(name1, name2);
        when(specialtyRepository.findAllById(anyList())).thenReturn(specialties);

        //when
        vetService.createVet(create);

        //then
        Vet vet = vetRepository.findByFirstNameAndLastName(firstName, lastName);
        assertNotNull(vet);
        assertEquals(firstName, vet.getFirstName());
        assertEquals(lastName, vet.getFirstName());

        List<VetSpecialty> vetSpecialties = vet.getVetSpecialties();
        assertNotNull(vetSpecialties);
        assertEquals(2, vetSpecialties.size());

        List<Specialty> specialtyList = vetSpecialties
                .stream()
                .map(VetSpecialty::getSpecialty)
                .collect(Collectors.toList());
        assertTrue(specialtyList.stream().anyMatch(s -> s.getName().equals(name1)));
        assertTrue(specialtyList.stream().anyMatch(s -> s.getName().equals(name2)));
    }
}
