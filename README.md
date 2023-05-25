# Anagram Finder
A simple command line utility for finding anagrams in a specified file

## Software required to run this
* Java 17

## Building and Running the tests
```
./gradlew clean build
```

## Running the program
```
./gradlew bootRun --args="example2.txt" 
```
where example2.txt is the text file that we want to search for anagrams

## Disclaimer
Timeboxed this to 1 hour. The result reflects this

## Implementation notes
- streams through the file and processes by batches of Strings the same length
- Relies on sorting each string to find the anagrams
- Uses a HashMap of String:List<String> where the key is the sorted string and the value is the list of anagrams found for this string
- Should have decent test coverage via the unit tests added (although I would like to add more)
- Added a couple of Spring Integration tests to cover some scenarios around the final batch being processed - this could be improved and pushed left into unit tests (see 'more time')

## Assumptions
- File is ordered by String lengths
- Duplicate strings are to be included (can convert to Set to avoid this)
- Strings that don't have any anagrams should still be returned (this appears to be the requirement from the example test)

## Things to clarify
- How should duplicate Strings be treated?

## Big O analysis
O(N)

## Next steps (given more time)
- More testing
  - edge cases etc - non alpha characters etc
- Performance testing
  - performance could potentially be improved by introducing parallelism. e.g. kick off a thread for each batch of strings of the same length etc - would introduce an OOM risk
- Large file testing - assess memory limits
- Try to break up AnagramCommandLineRunner to allow my code added there to be tested with a standard unit test rather than a Spring Integration test
