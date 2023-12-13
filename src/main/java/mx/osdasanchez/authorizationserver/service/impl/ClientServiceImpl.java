package mx.osdasanchez.authorizationserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.osdasanchez.authorizationserver.dto.CreateClientDto;
import mx.osdasanchez.authorizationserver.dto.MessageDto;
import mx.osdasanchez.authorizationserver.entity.Client;
import mx.osdasanchez.authorizationserver.repository.ClientRepository;
import mx.osdasanchez.authorizationserver.service.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id)
                .orElseThrow(() -> new RuntimeException("client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("client not found"));
        return Client.toRegisteredClient(client);
    }

    public MessageDto create(CreateClientDto dto) {
        Client client = clientFromDto(dto);
        clientRepository.save(client);
        return new MessageDto("client " + client.getClientId() + " saved");
    }

    // private methods
    private Client clientFromDto(CreateClientDto dto) {
        Client client = Client.builder()
                .clientId(dto.getClientId())
                .clientSecret(passwordEncoder.encode(dto.getClientSecret()))
                .authenticationMethods(dto.getAuthenticationMethods())
                .authorizationGrantTypes(dto.getAuthorizationGrantTypes())
                .redirectUris(dto.getRedirectUris())
                .postLogoutRedirectUris(dto.getPostLogoutRedirectUris())
                .scopes(dto.getScopes())
                .requireProofKey(dto.isRequireProofKey())
                .build();
        return client;
    }

}