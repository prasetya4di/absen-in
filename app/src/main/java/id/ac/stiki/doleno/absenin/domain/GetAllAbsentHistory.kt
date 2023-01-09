package id.ac.stiki.doleno.absenin.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface GetAllAbsentHistory {
    LiveData<List<Absent>> execute();
}
