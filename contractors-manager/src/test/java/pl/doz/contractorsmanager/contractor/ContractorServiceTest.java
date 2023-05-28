package pl.doz.contractorsmanager.contractor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.doz.contractorsmanager.exception.ContractorBlankNameException;
import pl.doz.contractorsmanager.exception.ContractorNotFoundException;
import pl.doz.contractorsmanager.exception.NonUniqueContractorNameException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContractorServiceTest {

    private static ContractorService service;

    private static final ContractorRepository CONTRACTOR_REPOSITORY = Mockito.mock(ContractorRepository.class);

    @BeforeAll
    static void init() {
        service = new ContractorService(CONTRACTOR_REPOSITORY);
    }

    @Test
    void whenFindAllThenOk() {

        //given
        List<Contractor> given = List.of(new Contractor(1L, "Jan"), new Contractor(2L, "Ala"));
        List<Contractor> expected = List.of(new Contractor(1L, "Jan"), new Contractor(2L, "Ala"));
        int expectedSize = 2;

        // when
        Mockito.when(CONTRACTOR_REPOSITORY.findAll()).thenReturn(given);
        List<Contractor> contractors = service.findAllContractors();

        //then
        assertThat(contractors.size()).isEqualTo(expectedSize);
        assertThat(contractors).containsAll(expected);
    }

    @Test
    void whenAddContractorWithNonUniqueNameThenThrowException() {

        // given
        Contractor givenContractor = new Contractor(1L, "Jan");
        String givenName = "Jan";
        String expectedMessage = "Contractor with this name already exists: Jan";

        // when
        Mockito.when(CONTRACTOR_REPOSITORY.existsByName(givenName)).thenReturn(true);

        // then
        NonUniqueContractorNameException ex = assertThrows(NonUniqueContractorNameException.class, () -> {
            service.newContractor(givenContractor);
        });
        assertThat(ex.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void whenAddUserWithNoNameThenThrow() {

        // given
        Contractor givenContractor = new Contractor();
        String expectedMessage = "Contractor's name cannot be null";

        // then
        ContractorBlankNameException ex = assertThrows(ContractorBlankNameException.class, () -> {
            service.newContractor(givenContractor);
        });
        assertThat(ex.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void whenReplaceContractorWithNonUniqueNameThenThrowException() {

        // given
        Contractor givenContractor = new Contractor("Jan");
        String givenName = "Jan";
        String expectedMessage = "Contractor with this name already exists: Jan";

        // when
        Mockito.when(CONTRACTOR_REPOSITORY.existsByName(givenName)).thenReturn(true);

        // then
        NonUniqueContractorNameException ex = assertThrows(NonUniqueContractorNameException.class, () -> {
            service.replaceContractor(2L, givenContractor);
        });
        assertThat(ex.getMessage()).isEqualTo(expectedMessage);
    }

    @Test
    void whenFindContractorWithGivenIdThenOk() {

        // given
        Long givenID = 2L;
        Contractor givenContractor = new Contractor(2L, "Ala");
        Contractor expectedContractor = new Contractor(2L, "Ala");

        // when
        Mockito.when(CONTRACTOR_REPOSITORY.findById(givenID)).thenReturn(Optional.of(givenContractor));

        // then
        assertThat(service.findContractorByID(givenID)).isEqualTo(expectedContractor);
    }

    @Test
    void whenIdOutOfRangeThenThrowException() {

        // given
        String expectedMessage = "Contractor with this id does not exists: 3";
        Long indexOutOfRange = 3L;

        // when
        Mockito.when(CONTRACTOR_REPOSITORY.findById(indexOutOfRange)).thenReturn(Optional.empty());

        // then
        ContractorNotFoundException ex = assertThrows(ContractorNotFoundException.class, () -> {
            service.findContractorByID(indexOutOfRange);
        });
        assertThat(ex.getMessage()).isEqualTo(expectedMessage);
    }
}