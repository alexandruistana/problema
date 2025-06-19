package com.interviu.problema.services;

import com.interviu.problema.model.Job;
import com.interviu.problema.model.JobDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobLogService
{
    private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    public List<JobDTO> parseLogs(List<String> lines) throws ParseException
    {
        Map<String, JobDTO> jobMap = new HashMap<>();
        for (String line : lines) {
            String[] parts = line.split(",", 4);
            Date startDate = formatter.parse(parts[0]);
            Date endTime = null;
            String jobName = parts[1];
            String status = parts[2];
            String pid = parts[3];
            String key = jobName + "-" + pid;

            if (status.equals("START")) {
                jobMap.put(key, new JobDTO(pid, jobName, startDate, endTime));
            } else if (status.equals("END") && jobMap.containsKey(key)) {
                jobMap.get(key).setEndTime(startDate);
            }
        }
        return jobMap.values().stream()
                .filter(job -> job.getStartTime() != null && job.getEndTime() != null)
                .collect(Collectors.toList());
    }

    public void generateReports(List<JobDTO> jobs) {
        System.out.println("Jobs > 5 minutes:");
        jobs.stream()
                .filter(job -> job.getDurationMinutes() >= 5)
                .forEach(job -> System.out.println(job.getJobName() + " (" + job.getPid() + ") - " + job.getDurationMinutes() + " min"));

        System.out.println("\nJobs > 10 minutes:");
        jobs.stream()
                .filter(job -> job.getDurationMinutes() >= 10)
                .forEach(job -> System.out.println(job.getJobName() + " (" + job.getPid() + ") - " + job.getDurationMinutes() + " min"));
    }
}
