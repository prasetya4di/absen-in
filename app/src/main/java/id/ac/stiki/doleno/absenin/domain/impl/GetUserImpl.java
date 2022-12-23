package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.GetUser;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class GetUserImpl implements GetUser {
    private final UserRepository userRepository;

    public GetUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute() {
        return userRepository.read();
    }
}
