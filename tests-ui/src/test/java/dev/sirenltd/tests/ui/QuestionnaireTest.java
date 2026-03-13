package dev.sirenltd.tests.ui;

import dev.sirenltd.UITestBase;
import io.qameta.allure.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ThankYouPage;
import pages.questionnaire.steps.KitchenElementsStep;
import pages.questionnaire.steps.KitchenSizeStep;

import static pages.questionnaire.steps.HomeTypeStep.MANUFACTURED_HOME_WARNING_TEXT;
import static pages.questionnaire.steps.HomeownerStep.AUTHORIZATION_WARNING_TEXT;
import static pages.questionnaire.steps.KitchenElementsStep.CABINET_WARNING_TEXT;
import static pages.questionnaire.steps.KitchenUpdateStep.FEW_CABINETS_WARNING_TEXT;
import static pages.questionnaire.steps.TypeOfPropertyStep.COMMERCIAL_WARNING_TEXT;

public class QuestionnaireTest extends UITestBase {
    private static final String VALID_ZIP_CODE = "10001";
    private static final String INVALID_ZIP_CODE = "10000";
    private static final String CONTRACTOR = "QA Company";

    private String fullName;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;

    @BeforeMethod
    void init() {
        openLanding();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        fullName = firstName + " " + lastName;
        email = faker.internet().emailAddress();
        phone = faker.regexify("[2-9][0-9]{9}");
    }

    @Issue("JIRA-ISSUE-1234, /thank-you page uses default user-name")
    @Test(description = "Full questionnaire flow from landing to thank you page")
    public void fullQuestionnaireFlowFromLandingToThankYouTest() {
        var kitchenSizeStep = completeStandardQuestionnaireUntilKitchenSize();
        var thankYouPage = completeQuestionnaireFromKitchenSize(kitchenSizeStep);

        thankYouPage.shouldShowConfirmationFor(firstName, CONTRACTOR);
    }

    @Test(description = "Verify warnings are displayed")
    public void questionnaireFlowWithWarningsTest() {
        var kitchenSizeStep = completeWarningFlowUntilKitchenSize();
        var thankYouPage = completeQuestionnaireFromKitchenSize(kitchenSizeStep);

        thankYouPage.shouldBeVisible();
    }

    @Test(description = "Verify no matching contractors found page")
    public void questionnaireFlowWithNoMatchingContractorsFoundTest() {
        var landingPage = new LandingPage().shouldBeVisible();
        var noMatchingContractorsPage = landingPage
                .inputZipCode(INVALID_ZIP_CODE)
                .clickGetEstimateExpectingNoMatchingContractors();

        noMatchingContractorsPage.shouldBeVisible();
        noMatchingContractorsPage
                .fillEmail(email)
                .clickSubmit()
                .shouldHaveSuccessMessage("Thank you for your interest, we will contact you when our service becomes available in your area!");

        noMatchingContractorsPage.clickTryAnotherZip().shouldBeVisible();
    }

    private KitchenElementsStep startQuestionnaire(String zipCode) {
        return new LandingPage()
                .shouldBeVisible()
                .inputZipCode(zipCode)
                .clickGetEstimate();
    }

    private KitchenSizeStep completeStandardQuestionnaireUntilKitchenSize() {
        var kitchenElementsStep = startQuestionnaire(VALID_ZIP_CODE)
                .shouldBeVisible()
                .shouldAskWhichKitchenElementsNeedUpdating()
                .shouldHaveOptionsCount(8);

        var kitchenUpdateStep = kitchenElementsStep
                .selectCabinets()
                .clickNext()
                .shouldAskAboutCabinetUpdates()
                .shouldHaveOptionsCount(3);

        var propertyTypeStep = kitchenUpdateStep
                .selectReplaceAll()
                .clickNext()
                .shouldAskForPropertyType()
                .shouldHaveOptionsCount(3);

        var homeTypeStep = propertyTypeStep
                .selectSingleFamily()
                .clickNext()
                .shouldAskAboutHomeType()
                .shouldHaveOptionsCount(2);

        var homeownerStep = homeTypeStep
                .selectNo()
                .clickNext()
                .shouldAskIfUserCanAuthorizeChanges()
                .shouldHaveOptionsCount(2);

        return homeownerStep
                .selectYes()
                .clickNext();
    }

    private KitchenSizeStep completeWarningFlowUntilKitchenSize() {
        var kitchenElementsStep = startQuestionnaire(VALID_ZIP_CODE)
                .shouldBeVisible()
                .shouldAskWhichKitchenElementsNeedUpdating();

        var kitchenUpdateStep = kitchenElementsStep
                .selectCountertops()
                .selectFlooring()
                .selectLighting()
                .clickNextExpectingWarning()
                .shouldHaveText(CABINET_WARNING_TEXT)
                .clickYes()
                .shouldAskAboutCabinetUpdates();

        var propertyTypeStep = kitchenUpdateStep
                .selectReplaceFew()
                .clickNextExpectingWarning()
                .shouldHaveText(FEW_CABINETS_WARNING_TEXT)
                .clickYes()
                .shouldAskForPropertyType();

        var homeTypeStep = propertyTypeStep
                .selectCommercial()
                .clickNextExpectingWarning()
                .shouldHaveText(COMMERCIAL_WARNING_TEXT)
                .clickYes()
                .shouldAskAboutHomeType();

        var homeownerStep = homeTypeStep
                .selectYes()
                .clickNextExpectingWarning()
                .shouldHaveText(MANUFACTURED_HOME_WARNING_TEXT)
                .clickYes()
                .shouldAskIfUserCanAuthorizeChanges();

        return homeownerStep
                .selectNo()
                .clickNextExpectingWarning()
                .shouldHaveText(AUTHORIZATION_WARNING_TEXT)
                .clickYes();
    }

    private ThankYouPage completeQuestionnaireFromKitchenSize(KitchenSizeStep kitchenSizeStep) {
        var budgetStep = kitchenSizeStep
                .shouldAskForKitchenSize()
                .inputSize("150")
                .clickNext()
                .shouldAskForBudget();

        var contactDetailsStep = budgetStep
                .choose5to10()
                .clickNext()
                .shouldAskForContactDetails();

        var phoneNumberStep = contactDetailsStep
                .fillFullName(fullName)
                .fillEmail(email)
                .clickNext()
                .shouldAskForPhoneNumber();

        return phoneNumberStep
                .fillPhoneNumber(phone)
                .clickSubmit()
                .confirmPhoneNumber();
    }
}
