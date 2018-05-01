package com.example.admin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import Base.ApiClient;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.action.ViewActions.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by admin on 5/1/18.
 */

public class UserRegisterActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<UserRegisterActivity> userRegisterActivityRule =
            new ActivityTestRule<>(UserRegisterActivity.class, true, true);
    private MockWebServer server;


    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRegisterUser() {
        Intent intent = new Intent();
        userRegisterActivityRule.launchActivity(intent);

        Matcher<View> edtPhoneNumber = withHint(getHintTextOfRequiredField(R.string.phone_number));
        Matcher<View> edtFullName = withHint(getHintTextOfRequiredField(R.string.full_name));
        Matcher<View> edtIDNumber = withHint(R.string.national_id_number);
        Matcher<View> edtAdress = withHint(R.string.address);
        Matcher<View> edtMonthlyIncome = withHint(getHintTextOfRequiredField(R.string.monthly_income));

        onView(edtPhoneNumber).perform(typeText("093394427"));
        onView(edtIDNumber).perform(typeText("12345"));
        onView(edtMonthlyIncome).perform(typeText("2000"));
        onView(edtPhoneNumber).check(matches(hasErrorText("Số điện thoại không hợp lệ")));
        onView(edtIDNumber).check(matches(hasErrorText("CMND không hợp lệ")));
        onView(edtMonthlyIncome).check(matches(hasErrorText("Thu nhập phải lớn hơn 3 triệu")));

        onView(edtPhoneNumber).perform(clearText());
        onView(edtIDNumber).perform(clearText());
        onView(edtMonthlyIncome).perform(clearText());

        onView(edtAdress).perform(typeText("An"), closeSoftKeyboard());
        onView(withText("An Giang"))
                .inRoot(withDecorView(not(is(userRegisterActivityRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));

        onView(withText("An Giang"))
                .inRoot(withDecorView(not(is(userRegisterActivityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());

        onView(edtAdress)
                .check(matches(withText("An Giang")));

        onView(edtPhoneNumber).perform(typeText("0933933279"));
        onView(edtIDNumber).perform(typeText("211111178"));
        onView(edtMonthlyIncome).perform(typeText("4000000"));

        onView(withText(R.string.click_here_to_register)).check(matches(isClickable()));
        onView(withText(R.string.click_here_to_register)).perform(click());
        onView(withText("Có thông tin không hợp lệ")).inRoot(withDecorView(not(is(userRegisterActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(edtFullName).perform(typeText("Linh"));
        onView(withText(R.string.click_here_to_register)).perform(click());
    }

    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

    private String getHintTextOfRequiredField(int id) {
        String hint = getResourceString(id);
        return hint + " (*)";
    }
}
