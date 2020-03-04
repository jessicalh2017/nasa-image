# Nasa Image Project

This project is a coding exercise using NASA API (https://api.nasa.gov/api.html ) to build an application that calls the Mars Rover API and selects a picture on a given day. 
The images are downloaded and stored locally. 

This is a single artifact combined Spring boot backend server with React front end UI.
Backend server serves a set of REST APIs that allow a user to retrieve images captured by the Mars rovers Opportunity, Curiosity, and Spirit via the NASA open API for the pre-configured set of dates.  

When an image is requested, the backend server will check if the image has already been cached, if not, it will request the image from NASA, save it to the local repository, and then send that image to the consumer.  
${rover name}_${photo id} is used as the image file name. Repository is /tmp directory.

Frontend is a bare-bones single page web application written with react/redux/router to present rovers, photos information.
### Prerequisites

 * Java 8 or later
 * Gradle 5.2.1
 * Node.js v12.16.1
 * npm 6.13.4
 * Webpack 4.42.0

### Run with gradlew
gradlew bootRun
You can access the application with http://localhost:8080

### Docker
Commands: 
gradlew clean build
docker build -t nasa-image .
docker run -p 9090:8080 nasa-image
You can access the application with http://localhost:9090

###Known Issues and Improvement
This application is written under time constraint. I would like to continue improvements:

###Backend
    Currently I assume all images are in jpeg format. In the future, I should add other image formats support.
    Use tmp as directory for image repository as it is convenient. Later should find a better location and make the path configurable.
    The image repository needs mechanism to control the size, handle deletion, etc.
    Get Photos API does not support pagination.
    Needs better unit test coverage.

###Frontend
    No unit tests, should have used test framework like Jest or Mocha.
    Needs better UI/UX styling.
    Needs to support Pagination.
    Needs better error handling.
    Needs to better display different sizes of images.
    
    


