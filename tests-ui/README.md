# UI Tests
This module contains end-to-end UI tests for the kitchen renovation questionnaire flow.

## Running Tests
Run the full UI suite:
```bash
mvn test
```

Run a single test class:
```bash
mvn -Dtest=QuestionnaireTest test
```

Run a single test method:
```bash
mvn -Dtest=QuestionnaireTest#fullQuestionnaireFlowFromLandingToThankYouTest test
```

Run tests with an explicit TestNG suite file:
```bash
mvn -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml test
```

The default Maven Surefire configuration uses:
```text
src/test/resources/testng.xml
```

## Notes
- Make sure Chrome is installed locally before running the tests.
- Update `baseUrl` in `ui-test.properties` if you want to target a different environment.
