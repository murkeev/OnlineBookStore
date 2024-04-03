package teamchallenge.server.services;

import teamchallenge.server.entities.Role;

public interface RoleService {

    Role findByName(String name);
}
