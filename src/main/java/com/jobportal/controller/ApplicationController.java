package com.jobportal.controller;

import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.ApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    // Apply to a job
    @PostMapping("/{jobId}/{userId}")
    public Application applyToJob(@PathVariable Long jobId, @PathVariable Long userId,
                                  @RequestBody Application application) {
        Job job = jobRepository.findById(jobId).orElse(null);
        User applicant = userRepository.findById(userId).orElse(null);

        if (job == null || applicant == null) return null;

        application.setJob(job);
        application.setApplicant(applicant);
        return applicationRepository.save(application);
    }

    // Get all applications
    @GetMapping
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
}
