package kr.co.hhjpetclinicstudy.owner;

import kr.co.hhjpetclinicstudy.persistence.repository.SpecialtyRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.VetRepository;
import kr.co.hhjpetclinicstudy.service.service.VetService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceUnitTest {

    @InjectMocks
    private VetService vetService;

    @Mock
    private VetRepository vetRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

//    @Test
//    @DisplayName("Create Vet - Success")
//    public void createVet_success(){
//
//        //given
//        String firstName = "test1";
//        String lastName = "test2";
//        List<Long> specialtiesId = new ArrayList<>(Arrays.asList(1L, 2L));
//        VetReqDTO.CREATE create = VetCreators.createVetReqDto(firstName, lastName, specialtiesId);
//
//        String name1 = "specName1";
//        String name2 = "specName2";
//        List<Specialty> specialties = SpecialtyCreators.createSpecialties(name1, name2);
//        when(specialtyRepository.findAllById(anyList())).thenReturn(specialties);
//
//        //when
//        vetService.createVet(create);
//
//        //then
//        Vet vet = vetRepository.findByFirstNameAndLastName(firstName, lastName);
//        assertNotNull(vet);
//        assertEquals(firstName, vet.getFirstName());
//        assertEquals(lastName, vet.getFirstName());
//
//        List<VetSpecialty> vetSpecialties = vet.getVetSpecialties();
//        assertNotNull(vetSpecialties);
//        assertEquals(2, vetSpecialties.size());
//
//        List<Specialty> specialtyList = vetSpecialties
//                .stream()
//                .map(VetSpecialty::getSpecialty)
//                .collect(Collectors.toList());
//        assertTrue(specialtyList.stream().anyMatch(s -> s.getName().equals(name1)));
//        assertTrue(specialtyList.stream().anyMatch(s -> s.getName().equals(name2)));
//    }
}
