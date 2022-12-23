package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.domain.GetUserRole;
import id.ac.stiki.doleno.absenin.repository.UserRepository;
import id.ac.stiki.doleno.absenin.util.enums.Role;

public class GetUserRoleImpl implements GetUserRole {
    private final UserRepository userRepository;

    public GetUserRoleImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Role execute() {
        return userRepository.read().role;
    }
}
