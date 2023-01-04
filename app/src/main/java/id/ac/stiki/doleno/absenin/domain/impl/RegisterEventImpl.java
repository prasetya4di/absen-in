package id.ac.stiki.doleno.absenin.domain.impl;

import android.os.AsyncTask;

import com.google.android.gms.tasks.Task;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;
import id.ac.stiki.doleno.absenin.data.entity.User;
import id.ac.stiki.doleno.absenin.domain.RegisterEvent;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;
import id.ac.stiki.doleno.absenin.repository.EventParticipantRepository;

public class RegisterEventImpl implements RegisterEvent {
    private final AbsentRepository absentRepository;
    private final EventParticipantRepository eventParticipantRepository;

    public RegisterEventImpl(AbsentRepository absentRepository, EventParticipantRepository eventParticipantRepository) {
        this.absentRepository = absentRepository;
        this.eventParticipantRepository = eventParticipantRepository;
    }

    @Override
    public Task<Void> execute(Event event, User user) {
        EventParticipant eventParticipant = new EventParticipant(event, user);
        Absent absent = new Absent(event);
        return eventParticipantRepository
                .post(eventParticipant)
                .addOnSuccessListener(success -> AsyncTask.execute(() -> eventParticipantRepository.create(eventParticipant)))
                .continueWithTask(task -> absentRepository.post(absent))
                .addOnSuccessListener(success -> AsyncTask.execute(() -> absentRepository.create(absent)));
    }
}
