package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ThankYouPage extends BasePage {
    private final SelenideElement root = $("#StepBodyId");
    private final SelenideElement title = root.$("h4");

    public ThankYouPage shouldBeVisible() {
        root.shouldBe(visible);
        title.shouldBe(visible).shouldHave(partialText("Thank you"));
        return this;
    }

    public ThankYouPage shouldShowConfirmationFor(String userName, String contractorName) {
        shouldBeVisible();
        title.shouldHave(text(String.format("Thank you %s, your contractor %s will call soon!", userName, contractorName)));
        return this;
    }
}
