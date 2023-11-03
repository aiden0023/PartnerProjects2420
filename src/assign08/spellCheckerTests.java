package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class spellCheckerTests {

    SpellChecker checker = new SpellChecker(new File("src/assign08/dictionary.txt"));

    @BeforeEach
    public void setup() {

    }

    @Test
    public void addToDictionaryTest() {

    }

    @Test
    public void spellCheckTest() {
        List<String> temp = checker.spellCheck(new File("src/assign08/test.txt"));
        assertEquals(2, temp.size());
        //checker.
    }
}
