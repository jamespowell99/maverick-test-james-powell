package com.global.commtech.test.anagramfinder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class AnagramCommandLineRunner implements CommandLineRunner {

    private final AnagramBatchProcessor anagramBatchProcessor;

    @Override
    public void run(final String... args) throws Exception {
        Assert.isTrue(args.length == 1, "Please ensure that the input file is provided");

        final File file = new File(args[0]);
        Assert.isTrue(file.exists(), args[0] + " Does not exist");

        Path path = Paths.get(args[0]);

        List<String> currentBatch = new ArrayList<>();
        int currentLength = 0;
        // Work through the file line by line, process in batches of string length
        try (Stream<String> lines = Files.lines(path)) {
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (line.length() > currentLength) {
                    // length has changed so need to process current batch and start a new one
                    processBatch(currentBatch);
                    currentLength = line.length();
                    currentBatch = new ArrayList<>();
                    currentBatch.add(line);
                } else {
                    // same length as last time so just add the line
                    currentBatch.add(line);
                }
            }
            //now need to process the final batch
            processBatch(currentBatch);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file " + args[0], e);
        }
    }

    private void processBatch(List<String> lines) {
        if (lines.size() > 0) {
            //process this batch
            List<String> anagrams = anagramBatchProcessor.findAnagrams(lines);
            anagrams.forEach(System.out::println);
        }
    }
}
