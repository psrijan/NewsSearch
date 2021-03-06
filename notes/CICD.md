## Need?
CICD means continuous integration and deployment. It’s a pipleline for a code to be delivered to the client, quickly and safely.

## Process?
1. **Commit To central Repo:**   The first stage of CICD involves committing a code to a central repository. This ensures that all changes are available in a central location, which additionally ensures that tests, unit tests or even DDT with huge test cases can be ran to ensure product quality. If the process fails, (ie build failure, test case fails) here then, the developer working on the issue is notified through a **issue tracking software such as JIRA**.
1. **Code Scans:** This can be anywhere from running test-cases, DDT framework tests, and code scans to ensure that code adheres to coding standards (DRY, SOLID, etc)
1. **Generate Builds and Test Reports**: After code scans are completed, and the code passess the standard of tests, build is created for CD. In addition, test results are also generated to show what parts of the code failed, had bugs, failed standards.
1. **Automate Deployment:** After the builds have been generated, then this build is passed on to the client side or to deployment services.

This entire process is called CICD.

How to Setup?
What happens each step?

