Alex Cleverley's Github Repo for the COMP3000 final year project.

# Go Phish

Go Phish is a mobile game application that focuses on phishing email scams and the signs to look out for when viewing them. The app is targeted towards self-employed people and people who want to be more cyber-aware, providing a fun ‘brain-training’ exercise to verse themselves in signs of a potential phishing email. Go phish is an ‘On-the-Go’ mobile app that allows the user to freshen their memory of potential phishing signs and highlights what to look out for when viewing one.

# Frontend

The front end of the project is an Android mobile application. The application is split into two modes: the tutorial mode and the play mode.

### Android Mobile Application

- Tutorial mode: Reccomended for 1st time users, provides an example level of how the app is structured.
- Play mode: User plays a level, examining a potential phising email for a limited time to find and pick out the relevant signs of a phishing email. Returns a score based on number of correct signs. 

# Backend

In tandem with the Android mobile application, the backend uses a database to store the potential phishing email examples as well as a RESTful Web API to host and present the database information to the Frontend.

### API

- The API gets the phishing record information and hosts it on a web source for the Mobile Application to access.

### Database

- The database holds phishing example records, including: a record ID, a Record image and Record Attributes.

# Links

Alex Cleverley's Gantt Chart: https://app.teamgantt.com/projects/gantt?ids=3258035
