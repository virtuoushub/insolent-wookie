package org.colapietro.iso.cron;

import com.github.jeffsheets.AutowiringSpringBeanJobFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Peter Colapietro
 */
@Configuration
public class CronConfiguration {

    private static final String CRON_EXPRESSION = "01 0 * * * ?";

    private static final String TEST_CRON_EXPRESSION = "* * * * * ?";

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public CronTriggerFactoryBean getCronTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(process().getObject());
        cronTriggerFactoryBean.setCronExpression(TEST_CRON_EXPRESSION);
        return cronTriggerFactoryBean;
    }

    @Bean
    public JobDetailFactoryBean process() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(FooJob.class);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

        quartzScheduler.setOverwriteExistingJobs(true);

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        Trigger[] triggers = {
                getCronTrigger().getObject()
        };

        quartzScheduler.setTriggers(triggers);

        return quartzScheduler;
    }

}
