package id.ac.stiki.doleno.absenin.domain;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

public interface ReadEventParticipant {
    List<EventParticipant> execute(int eventId);
}
