package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.model.User;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {


    private JobRepository jobRepository;


    private UserRepository userRepository;


    @PostMapping("/{employerId}")
    public Job postJob(@PathVariable Long employerId, @RequestBody Job job) {
        User employer = userRepository.findById(employerId).orElse(null);
        if (employer == null) return null;

        job.setEmployer(employer);
        return jobRepository.save(job);
    }


    @GetMapping
    public List<Job> getAllJobs() {

        return jobRepository.findAll();
    }


    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id) {

        return jobRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);
    }
}
