package com.global.commtech.test.anagramfinder;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnagramBatchProcessorTest {

    private final AnagramBatchProcessor anagramBatchProcessor = new AnagramBatchProcessor();

    @Test
    void shouldProcessEmptyList() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(Collections.emptyList());
        assertThat(anagrams).isEqualTo(Collections.emptyList());
    }

    @Test
    void shouldProcessSingleEntryList() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test"));
        assertThat(anagrams).isEqualTo(List.of("test"));
    }

    @Test
    void shouldProcessMultipleEntryListWithNoAnagrams() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test", "best", "rest"));
        assertThat(anagrams).hasSize(3);
        assertThat(anagrams).contains("test");
        assertThat(anagrams).contains("best");
        assertThat(anagrams).contains("rest");
    }

    @Test
    void shouldProcessMultipleEntryListWithAllAnagrams() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test", "stte", "ttse"));
        assertThat(anagrams).isEqualTo(List.of("test,stte,ttse"));
    }

    @Test
    void shouldProcessMultipleEntryListWithAMix() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test", "stte", "ttse", "rest", "bbae", "aebb"));
        assertThat(anagrams).hasSize(3);
        assertThat(anagrams).contains("rest");
        assertThat(anagrams).contains("test,stte,ttse");
        assertThat(anagrams).contains("bbae,aebb");
    }

    //todo this test should go if the class enforces a List where all strings are the same length
    @Test
    void shouldProcessMultipleEntryListWithAMixedLengthList() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test", "stte", "ttse", "rest", "bbae", "aebb", "tester", "mixed", "ximed", "demix"));
        assertThat(anagrams).hasSize(5);
        assertThat(anagrams).contains("rest");
        assertThat(anagrams).contains("test,stte,ttse");
        assertThat(anagrams).contains("bbae,aebb");
        assertThat(anagrams).contains("tester");
        assertThat(anagrams).contains("mixed,ximed,demix");
    }

    // Need to check the requirements here. Should duplicate strings be included?
    // Could switch to a Set if we only want the unique ones
    @Test
    void shouldIncludeDuplicateStrings() {
        List<String> anagrams = anagramBatchProcessor.findAnagrams(List.of("test", "stte", "test", "ttse", "test"));
        assertThat(anagrams).isEqualTo(List.of("test,stte,test,ttse,test"));
    }
}