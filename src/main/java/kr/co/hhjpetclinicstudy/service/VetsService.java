package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.repository.VetsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VetsService {
    private final VetsRepository vetsRepository;
}
