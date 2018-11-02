## News Search

#### Overview
Application Features. 
1. **Enable Service:** I have a service written in backend that a user can enable and disable using a REST endpoint. This service calls an external news API and saves the data to mongodb document store. 
1. **Fetch News From Database:** Another API provides functionality to fetch news from the mongodb database and display it in the UI. 
1. **Fetch News On Author**: There is also an API to fetch News based on author name, however, this can't be called through UI. One can however call it through the browser at  https:/host:port/author/{authorname}
1. **UI:** UI part is made in angular, and has functions to toggle the service (start stop service). The second part displays the documents stored in mongodb in the UI. The UI filters the repeated news Item through an object map called newsMapper available in NewsBoxComponent.   


## Points
#### Thought process: 
For this project, it was important to decide and build a backend before the client side. The reason for this is deciding on database storage, APIs and services make it clearer to implement the client side logic.
 
 While initially I had decided to use elastic search, it's TransactionClient in spring wasn't consistent with the version of elasticsearch I installed.

I have used the inbuilt Spring Repository to query documents from mongodb and sorting is done in the UI itself through a class.

**How am I sorting redundant data?** 
As I had the option to sort through redundant data either in database or in UI, I choose the latter. I'm using a class called newsMapper to do so. This object is a keyvalue pair, where key is the unique id used to identify individual news. A news that is repeated will have same keys. The repeated news I am storing as array of object inside the first News object. The array name is childs.

#### Limitations:
1. The UI loads the data on the main thread, and for huge number of data will definitely hang the UI. Solution is to populated the view in a background thread. 
1. The status displayed may be incorrect ifone loads the view after toggling the service. Solution: Call the API and get status at the start of the view load.
1. Instead of printing the error in console, it's a good practice to log all the errors through a logging framework such as Log4j. This can be done by configuring the log4j in application.properties and using the Log4j class to log data into a file.



This entire process is called CICD.

## How to Setup Project: 
1. Import all java dependencies through maven. 
1. Go to src/ui/my-sassy-app and install all Javascript/typescript dependency using node package manager. `npm install`
1. Need to have mongodb database installed. Start mongodb service using. `sudo service mongod start`

#### Final Result: 

[![IMAGE ALT TEXT HERE](http://img.youtube.com/vi/YOUTUBE_VIDEO_ID_HERE/0.jpg)](http://www.youtube.com/watch?v=cqwa3O-IMss&feature=youtu.be)

## Part 2: Continuous Integration | Deployment Steps:

**Need:** CICD means continuous integration and deployment. It’s a pipleline for a code to be delivered to the client, quickly and safely.

1. **Commit To central Repo:**   The first stage of CICD involves committing a code to a central repository. This ensures that all changes are available in a central location, which additionally ensures that tests, unit tests or even DDT with huge test cases can be ran to ensure product quality. If the process fails, (ie build failure, test case fails) here then, the developer working on the issue is notified through a **issue tracking software such as JIRA**.
1. **Code Scans:** This can be anywhere from running test-cases, DDT framework tests, and code scans to ensure that code adheres to coding standards (DRY, SOLID, etc)
1. **Generate Builds and Test Reports**: After code scans are completed, and the code passess the standard of tests, build is created for CD. In addition, test results are also generated to show what parts of the code failed, had bugs, failed standards.
1. **Automate Deployment:** After the builds have been generated, then this build is passed on to the client side or to deployment services.


