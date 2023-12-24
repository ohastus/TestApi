package dev.naumen.testapi.repository;

import dev.naumen.testapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByRoleNameAndActiveIsTrueAndDepartmentActiveIsTrue(String roleName, Pageable pageable);

    @Query(" SELECT u FROM User u" +
            " WHERE u.department.id = :departmentId " +
            " AND u.updateDate < :updateDate")
    List<User> findAllByDepartmentIdAndUpdateDateBefore(
            @Param("departmentId") Long departmentId,
            @Param("updateDate") LocalDate updateDate
    );


}
