package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.helpers.FeedbackType;
import com.trailblazers.freewheelers.helpers.SyntaxSugar;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NetPromoterScoreTest extends UserJourneyBase {

    private final String adminUser = "NPSAdmin123";
    private final String password = "Password1!";
    private final String username = "Promoter";
    private final String frame = "Bike Frame";
    private final long someAccountId = 12L;

    @Before
    public void setUp() {
        long quantity = 10;
        admin
                .there_is_an_admin(adminUser, password)
                .there_is_a_frame(frame, quantity)
                .there_is_a_user(username, password);
    }

    @Ignore
    @Test
    public void testSurveyPopUp() throws InterruptedException {
        user
                .logs_in_with(SyntaxSugar.emailFor(username), password)
                .visits_home_page()
                .add_to_cart_and_check_out(frame)
                .entersPaymentDetails("Visa", "4111111111111111", "534", "11", "2020")
                .submits_payment_details()
                .waits_for_survey_popup();

//        npsSurveyForm.submitFeedback(FeedbackType.Positive, "Some Feedback");
//        assertTrue(npsSurveyForm.thankYouMessageExists());
//        npsSurveyForm.closeWindow();

        user.is_logged_out();
    }

    @Test
    public void testNPSReportOption() {


        //unlogged in user
        user
                .is_logged_out();
        screen
                .should_not_contain_nps_report_link_in_header();

        //unauthorised user
        user.logs_in_with(username, password);
        screen
                .should_not_contain_nps_report_link_in_header();


    }

    @Test
    public void shouldShowNpsReport() throws Exception {
        admin
                .there_are_no_survey_entries()
                .there_is_a_survey_entry_for(someAccountId, 10, "Some Feedback")
                .there_is_a_survey_entry_for(someAccountId, 10, "Some Positive Feedback")
                .there_is_a_survey_entry_for(someAccountId, 7, "Some Passive Feedback")
                .there_is_a_survey_entry_for(someAccountId, 0, "Some Negative Feedback");
        //show survey report
        user
                .is_logged_out()
                .logs_in_with(SyntaxSugar.emailFor(adminUser), password)
                .visits_nps_report_page_by_link();

        assertTrue(npsReportPage.totalResponsesCorrect("4"));
        assertTrue(npsReportPage.npsScoreCorrect("25"));
        assertTrue(npsReportPage.percentagePromotersCorrect("50"));
        assertTrue(npsReportPage.percentageDetractorsCorrect("25"));
        assertTrue(npsReportPage.percentagePassivesCorrect("25"));
        assertTrue(npsReportPage.containsPromoterFeedbackComment("Some Positive Feedback"));
        assertTrue(npsReportPage.containsDetractorFeedbackComment("Some Negative Feedback"));
        assertTrue(npsReportPage.containsPassiveFeedbackComment("Some Passive Feedback"));
    }

    @After
    public void tearDown() {
        admin
                .there_are_no_survey_entries()
                .there_is_no_item(frame)
                .there_is_no_account_for(adminUser)
                .there_is_no_account_for(username);
    }
}
