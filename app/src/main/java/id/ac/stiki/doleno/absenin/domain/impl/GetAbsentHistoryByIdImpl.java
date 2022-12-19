package id.ac.stiki.doleno.absenin.domain.impl;

import javax.inject.Inject;

import id.ac.stiki.doleno.absenin.data.entity.Absent;
import id.ac.stiki.doleno.absenin.domain.GetAbsentHistoryById;
import id.ac.stiki.doleno.absenin.repository.AbsentRepository;

public class GetAbsentHistoryByIdImpl implements GetAbsentHistoryById {
    private final AbsentRepository absentRepository;

    @Inject
    public GetAbsentHistoryByIdImpl(AbsentRepository absentRepository) {
        this.absentRepository = absentRepository;
    }

    @Override
    public Absent execute(int id) {
        return absentRepository.getAbsentHistoryById(id);
    }
}
