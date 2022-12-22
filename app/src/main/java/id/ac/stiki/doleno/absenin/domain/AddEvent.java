package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface AddEvent {
    Task<Void> execute(Event event);
}
