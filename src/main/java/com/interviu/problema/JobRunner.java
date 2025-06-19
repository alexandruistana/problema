package com.interviu.problema;

import com.interviu.problema.model.JobDTO;
import com.interviu.problema.services.JobLogService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class JobRunner
{

    private final JobLogService jobLogService;

    public JobRunner(JobLogService jobLogService)
    {
        this.jobLogService = jobLogService;
    }

    @PostConstruct
    public void run() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/main/resources/logs.log"));
        List<JobDTO> jobs = jobLogService.parseLogs(lines);
        jobLogService.generateReports(jobs);
    }
}
