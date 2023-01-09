package id.ac.stiki.doleno.absenin.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.EventParticipant;

public interface GetEventParticipant {
    LiveData<List<EventParticipant>> execute(int eventId);
}
