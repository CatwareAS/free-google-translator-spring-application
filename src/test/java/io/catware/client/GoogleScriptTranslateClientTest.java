package io.catware.client;

import io.catware.FreeTranslateApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreeTranslateApplication.class)
public class GoogleScriptTranslateClientTest {

    @Autowired
    private GoogleScriptTranslateClient googleScriptTranslateClient;

    @Test
    public void translateFromEnglishToNorwigianWithAutoDetectingSourceLanguageTest() {
        String translatedText = googleScriptTranslateClient.translateText("Hello world!", "no").block();

        Assert.assertEquals("Hei Verden!", translatedText);
    }

    @Test
    public void translateFromEnglishToGermanTest() {
        String translatedText = googleScriptTranslateClient.translateText("Hello world!",  "en", "de").block();

        Assert.assertEquals("Hallo Welt!", translatedText);
    }

    @Test
    public void translateFromSpanishToEnglishTest() {
        String translatedText = googleScriptTranslateClient.translateText("¿Cómo estás?",  "en").block();

        Assert.assertEquals("How are you?", translatedText);
    }

}
