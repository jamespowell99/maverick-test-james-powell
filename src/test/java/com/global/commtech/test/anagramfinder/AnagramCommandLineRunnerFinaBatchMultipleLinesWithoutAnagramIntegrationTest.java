package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(args = {"src/test/resources/example3-finalBatchHasMultipleLinesNoAnagram.txt"})
@ExtendWith(OutputCaptureExtension.class)
class AnagramCommandLineRunnerFinaBatchMultipleLinesWithoutAnagramIntegrationTest {

    @Autowired
    AnagramCommandLineRunner anagramCommandLineRunner;

    @Test
    void shouldFindAnagrams(final CapturedOutput capturedOutput) {
        assertThat(capturedOutput.getOut()).contains("abc,bac,cba");
        assertThat(capturedOutput.getOut()).contains("fun,unf");
        assertThat(capturedOutput.getOut()).contains("hello");
        assertThat(capturedOutput.getOut()).contains("llehy");
    }


}