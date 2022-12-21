package id.ac.stiki.doleno.absenin.domain.impl;

import id.ac.stiki.doleno.absenin.domain.DoLogout;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.EventRepository;
import id.ac.stiki.doleno.absenin.repository.UserRepository;

public class DoLogoutImpl implements DoLogout {

    private final UserRepository userRepository;
    private final AbsentRepository absentRepository;
    private final EventRepository eventRepository;

    public DoLogoutImpl(UserRepository userRepository, AbsentRepository absentRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.absentRepository = absentRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public void execute() {
        userRepository.delete();
        absentRepository.delete();
        eventRepository.delete();
    }
}
