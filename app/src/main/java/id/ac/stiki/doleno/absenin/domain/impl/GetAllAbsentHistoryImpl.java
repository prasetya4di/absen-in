package id.ac.stiki.doleno.absenin.domain.impl;

import java.util.List;

import javax.inject.Inject;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.domain.GetAllAbsentHistory;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

public class GetAllAbsentHistoryImpl implements GetAllAbsentHistory {
    private final AbsentRepository absentRepository;

    @Inject
    public GetAllAbsentHistoryImpl(AbsentRepository absentRepository) {
        this.absentRepository = absentRepository;
    }

    @Override
    public List<Absent> execute() {
        return absentRepository.read();
    }
}
