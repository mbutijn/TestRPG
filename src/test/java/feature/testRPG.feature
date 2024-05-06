Feature: Tests for RPG
  @verifyNameLength # test during the creation of the character
  Scenario: Verify length of entered character name
    When I type a character name of 2 letters
    And I click on the startButton
    Then I should see a validation message Name must be at least 3 characters
    When I type a character name of 21 letters
    Then I should see a validation message Name cannot be longer than 20 characters

  @verifyStatsChanged
  Scenario: Verify changing build character changes stats
    Given the stats for the current build type
    When I change the build type to Knight
    Then I should see the stats changed

  @adventure
  Scenario: Clicking 5 times on the button should level up your character and show a confirmation message
    Given I have a current level
    When I type a character name of 4 letters
    And I click on the startButton
    And I click on the button 5 times
    Then I should see a higher level
    And I should see a first confirmation message Great job! You levelled up
    And I should see the button becomes disabled

  @adventure
  Scenario: Selecting a file for upload should level up your character and show a confirmation message
    Given I have a current level
    When I have a character name of 4 letters
    And I click on choose file
    Then I should see a higher level
    And I should see second confirmation message File selected, level up!
    And I should see the file upload section becomes disabled

  @adventure
  Scenario: Typing 'Lorem Ipsum' into the input field should level up your character and show a confirmation message.
    Given I have a current level
    And I have a character name of 4 letters
    When I type Lorem Ipsum in the Type it! text-field
    Then I should see a higher level
    And I should see a third confirmation message Dolar sit amet!
    And I should see the input field becomes disabled

  @adventure
  Scenario: Moving the slider all the way to the right should level up your character and show a confirmation message.
    Given I have a current level
    And I have a character name of 4 letters
    When I move the slider all the way to the right
    Then I should see a higher level
    And I should see a fourth confirmation message Slid to the next level!
    And I should see the slider becomes disabled