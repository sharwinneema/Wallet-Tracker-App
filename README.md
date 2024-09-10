# A2-PersistUI

This Android app accepts user clicks on dice button and updates its fields according to the dice value received. The App can retain data when user shifts from portrait to landscape orientation.

### a. Project Information

**Name of the Project** - Dice Game

**Name of the Students** -  Sharwin Neema , Ameya Kasture

**BITS IDs** - 2021A7PS1442G, 2021A7PS2058G

### b. Application Information

The Application takes user inputs in the form of clicks. According to the dice value received, the App updates its fields.

The Application however has the following limitations:-

* For large enough number of rolls, the texts in _text boxes_ will begin to overlap with each other or extend over screen boundaries, causing lack of clarity of the text present.
* Fast clicks on die button can cause lag or broken talk-backs.


However, these errors are highly improbable and don't threaten the functionality of the program in any way.

### c. Description On Completion Of The Tasks

**Task1: Implement the WalletActivity as discussed in class.**

* We first implemented WalletViewModel and Die6 classes so that they can be used by UI elements.
* We then defined UI elements in both portrait and landscape layouts.
* WalletActivity uses findViewById to access UI elements the UI to be updated by onclick event listener on the main button.
* We used setText to update the values in the view.
* We changed the description of UI elements using setContentDescription so that while using talkback one can know values of that particular text as well as what the text is for.


**Task2: A Toast is a mechanism in Android to provide quick feedback for a user action**

* We imported android.widget.Toast in WalletActivity to implement toasting.
* We then defined toasting() function for the toast "Congratulations! You have earned coins" and calling this function whenever dieValue() returns 6.
* Also defined "Oops! you have lost coins" when user losses coins

**Task3: Implementing Win rate**

* We implemented win rates by making additional text boxes for total number of rolls and number of times six was rolled.
* These give calls to singleSixes() and totalRolls() function in WalletViewModel to get values for the same.

**Task4: Implementing Advanced coins feature**

* We then added additional text boxes for number of times a number not six was rolled twice, and number of times six was rolled twice.
* These give calls to doubleOthers() and doubleSixes() function in WalletViewModel to get values for the same.
* Then we changed rollDie() function in WalletViewActivity so that it takes modified parameters into account and changes the balance accordingly.

### d. Testing

For this project, I adopted a test-driven approach, focusing on writing tests to validate core functionalities of the app during development.
#### Unit Testing
I wrote several unit tests to ensure that the `WalletViewModel` was functioning correctly. The tests covered the following aspects:
- **Initial State Validation**: Ensured that the initial balance was zero when the app started.
- **Game Logic Verification**: Tested the different scenarios where coins were added or subtracted based on the player's dice rolls, including the following cases:
    - A single roll of six should add 5 coins.
    - Two consecutive rolls of six should add 10 coins.
    - Two consecutive rolls of any number other than six should result in a 5 coin deduction.
- **Roll Tracking**: Verified that the app correctly tracked total rolls, single sixes, double sixes, and double non-sixes.

#### Instrumented Testing
In addition to unit tests, I wrote 2-3 instrumented test cases to validate the behavior of the app in a real environment:

Overall, the test-driven development approach provided structure to the project, and instrumented testing was useful for validating the app's performance in real-world scenarios. This testing strategy helped me identify bugs early and ensure the core functionality was stable.


### e. Accessibility
* We ran the **TalkBack** service to verify that all UI components were accessible. Initially, it was challenging to navigate the app using TalkBack, but once the content descriptions were properly set, the service accurately read out the texts associated with each component, allowing us to interact with the app without needing to see the screen.

* At first, certain UI elements, such as the total dice rolled, were not being read by TalkBack during updates because it was only reading the initial description of the `TextView`. To resolve this, we updated the content description dynamically during runtime, ensuring that TalkBack would read both the label and the current value of each component. This allowed us to provide a more detailed and informative experience for users relying on accessibility services.

* For elements like buttons or images, we ensured smooth interaction by assigning appropriate content descriptions, either via XML attributes or programmatically. We made sure that all clickable elements were properly focused and accessible using the **Accessibility Scanner**. Additionally, we would consider testing with actual users who rely on accessibility features to further refine the user experience.

* When running the **Accessibility Scanner**, it flagged some issues, such as inconsistent text scaling and duplicate content descriptions for certain output text boxes. These were addressed by setting unique content descriptions for each element, making it easier for users to distinguish between them. Furthermore, we ensured that elements like the dice button would announce their updated value each time they were interacted with, providing real-time feedback to users.


With this we had successfully mitigated all issues the accessibility scanner and talkback had brought to us.

### f. Time
* Basic backend code in WalletViewModel took 30 Minutes.
* Designing layout in both Portrait and Landscape took each of us 1 Hour and then renaming variables to match both the layout took another 1 Hour.
* Implementing the code with one view took 30 Minutes.
* Implementing Toasting took 30 Minutes.
* Implementing on-screen Rotate another 1 Hour.
* Implementing the Accessibility Advanced Feature 1 Hour.
* Working on Instrumented Test cases for 1 Hour.
* Updating README file took another 2 hours.
* Total Time approx 10.5 Hours.

### g. Pair Programming

* Working with Pair Programming really split out work.
* We split our work by working on the one layout each.
* We also split the work of implementing features and worked in an order.
* We would rate our pair programming a 5/5.

### h. Difficulty
* We would Rate this assignment 9/10 as it was UI intensive, and meeting the requirements took time and would be challenging for first time users.