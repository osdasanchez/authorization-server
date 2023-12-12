package mx.osdasanchez.authorizationserver.service;

import mx.osdasanchez.authorizationserver.dto.CreateAppUserDto;
import mx.osdasanchez.authorizationserver.dto.MessageDto;

public interface AppUserService {
    public MessageDto createUser(CreateAppUserDto dto);
}
