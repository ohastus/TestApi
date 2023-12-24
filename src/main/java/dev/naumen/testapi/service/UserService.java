package dev.naumen.testapi.service;

import dev.naumen.testapi.model.User;
import dev.naumen.testapi.repository.UserRepository;
import dev.naumen.testapi.service.dto.ExternalUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ExternalService externalService;

    public UserService(UserRepository userRepository, ExternalService externalService) {
        this.userRepository = userRepository;
        this.externalService = externalService;
    }

    public void updateUser(Long departmentId) {
        List<User> usersToUpdate = userRepository.findAllByDepartmentIdAndUpdateDateBefore(
                departmentId, LocalDate.now().minusDays(30)
        );

        for (User user : usersToUpdate) {
            ExternalUserDto externalUserDto = externalService.getUserByLogin(user.getLogin());
            if (externalUserDto != null) {
                updateUserData(user, externalUserDto);
                userRepository.save(user);
            }
        }
    }

    public void updateUserData(User user, ExternalUserDto externalUserDto) {
        user.setFullName(externalUserDto.getFullName());
        user.setEmail(externalUserDto.getEmail());
        user.setUpdateDate(LocalDate.now());

    }

    public Page<User> getAdminUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return userRepository.findByRoleNameAndActiveIsTrueAndDepartmentActiveIsTrue("ADMIN", pageRequest);


    }

}
