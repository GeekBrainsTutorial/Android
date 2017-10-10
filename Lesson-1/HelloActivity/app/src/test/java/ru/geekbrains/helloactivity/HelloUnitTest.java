package ru.geekbrains.helloactivity;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelloUnitTest {

    GreetingStrings greetingStrings = new GreetingStrings() {
        @Override
        public String getHelloer() { return "GeekBrains"; }
        @Override
        public String getMorning() { return "Good morning"; }
        @Override
        public String getAfternoon() { return "Good afternoon"; }
        @Override
        public String getEvening() { return "Good evening"; }
        @Override
        public String getNight() { return "Good night"; }
    };

    @Test
    public void BuilderGreetingPhrase_Morning_Test() throws Exception {
        BuilderGreetingPhrase builderGreetingPhrase = new BuilderGreetingPhrase(greetingStrings, 9);
        String expected = "Good morning GeekBrains!";
        String actual = builderGreetingPhrase.get();
        assertEquals(expected, actual);
    }

    @Test
    public void BuilderGreetingPhrase_Afternoon_Test() throws Exception {
        BuilderGreetingPhrase builderGreetingPhrase = new BuilderGreetingPhrase(greetingStrings, 12);
        String expected = "Good afternoon GeekBrains!";
        String actual = builderGreetingPhrase.get();
        assertEquals(expected, actual);
    }

    @Test
    public void BuilderGreetingPhrase_Evening_Test() throws Exception {
        BuilderGreetingPhrase builderGreetingPhrase = new BuilderGreetingPhrase(greetingStrings, 18);
        String expected = "Good evening GeekBrains!";
        String actual = builderGreetingPhrase.get();
        assertEquals(expected, actual);
    }

    @Test
    public void BuilderGreetingPhrase_Night_Test() throws Exception {
        BuilderGreetingPhrase builderGreetingPhrase = new BuilderGreetingPhrase(greetingStrings, 22);
        String expected = "Good night GeekBrains!";
        String actual = builderGreetingPhrase.get();
        assertEquals(expected, actual);
    }
}
