Feature: Get and create jobs

  Background:
    Given that the job table will contain only:
      |id|name          |
      |1 |SOME_JOB      |
      |2 |SOME_OTHER_JOB|
      |3 |THIRD_JOB     |

  Scenario: Retrieving all jobs
    When we call "/tzatziki-example/jobs"
    Then we receive a status OK
    Then we receive:
      """
      [
        {
          "id": 1,
          "name": "SOME_JOB",
        },
        {
          "id": 2,
          "name": "SOME_OTHER_JOB",
        },
        {
          "id": 3,
          "name": "THIRD_JOB",
        },
      ]
      """

  Scenario: Create a new job
    When we post "/tzatziki-example/jobs?name=SOME_NEW_JOB"
    Then the job table contains only:
      """yml
      - id: 1
        name: SOME_JOB
      - id: 2
        name: SOME_OTHER_JOB
      - id: 3
        name: THIRD_JOB
      - id: 4
        name: SOME_NEW_JOB
      """
    Then we receive a status OK
    Then we receive "4"

  Scenario Outline: Multiple tests with examples
    When we get "/tzatziki-example/jobs/<id>"
    Then we receive a status <response_code>
    Then if <response_code> == OK => we receive:
      """
        id: <id>
        name: <name>
      """

    Examples:
      | id | name      | response_code |
      | 1  | SOME_JOB  | OK            |
      | 4  |           | NOT_FOUND_404 |
      | 3  | THIRD_JOB | OK            |
