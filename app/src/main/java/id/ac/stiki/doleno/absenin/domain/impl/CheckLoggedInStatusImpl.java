package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.domain.CheckLoggedInStatus;
import id.ac.stiki.doleno.absenin.repository.AuthRepository;

public class CheckLoggedInStatusImpl implements CheckLoggedInStatus {
    private final AuthRepository authRepository;

    public CheckLoggedInStatusImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Boolean execute() {
        return authRepository.isLoggedIn();
    }
}
