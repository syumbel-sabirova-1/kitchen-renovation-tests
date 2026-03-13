package pages.components;

import com.codeborne.selenide.SelenideElement;

public class SingleChoiceStep<T extends SingleChoiceStep<T>> extends BaseStep<T> {
    protected SingleChoiceStep(SelenideElement root, String... optionAttributes) {
        super(root, optionAttributes);
    }

    @SuppressWarnings("unchecked")
    public T selectOption(String optionAttribute) {
        optionLabel(optionAttribute).click();
        return (T) this;
    }
}
