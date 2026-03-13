package com.site.resume.service;

import com.site.resume.model.Experience;
import com.site.resume.repository.ExperienceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }
}
