package org.colapietro.iso.cron;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Peter Colapietro
 */
@Service
@Transactional
public class FooJob implements Job {

    private final Logger log = LoggerFactory.getLogger(FooJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("FOO");
    }
}
