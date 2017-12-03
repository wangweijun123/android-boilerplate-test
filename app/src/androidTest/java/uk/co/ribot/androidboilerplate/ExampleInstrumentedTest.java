package uk.co.ribot.androidboilerplate;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import uk.co.ribot.androidboilerplate.ui.main.MainActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Intent intent = new Intent(appContext, MainActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);

//        assertEquals("uk.co.ribot.androidboilerplate", appContext.getPackageName());
    }
}
