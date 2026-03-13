package pages.components;

import com.codeborne.selenide.SelenideElement;

public class MultiChoiceStep<T extends MultiChoiceStep<T>> extends BaseStep<T> {
    protected MultiChoiceStep(SelenideElement root, String... optionAttributes) {
        super(root, optionAttributes);
    }

    @SuppressWarnings("unchecked")
    protected T selectOptions(String... optionAttributes) {
        for (String optionAttribute : optionAttributes) {
            optionLabel(optionAttribute).click();
        }
        return (T) this;
    }
}
