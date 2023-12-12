package mx.osdasanchez.authorizationserver.service;

import mx.osdasanchez.authorizationserver.dto.CreateClientDto;
import mx.osdasanchez.authorizationserver.dto.MessageDto;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public interface ClientService extends RegisteredClientRepository {
    public MessageDto create(CreateClientDto dto);
}
