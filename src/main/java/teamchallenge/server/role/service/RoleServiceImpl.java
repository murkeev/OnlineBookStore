package teamchallenge.server.role.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamchallenge.server.role.entity.Role;
import teamchallenge.server.role.entity.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
