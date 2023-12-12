package mx.osdasanchez.authorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.osdasanchez.authorizationserver.dto.CreateAppUserDto;
import mx.osdasanchez.authorizationserver.dto.MessageDto;
import mx.osdasanchez.authorizationserver.entity.AppUser;
import mx.osdasanchez.authorizationserver.entity.Role;
import mx.osdasanchez.authorizationserver.enums.RoleName;
import mx.osdasanchez.authorizationserver.repository.AppUserRepository;
import mx.osdasanchez.authorizationserver.repository.RoleRepository;
import mx.osdasanchez.authorizationserver.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateAppUserDto dto){
        AppUser appUser = AppUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles().forEach(r -> {
            Role role = repository.findByRole(RoleName.valueOf(r))
                    .orElseThrow(()-> new RuntimeException("role not found"));
            roles.add(role);
        });
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return new MessageDto("user " + appUser.getUsername() + " saved");
    }
}