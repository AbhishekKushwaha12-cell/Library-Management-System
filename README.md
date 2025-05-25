# Library-Management-System
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Library Management System - React Demo</title>
<style>
  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: #f4f7f9;
    margin: 0;
    padding: 20px;
    color: #333;
  }
  h1 {
    color: #004d99;
    text-align: center;
    margin-bottom: 20px;
  }
  .container {
    max-width: 600px;
    margin: auto;
    background: #fff;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0 10px 20px rgb(0 0 0 / 0.1);
  }
  label {
    display: block;
    margin-top: 10px;
    font-weight: 600;
  }
  input[type="text"], select {
    width: 100%;
    padding: 8px 10px;
    margin-top: 5px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
  }
  button {
    margin-top: 15px;
    background-color: #007acc;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 7px;
    font-size: 1em;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  button:hover {
    background-color: #005fa3;
  }
  .book-list {
    margin-top: 30px;
  }
  .book {
    background-color: #e9f0fa;
    padding: 10px 15px;
    border-radius: 8px;
    margin-bottom: 10px;
  }
  .book-type {
    font-weight: 700;
    color: #004d99;
  }
  .search-section {
    margin-top: 25px;
  }
  .no-book {
    color: #777;
    font-style: italic;
  }
</style>
</head>
<body>
<div id="root"></div>
<script crossorigin src="https://unpkg.com/react@17/umd/react.development.js"></script>
<script crossorigin src="https://unpkg.com/react-dom@17/umd/react-dom.development.js"></script>
<script>
  const e = React.createElement;

  class LibraryManagementSystem extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        books: [],
        title: '',
        author: '',
        type: 'Fiction',
        searchQuery: '',
        searchResult: null
      };
    }

    handleChange(field, event) {
      this.setState({ [field]: event.target.value, searchResult: null});
    }

    addBook() {
      const { title, author, type, books } = this.state;
      if (!title.trim() || !author.trim()) {
        alert('Please enter both title and author.');
        return;
      }
      const newBook = { title: title.trim(), author: author.trim(), type: type };
      this.setState({
        books: [...books, newBook],
        title: '',
        author: '',
        searchResult: null
      });
    }

    searchBook() {
      const { searchQuery, books } = this.state;
      if (!searchQuery.trim()) {
        this.setState({ searchResult: null });
        return;
      }
      const found = books.find(b => b.title.toLowerCase() === searchQuery.trim().toLowerCase());
      this.setState({ searchResult: found || 'notfound' });
    }

    render() {
      const { books, title, author, type, searchQuery, searchResult } = this.state;
      return e('div', { className: 'container', role: 'main' }, 
        e('h1', null, 'Library Management System'),

        e('label', { htmlFor: 'titleInput' }, 'Book Title:'),
        e('input', {
          type: 'text',
          id: 'titleInput',
          value: title,
          onChange: this.handleChange.bind(this, 'title'),
          placeholder: 'Enter book title'
        }),

        e('label', { htmlFor: 'authorInput' }, 'Author:'),
        e('input', {
          type: 'text',
          id: 'authorInput',
          value: author,
          onChange: this.handleChange.bind(this, 'author'),
          placeholder: 'Enter author name'
        }),

        e('label', { htmlFor: 'typeSelect' }, 'Book Type:'),

        e('select', {
          id: 'typeSelect',
          value: type,
          onChange: this.handleChange.bind(this, 'type')
        }, 
          e('option', null, 'Fiction'),
          e('option', null, 'Non-Fiction'),
          e('option', null, 'Reference')
        ),

        e('button', { onClick: () => this.addBook(), 'aria-label': 'Add Book' }, 'Add Book'),

        e('div', { className: 'book-list', 'aria-label': 'Book List' },
          e('h2', null, 'All Books'),
          books.length === 0 
            ? e('p', { className: 'no-book' }, 'No books added yet.')
            : books.map((book, idx) => 
                e('div', { key: idx, className: 'book' },
                  e('span', { className: 'book-type' }, [${book.type}]),
                  ` Title: ${book.title}, Author: ${book.author}`
                )
              )
        ),

        e('div', { className: 'search-section' },
          e('h2', null, 'Search Book by Title'),
          e('input', {
            type: 'text',
            placeholder: 'Enter title to search',
            value: searchQuery,
            onChange: this.handleChange.bind(this, 'searchQuery'),
            'aria-label': 'Search book title'
          }),
          e('button', { onClick: () => this.searchBook(), 'aria-label': 'Search Book' }, 'Search'),
          e('div', { role: 'region', 'aria-live': 'polite', style: { marginTop: '12px' } },
            searchResult === null ? null :
            searchResult === 'notfound' ?
              e('p', null, 'Book not found.') :
              e('p', null, 
                e('span', { className: 'book-type' }, [${searchResult.type}]),
                ` Title: ${searchResult.title}, Author: ${searchResult.author}`
              )
          )
        )
      );
    }
  }

  ReactDOM.render(e(LibraryManagementSystem), document.getElementById('root'));
</script>
</body>
</html>
