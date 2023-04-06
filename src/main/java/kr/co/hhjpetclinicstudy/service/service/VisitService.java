package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.persistence.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
}
