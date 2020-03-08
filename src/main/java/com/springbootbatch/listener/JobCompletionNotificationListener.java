package com.springbootbatch.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springbootbatch.model.WriterSO;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<WriterSO> results = jdbcTemplate.query("SELECT id, full_name, random_num FROM writer", (rs, row) -> {
                WriterSO writerSO = new WriterSO();
                writerSO.setId(rs.getLong("id"));
                writerSO.setFullName(rs.getString("full_name"));
                writerSO.setRandomNum(rs.getString("random_num"));
                return writerSO;
            });

            for (WriterSO writerSO : results) {
                log.info("Found <" + writerSO + "> in the database.");
            }
        }
    }
}
