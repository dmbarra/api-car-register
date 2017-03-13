Feature: Should return welcome message

  Scenario: Hit corret endpoint to show message
    Given My service is online
    When I acess the service
    Then I show message: "Oi Henrique!"