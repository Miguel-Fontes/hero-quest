package br.com.miguelmf.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stringfy")
class StringfyTest {

    private static TestSubject testSubject;

    @BeforeAll
    static void setup() {
        testSubject = TestSubject.newInstance();
    }

    @Test
    @DisplayName("should stringfy an object with curly braces notation")
    void shouldStringfyToCurlyBraces() {
        String stringfiedTestSubject = Stringfy.curly(testSubject);

        assertEquals(
            "TestSubject { privateAttr: 5, finalAttr: Test, staticFinal: 2, publicAttr: 9.9 }",
            stringfiedTestSubject
        );
    }

    private static class TestSubject {

        private Long privateAttr;
        private final String finalAttr;
        private static final int staticFinal = 2;
        public Double publicAttr;

        private TestSubject() {
            this.privateAttr = 5L;
            this.finalAttr = "Test";
            this.publicAttr = 9.9d;
        }

        public static TestSubject newInstance() {
            return new TestSubject();
        }

    }

}