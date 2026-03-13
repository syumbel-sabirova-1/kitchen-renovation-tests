package pages.questionnaire.steps;

import com.codeborne.selenide.SelenideElement;
import pages.ThankYouPage;
import pages.components.BaseStep;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PhoneNumberStep extends BaseStep<PhoneNumberStep> {
    public static final String TITLE = "What is your phone number?";

    private final SelenideElement phoneNumberField = $("[data-autotest-input-phonenumber-tel]");
    private final SelenideElement submitRequestButton = $("[data-autotest-button-submit-submit-my-request]");
    private final SelenideElement confirmPhoneNumberButton = $("[data-autotest-button-submit-phone-number-is-correct]");

    public PhoneNumberStep() {
        super($("[data-tracking='form-step-pii2']").$("#StepBodyId"));
    }

    public PhoneNumberStep shouldAskForPhoneNumber() {
        return shouldHaveTitle(TITLE);
    }

    public PhoneNumberStep fillPhoneNumber(String phoneNumber) {
        phoneNumberField.shouldBe(visible).setValue(phoneNumber);
        return this;
    }

    public PhoneNumberStep clickSubmit() {
        submitRequestButton.shouldBe(visible).click();
        confirmPhoneNumberButton.shouldBe(visible);
        return this;
    }

    public ThankYouPage confirmPhoneNumber() {
        confirmPhoneNumberButton.shouldBe(visible).click();
        return new ThankYouPage().shouldBeVisible();
    }
}
