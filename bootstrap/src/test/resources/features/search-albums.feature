Feature: Search albums

  Scenario: Search albums by title
    Given the following albums
      | title               | artist       | releaseYear | coverUrl                                                                           |
      | Don't Give Me Names | Guano Apes   | 2000        | http://g-ecx.images-amazon.com/images/G/01/x-site/icons/no-img-lg._V192198674_.gif |
      | Shine               | Cyndi Lauper | 2002        | http://ecx.images-amazon.com/images/I/315DQQYV7CL.jpg                              |
      | Beautiful Collision | Bic Runga    | 2002        | http://g-ecx.images-amazon.com/images/G/01/x-site/icons/no-img-lg._V192198674_.gif |
    When I search for "Don't"
    Then I get the result
      | title               | artist     | releaseYear | coverUrl                                                                           |
      | Don't Give Me Names | Guano Apes | 2000        | http://g-ecx.images-amazon.com/images/G/01/x-site/icons/no-img-lg._V192198674_.gif |

  Scenario: Search albums by artist

  Scenario: Searched keywords should not include release year

  Scenario: Searched keywords should not include cover url

  Scenario: Search should return paginated results

  Scenario: Search should return release year count

  Scenario: Search should return the total number of results

