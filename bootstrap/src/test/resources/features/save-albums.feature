Feature: Save albums

  Scenario: Save albums
    Given the following albums
      | title               | artist       | releaseYear | coverUrl                                                                           |
      | Don't Give Me Names | Guano Apes   | 2000        | http://g-ecx.images-amazon.com/images/G/01/x-site/icons/no-img-lg._V192198674_.gif |
      | Shine               | Cyndi Lauper | 2002        | http://ecx.images-amazon.com/images/I/315DQQYV7CL.jpg                              |
      | Beautiful Collision | Bic Runga    | 2002        | http://g-ecx.images-amazon.com/images/G/01/x-site/icons/no-img-lg._V192198674_.gif |
    When I try to save them
    Then these albums are saved

  Scenario: Trying to save an album without title should result in an error
    Given an album without title
    When I try to save this album
    Then I get an HTTP 400 error with the message : "Could not save albums. At least one of them doesn't have a title"

