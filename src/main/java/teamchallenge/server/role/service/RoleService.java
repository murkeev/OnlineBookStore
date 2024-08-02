package teamchallenge.server.role.service;

import teamchallenge.server.role.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
