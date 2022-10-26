package com.pras.absenin.domain.impl;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.GetAbsentHistoryById;
import com.pras.absenin.repository.AbsentRepository;

import javax.inject.Inject;

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
