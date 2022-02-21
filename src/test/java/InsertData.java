import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;

public class InsertData {

    SelenideElement
            firstNameField = $("#firstName"),
            lastNameField = $("#lastName"),
            userEmailField = $("#userEmail"),
            userNumberField = $("#userNumber"),
            currentAddressField = $("#currentAddress"),
            genderField = $("#genterWrapper"),
            dateOfBirthField = $("#dateOfBirthInput"),
            monthOfBirthField = $(".react-datepicker__month-select"),
            yearOfBirthField = $(".react-datepicker__year-select"),
            subjectsField = $("#subjectsInput"),
            hobbiesField = $("#hobbiesWrapper"),
            stateField = $("#react-select-3-input"),
            cityField = $("#react-select-4-input"),
            pictureField = $("#uploadPicture");


    @Step ("Запускаем браузер с необходимым разрешением на необходимую страницу")
    public InsertData openBrowser () {
        open("/automation-practice-form");
        return this;
    }

    @Step ("назначаем все значения для всех ячеек")
    public InsertData setAllData (String fName, String lName, String email,String gender, String number, String address,
                                  String dayOfBirth, String monthOfBirth, String yearOfBirth, String subject,
                                  String hobbies, String state, String city) {
        firstNameField.setValue(fName);
        lastNameField.setValue(lName);
        userEmailField.setValue(email);
        genderField.$(byText(gender)).click();
        userNumberField.setValue(number);
        currentAddressField.setValue(address);
        dateOfBirthField.click();
        monthOfBirthField.selectOption(monthOfBirth);
        yearOfBirthField.selectOption(yearOfBirth);
        String dob = format(".react-datepicker__day--0%s:not(.react-datepicker__day--outside-month)", dayOfBirth);
        $(dob).click();
        subjectsField.setValue(subject).pressEnter();
        hobbiesField.$(byText(hobbies)).click();
        stateField.setValue(state).pressEnter();
        cityField.setValue(city).pressEnter();
        return this;
    }

    @Step ("Подтверждение выбранных значений")
    public InsertData submit () {
        $("#submit").scrollTo().click();
        return this;
    }

    @Step ("Проверка правильного расположения выбранных значений")
    public  InsertData validation (String fName, String lName, String email,String gender, String number, String address,
                             String dayOfBirth, String monthOfBirth, String yearOfBirth, String subject,
                             String hobbies, String state, String city) {
        $$(".modal-content td").shouldHave(containExactTextsCaseSensitive(fName + " " + lName,email,gender,
                number,dayOfBirth + " " + monthOfBirth + "," + yearOfBirth,subject,hobbies,address,state + " " + city));
        return this;
    }

}



