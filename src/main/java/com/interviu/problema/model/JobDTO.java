package com.interviu.problema.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class JobDTO
{
    private String pid;
    private String jobName;
    private Date startTime;
    private Date endTime;

    public JobDTO(String pid, String jobName, Date startTime, Date endTime)
    {
        this.pid = pid;
        this.endTime = endTime;
        this.startTime = startTime;
        this.jobName = jobName;
    }

    public long getDurationMillis()
    {
        if (startTime == null || endTime == null) return 0;
        return endTime.getTime() - startTime.getTime();
    }

    public long getDurationMinutes()
    {
        if (startTime == null || endTime == null) return 0;
        return getDurationMillis() / (60 * 1000);
    }

    public String getPid()
    {
        return pid;
    }

    public void setPid(String pid)
    {
        this.pid = pid;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }
}
