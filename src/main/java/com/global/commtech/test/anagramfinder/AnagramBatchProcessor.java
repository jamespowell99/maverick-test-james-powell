package com.global.commtech.test.anagramfinder;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AnagramBatchProcessor {
    //todo could probably simplify this now that we know the lines param will be a list of strings with the same length
    /**
     * Process a list of strings and group them by those that are anagrams of each other
     * @param lines - list of strings to process
     * @return A list of strings, each string is a collection of anagrams in a comma delimited list
     */
    public List<String> findAnagrams(List<String> lines) {
        // Using a Map with key of type String to group all the anagrams together.
        // Relying on sorting each string alphabetically to find anagrams
        Map<String, List<String>> anagrams = new HashMap<>();

        lines.forEach(line->{
            char[] charArray = line.toCharArray();
            Arrays.sort(charArray);
            String sorted = new String(charArray);
            if (anagrams.containsKey(sorted)) {
                // This string is already in the map as an anagram so add this string to it
                List<String> mapEntry = anagrams.get(sorted);
                mapEntry.add(line);
                anagrams.put(sorted, mapEntry);
            } else {
                // This string is not in the anagram list so store it with just this line
                ArrayList<String> strings = new ArrayList<>();
                strings.add(line);
                anagrams.put(sorted, strings);
            }
        });

        // Now convert each map entry into a comma delimited list of strings
        return anagrams.values().stream()
                .map(strings -> String.join(",", strings))
                .collect(Collectors.toList());
    }
}
