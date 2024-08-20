//package com.packtpub.onlineauction.etlbatchprocess.config;
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//
//@Component
//@StepScope
//public class FileRenamingTasklet implements Tasklet {
//
//    @Value("#{jobParameters['inputFilePath']}")
//    private String inputFilePath;
//
//    @Override
//    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//        File file = new File(inputFilePath);
//        if (file.exists()) {
//            String newFileName = file.getAbsolutePath().replace(".csv", "_processed.csv");
//            Path sourcePath = file.toPath();
//            Path targetPath = new File(newFileName).toPath();
//
//            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
//        }
//        return RepeatStatus.FINISHED;
//    }
//}
