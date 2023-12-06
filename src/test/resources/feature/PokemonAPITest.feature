Feature: Validate Pokemon API

  Scenario Outline: Verify Pokemon API Response for Invalid Data
    Given user set up request for "/pokemon/<invalidData>"
    When user perform GET call
    Then verify status code is 404

    Examples:
      | invalidData |
      | 0           |
      | 10000       |
      | -5          |
      | InvalidName |

  Scenario Outline: Verify Pokemon API Response Matches the Json Schema
    Given user set up request for "/pokemon/<validData>"
    When user perform GET call
    Then verify status code is 200
    And verify response matches the json schema "pokemon_response schema.jsd"

    Examples:
      | validData  |
      | 25         |
      | charmeleon |
      | beedrill   |
      | 78         |

