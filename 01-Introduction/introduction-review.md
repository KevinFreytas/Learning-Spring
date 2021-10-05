Review
Well done! You’ve successfully used the Spring framework to handle HTTP requests (GET, POST, PUT, and DELETE), streamlining data transfer between the client application and a web server.

In this lesson, we covered two different ways to make GET requests, an HTTP method that retrieves data from the server and sends it back to the application.

Using the browser: type the URL https://example.com/abc into the address bar.

Using curl: type curl https://example.com/abc into your local command line

In addition, we made POST requests, an HTTP method that adds new data to the web application. We can make POST requests using either curl or the browser.

Using the browser: submit an HTML form that is setup to hit the https://www.example.com/abc endpoint.

Using curl: type curl -X POST -d "{\"key1\":\"value1\", \"key2\":\"value2\"}" -H "Content-Type: application/json" https://example.com/abc into the command line.

Spring’s ability to quickly and accurately respond to HTTP requests allows clients to easily manage, add, and delete data from their web applications. This functionality becomes especially useful when a client application is dealing with large amounts of information (e.g. when a large enterprise application is receiving millions of requests within a small time frame). By facilitating efficient interaction between client and server, the Spring framework enables us developers to easily build complex RESTful web apps with minimal effort and configuration.