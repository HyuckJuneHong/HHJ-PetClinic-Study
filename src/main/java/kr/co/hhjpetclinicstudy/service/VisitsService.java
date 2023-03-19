package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.repository.VisitsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitsService {
    private final VisitsRepository visitsRepository;
}
