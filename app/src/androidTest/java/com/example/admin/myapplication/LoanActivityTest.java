package com.example.admin.myapplication;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Matcher;

import Base.ApiClient;
import Utils.AssetUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by admin on 5/1/18.
 */

public class LoanActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<LoanActivity> loanActivityRule =
            new ActivityTestRule<>(LoanActivity.class, true, false);
    private MockWebServer server;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        ApiClient.BASE_URL = server.url("/").toString();
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    public void testLoanActivity() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(AssetUtils.loadAssets(getInstrumentation().getContext().getAssets(), "loan_info_test.json")));
        Intent intent = new Intent();
        loanActivityRule.launchActivity(intent);

        onView(withText("₫30,000,000/₫100,000,000")).check(matches(isDisplayed()));
        onView(withText("6/18")).check(matches(isDisplayed()));
        onView(withText("19.99%")).check(matches(isDisplayed()));
        onView(withText("Vietcombank")).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegister)).check(matches(isClickable()));
        server.enqueue(new MockResponse());
        Intents.init();
        onView(withId(R.id.btnRegister)).perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(UserRegisterActivity.class.getName()));
        Intents.release();
    }
}
