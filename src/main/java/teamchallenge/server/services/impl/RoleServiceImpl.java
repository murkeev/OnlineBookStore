package teamchallenge.server.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.entities.Role;
import teamchallenge.server.repositories.RoleRepository;
import teamchallenge.server.services.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
