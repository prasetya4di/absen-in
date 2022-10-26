package com.pras.absenin.domain.impl;

import com.pras.absenin.data.entity.Absent;
import com.pras.absenin.domain.GetAllAbsentHistory;
import com.pras.absenin.repository.AbsentRepository;

import java.util.List;

import javax.inject.Inject;

public class GetAllAbsentHistoryImpl implements GetAllAbsentHistory {
    private final AbsentRepository absentRepository;

    @Inject
    public GetAllAbsentHistoryImpl(AbsentRepository absentRepository) {
        this.absentRepository = absentRepository;
    }

    @Override
    public List<Absent> execute() {
        return absentRepository.getAllAbsentHistory();
    }
}
