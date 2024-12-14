package com.packtpub.onlineauction.etlbatchprocess;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.nio.file.*;
import java.util.Calendar;

@EnableScheduling
@SpringBootApplication
@Slf4j
public class EtlBatchProcessApplication {

    private final JobLauncher jobLauncher;
    private final Job job;

    private static final String BIDS_FILE = "../data-files/Bids.csv";
    private static final String  USERS_FILE = "../data-files/Users.csv";
    private static final String  PRODUCTS_FILE = "../data-files/Products.csv";
    private static final String  AUCTIONS_FILE = "../data-files/Auctions.csv";

    public EtlBatchProcessApplication(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(EtlBatchProcessApplication.class)
                .web(WebApplicationType.NONE)
                .run(args)
                .registerShutdownHook();
    }

    @Scheduled(fixedRate = 60000)
    public void importFiles() throws Exception {
        try {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .addString("usersFile", USERS_FILE)
                .addString("bidsFile", BIDS_FILE)
                .addString("productsFile", PRODUCTS_FILE)
                .addString("auctionsFile", AUCTIONS_FILE)
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            log.info("Processing job execution...");
        }
        if (jobExecution.getStatus().isUnsuccessful()) {
            log.error("Job execution failed with exceptions: {}", jobExecution.getAllFailureExceptions());
        } else {
            log.info("Job execution completed successfully.");
        }
    } catch (Exception e) {
        log.error("Error occurred during job execution", e);
    }
        }
}
