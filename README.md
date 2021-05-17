
# backend_livros
Protótipo de aplicação para extrair livros sobre Kotlin.  

## Endpoints
http://localhost:8080/

 ***POST*** /books
 
  <details>
    <summary><b>Request Body</b></summary>

    ```
    {
      "title": "Book title example 2",
      "description": "Book description example",
      "isbn": "9781617293290"
    }
    ```
 </details>

 ***GET*** /books

 Seleciona os livos no site https://kotlinlang.org/docs/books.html
 
 ***GET*** /books{id}
 
   <details>
    <summary><b>Response </b></summary>

    ```
    {

    "id": 2,
    "title": "Atomic Kotlin",
    "description": "From Bruce Eckel, author of the multi-award-winning Thinking in C++ and Thinking in Java, and Svetlana Isakova, Kotlin Developer Advocate at JetBrains, comes a book that breaks the language concepts into small, easy-to-digest “atoms”, along with a free course consisting of exercises supported by hints and solutions directly inside IntelliJ IDEA!",
    "isbn": "Unavailable"

    }
    ```
 </details>
 
  ***GET*** /books/getAllItems
 
   <details>
    <summary><b>Response </b></summary>

    ```

    [
      {
          "id": 1,
          "title": "Book title example 2",
          "description": "Book description example",
          "isbn": "9781617293290"
      },
      {
          "id": 2,
          "title": "Atomic Kotlin",
          "description": "From Bruce Eckel, author of the multi-award-winning Thinking in C++ and Thinking in Java, and Svetlana Isakova, Kotlin Developer Advocate at JetBrains, comes a book that breaks the language concepts into small, easy-to-digest “atoms”, along with a free course consisting of exercises supported by hints and solutions directly inside IntelliJ IDEA!",
          "isbn": "Unavailable"
      }
     ]

    ```
 </details>

 ***DELETE*** /books{id}
 
 
