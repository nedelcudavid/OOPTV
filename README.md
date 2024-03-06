# OOPTV
## Copyright 2022 Nedelcu Andrei-David

### Introduction

OOPTV in a Java-based backend for a movie streaming platform. Input is handled through JSON files, ensuring easy integration and data parsing. OOPTV encompasses various functionalities, including movie management, user authentication, and personalized recommendations. With its intuitive design and efficient processing, OOPTV promises an easy-to-use and pleasent experience for all users.

### Project Structure

The project is organized into several packages, each serving a specific purpose:

- **input**: Contains classes responsible for reading input data, designed following the JSON input pattern for easy parsing. Notably, I enhanced the InputCredentials class with the subBalance method to facilitate balance deduction.

- **platform**: Houses the core features of the streaming platform:

  - *Movie*: Extends InputMovie and encapsulates movie attributes. Additionally, I implemented three additional methods (addNumLikes, addNumRating, and addRating) to aid data processing.
  
  - *RegisteredUser*: Extends InputUser and represents a registered user in the platform's database. It also features three additional methods to streamline data processing (subNumFreePremiumMovies, addTokensCount, and subTokensCount).
  
  - *Database*: A singleton class representing the platform's database, storing users, movies, and actions. It updates in real-time as the platform is used, with methods to find the index of the current user and movie in the database and to clear the database when necessary.
  
  - *Executable*: Another singleton class representing the program's execution, helping manage the current page, movie list, and user in real-time. This class includes methods to execute actions and populate the output array.

- **pages**: Divided into two sub-packages:

  - *unauthenticated_pages*: Contains singleton classes for unauthenticated pages:
  
    - *UnauthenticatedHomepage*: Handles the unauthenticated homepage, including the logout functionality.
    
    - *LoginPage*: Manages the login page.
    
    - *RegisterPage*: Manages the registration page.
    
  - *authenticated_pages*: Contains singleton classes for authenticated pages:
  
    - *AuthenticatedHomepage*: Manages the authenticated homepage.
    
    - *MoviesPage*: Displays available movies.
    
    - *SeeDetails*: Shows details of selected movies.
    
    - *Upgrades*: Handles account upgrades.

  All these classes extend the abstract Page class and share a common method, validPagesToVisitFromHere(), to define navigation options.

- **actions**: Contains possible actions on the platform:

  - *ChangePageActions*: A singleton class handling page change actions based on input commands.
  
  - *on_page_actions*: A sub-package for on-page actions:
  
    - *OnPageActions*: A singleton class executing on-page actions based on input commands.
    
    - *LoginAction*: Manages the login process, authenticating users and updating user data upon successful login.
    
    - *RegisterAction*: Handles user registration, checking for existing credentials and permitting registration if valid.
    
    - *SearchAction*: Filters current movies based on input strings.
    
    - *FilterAction*: Filters, sorts, and displays current movies based on various criteria.
    
    - *BuyTokensAction*: Allows users to purchase tokens if sufficient balance is available.
    
    - *BuyPremiumAccountAction*: Enables users to upgrade to a premium account if eligible.
    
    - *PurchaseAction*: Adds purchased movies to the user's library if sufficient tokens or premium credits are available.
    
    - *WatchAction*: Adds watched movies to the user's history if purchased.
    
    - *LikeAction*: Adds a like to the current movie and updates it accordingly.
    
    - *RateAction*: Adds a rating to the current movie and updates it accordingly.

  If any action is not permitted due to the current page or incomplete conditions, an error message is displayed in the output.

- **Main class**: Contains the readInput() and writeOutput() methods. The former reads data from input and constructs the database, while the latter displays the output array in corresponding files. The run() method from the Executable class is called between these static method calls in the main method, executing all actions and populating the output array.

### Advanced Functionalities

- **Subscribe Functionality**: Users can now subscribe to movie genres from the 'see details' page. If the movie contains the selected genre and is not already in their subscribedGenres list, it's added; otherwise, an error is displayed.

- **Movie Management**: Adding or deleting movies now involves the Database class:
  - The modifyDB() method handles the action type and execution.
  - The addMovie() method checks if the movie already exists and adds it using the Observer design pattern if not.
  - The deleteMovie() method checks if the movie exists and deletes it using the Observer design pattern if so.

- **Page Navigation**: The BackAction class now facilitates navigating back one page, extracting the previous page and movie from the stacks created for this purpose.

- **User Recommendations**: Premium users receive personalized movie recommendations at the end of actions. The SubscriptionAction class implements the giveRecommendation() method, collecting and sorting available genres, then recommending unseen movies based on likes and ratings.

### Design Patterns

In this stage, I incorporated several design patterns:

- **Factory Design Pattern**: Used to create notifications based on type (ADD/DELETE/Recommendation). Implemented in the -notifications_system- package.
  
- **Strategy Design Pattern**: Implemented for payment methods when purchasing movies. Found in the -payment_system- package.
  
- **Observer Design Pattern**: Facilitates database changes. Implemented in the -changing_database_system- package. 
