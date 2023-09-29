package bg.softuni.springautomappingobjects.repositories;

import bg.softuni.springautomappingobjects.entities.Employee;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNameAndSalaryDTO;
import bg.softuni.springautomappingobjects.entities.dtos.EmployeeNamesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new bg.softuni.springautomappingobjects.entities.dtos.EmployeeNamesDTO(e.firstName, e.lastName) FROM Employee AS e WHERE e.id = :id")
    EmployeeNamesDTO findNamesById(long id);

    EmployeeNameAndSalaryDTO findFirstNameAndSalaryById(long id);

    Optional<List<Employee>> findAllByBirthdayBeforeOrderBySalaryDesc(LocalDate date);

}
