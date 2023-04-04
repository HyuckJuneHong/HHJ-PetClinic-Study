package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
}
