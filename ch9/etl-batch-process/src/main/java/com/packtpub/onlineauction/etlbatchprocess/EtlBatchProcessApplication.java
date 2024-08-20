package com.packtpub.onlineauction.etlbatchprocess;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.Job;
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

    @Scheduled(fixedRate = 2000)
    public void importBidsFile() throws Exception {
        String bidsFile = "/Users/wanderson/01-Professional/01-Author/01-Books/Software-Architecture-with-Spring-6.0/ch9/data-files/Bids.csv";
        String usersFile = "/Users/wanderson/01-Professional/01-Author/01-Books/Software-Architecture-with-Spring-6.0/ch9/data-files/Users.csv";
        String productsFile = "/Users/wanderson/01-Professional/01-Author/01-Books/Software-Architecture-with-Spring-6.0/ch9/data-files/Products.csv";
        String auctionsFile = "/Users/wanderson/01-Professional/01-Author/01-Books/Software-Architecture-with-Spring-6.0/ch9/data-files/Auctions.csv";

        val jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .addString("bidsFile", bidsFile)
                .addString("usersFile", usersFile)
                .addString("productsFile", productsFile)
                .addString("auctionsFile", auctionsFile)
                .toJobParameters();

        val jobExecution = jobLauncher.run(job, jobParameters);
        while (jobExecution.isRunning()) {
            log.info("processing job execution...");
        }
    }

}
