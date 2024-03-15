# Java Recommendation System for Streaming App

This project implements a Recommendation System for a Streaming App, focusing on music, podcasts, and audiobooks. The system incorporates user behavior and stream creator data to provide personalized recommendations. Input is received through a series of commands and processed accordingly.

Functionality
  The system processes the following commands:

ADD: Adds a new stream to the system.

LIST: Lists streams based on specified criteria.

LISTEN: Records when a user listens to a stream.

DELETE: Deletes a stream from the system.

RECOMMEND: Provides recommendations based on user preferences and behavior.

SURPRISE: Provides a random surprise recommendation.

Implementation Details
  Language: Java

  Design Patterns:

Singleton: Utilized for the AccountManager to ensure that only one instance of this class exists throughout the application, providing a central point for managing user accounts and streamers.

Iterator: Possibly used internally for iterating through collections like ArrayList, though not explicitly mentioned in the code.

Proxy: Not explicitly mentioned in the code, but could potentially be used to control access to resources such as files or network connections.

Command: Implemented for encapsulating method invocation, allowing for the parameterization of clients with queues, requests, or operations. Commands 
(AddStreamCommand, DeleteStreamCommand, etc.) encapsulate operations on streams, enabling the decoupling of the invoker (CommandCaller) from the receiver (stream operations).

Object-Oriented Programming (OOP): Designed with OOP principles to ensure maintainability and extensibility.

Input Handling: Accepts input through a series of commands stored and processed by the system.

Usage:

Ensure the necessary input files are available in the specified directory.
Execute the program, providing the necessary command line arguments.
Enter commands as needed to interact with the system.
Note
This project was implemented in Java due to its support for Object-Oriented Programming (OOP), providing advantages in structuring the recommendation system. The utilization of design patterns ensures a compact and robust implementation.
