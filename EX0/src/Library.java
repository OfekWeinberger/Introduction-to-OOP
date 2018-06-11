/**
 * This class represents a library, which hold a collection of books. Patrons can register at
 * the library to be able to check out books, if a copy of the requested book is available.
 */
public class Library {

    int noPatron = -1;

    /**
     * The maximal number of books this library can hold.
     */
    int maxBookCapacity;

    /**
     * The maximal number of books this library allows a single patron to borrow at the same time.
     */
    int maxBorrowedBooks;

    /**
     * The maximal number of registered patrons this library can handle.
     */
    int maxPatronCapacity;

    /**
     * Counter for the books.
     */
    int bookCounter;

    /**
     * Counter for the patrons.
     */
    int patronCounter;

    /**
     * An array to store all books.
     */
    Book[] bookArray;

    /**
     * An array to store all patrons.
     */
    Patron[] patronArray;


    /**
     * Creates a new library with the given parameters.
     *
     * @param maxBookCapacity   The maximal number of books this library can hold.
     * @param maxBorrowedBooks  The maximal number of books this library allows
     *                          a single patron to borrow at the same time.
     * @param maxPatronCapacity The maximal number of registered patrons this library can handle.
     */
    Library(int maxBookCapacity, int maxBorrowedBooks, int maxPatronCapacity) {
        this.maxBookCapacity = maxBookCapacity;
        this.maxBorrowedBooks = maxBorrowedBooks;
        this.maxPatronCapacity = maxPatronCapacity;
        this.bookCounter = 0;
        this.patronCounter = 0;
        this.bookArray = new Book[maxBookCapacity];
        this.patronArray = new Patron[maxPatronCapacity];

    }

    /**
     * Adds the given book to this library, if there is place available, and it isn't already in the library.
     *
     * @param book The book to add to this library.
     * @return a non-negative id number for the book if there was a spot and the book was successfully added,
     * or if the book was already in the library; a negative number otherwise.
     */
    int addBookToLibrary(Book book) {
        for(int i = 0; i < this.maxBookCapacity; i++)
            if(this.bookArray[i] == book)
                return i;
        if (this.bookCounter < this.maxBookCapacity) {
            this.bookArray[bookCounter] = book;
            bookCounter++;
            return bookCounter - 1;
        }
        return noPatron;
    }


    /**
     * Returns true if the given number is an id of some book in the library, false otherwise.
     *
     * @param bookId The id to check.
     * @return true if the given number is an id of some book in the library, false otherwise.
     */
    boolean isBookIdValid(int bookId) {
        return bookId >= 0 && bookId < this.maxBookCapacity && this.bookArray[bookId] != null;
    }


    /**
     * Returns the non-negative id number of the given book if he is owned by this library, noPatron otherwise.
     *
     * @param book he book for which to find the id number.
     * @return a non-negative id number of the given book if he is owned by this library, noPatron otherwise.
     */
    int getBookId(Book book) {
        int index = 0;
        while (index < this.bookArray.length) {
            if (this.bookArray[index] == book)
                return index;
            index++;
        }
        return noPatron;
    }


    /**
     * Returns true if the book with the given id is available, false otherwise.
     *
     * @param bookId The id number of the book to check.
     * @return true if the book with the given id is available, false otherwise.
     */
    boolean isBookAvailable(int bookId) {
        return isBookIdValid(bookId) && this.bookArray[bookId].isStocked();
    }


    /**
     * Registers the given Patron to this library, if there is a spot available.
     *
     * @param patron patron - The patron to register to this library.
     * @return a non-negative id number for the patron if there was a spot and the patron
     * was successfully registered, a negative number otherwise.
     */
    int registerPatronToLibrary(Patron patron) {
        for(int i = 0; i < this.maxPatronCapacity; i++)
            if(this.patronArray[i] == patron)
                return i;
        if (this.patronCounter < this.maxPatronCapacity) {
            this.patronArray[patronCounter] = patron;
            patronCounter++;
            return patronCounter - 1;
        }
        return noPatron;
    }


    /**
     * Returns true if the given number is an id of a patron in the library, false otherwise.
     *
     * @param patronId The id to check.
     * @return true if the given number is an id of a patron in the library, false otherwise.
     */
    boolean isPatronIdValid(int patronId) {
        return  patronId > noPatron && patronId < this.maxPatronCapacity && this.patronArray[patronId] != null;
    }


    /**
     * Returns the non-negative id number of the given patron if he is registered to
     * this library, noPatron otherwise.
     *
     * @param patron The patron for which to find the id number.
     * @return a non-negative id number of the given patron if he is registered to
     * this library, noPatron otherwise.
     */
    int getPatronId(Patron patron) {
        int index = 0;
        while (index < this.patronArray.length) {
            if (this.patronArray[index] == patron)
                return index;
            index++;
        }
        return noPatron;
    }


    /**
     * Marks the book with the given id number as borrowed by the patron with the given patron id, if
     * this book is available, the given patron isn't already borrowing the maximal number of books allowed,
     * and if the patron will enjoy this book.
     *
     * @param bookId   The id number of the book to borrow.
     * @param patronId The id number of the patron that will borrow the book.
     * @return true if the book was borrowed successfully, false otherwise.
     */
    boolean borrowBook(int bookId, int patronId) {
        if (isBookIdValid(bookId) && isPatronIdValid(patronId) && isBookAvailable(bookId) &&
                this.patronArray[patronId].getNumberOfBooks() < maxBorrowedBooks &&
                this.patronArray[patronId].willEnjoyBook(this.bookArray[bookId])) {
            this.bookArray[bookId].setBorrowerId(patronId);
            this.patronArray[patronId].setNumberOfBooks(this.patronArray[patronId].getNumberOfBooks() + 1);
            return true;
        }
        return false;

    }


    /**
     * Return the given book.
     *
     * @param bookId The id number of the book to return.
     */
    void returnBook(int bookId) {
        if (isBookIdValid(bookId) && isPatronIdValid(this.bookArray[bookId].getCurrentBorrowerId())) {
            this.patronArray[this.bookArray[bookId].getCurrentBorrowerId()].setNumberOfBooks(
                    this.patronArray[this.bookArray[bookId].getCurrentBorrowerId()].getNumberOfBooks() - 1);
            this.bookArray[bookId].returnBook();
        }
    }


    /**
     * Suggest the patron with the given id the book he will enjoy the most,
     * out of all available books he will enjoy, if any such exist.
     *
     * @param patronId The id number of the patron to suggest the book to.
     * @return The available book the patron with the given will enjoy the most. Null if no book is available.
     */
    Book suggestBookToPatron(int patronId) {
        if (isPatronIdValid(patronId)) {
            int max = this.patronArray[patronId].getPatronEnjoymentThreshold();
            int max_id = noPatron;
            for (int i = 0; i < this.bookArray.length; i++) {
                if (isBookAvailable(i) && this.patronArray[patronId].getBookScore(bookArray[i]) > max) {
                    max = this.patronArray[patronId].getBookScore(bookArray[i]);
                    max_id = i;
                    System.out.println(max + " " + max_id);
                }
            }
            if (max_id != noPatron)
                return this.bookArray[max_id];
        }
        return null;
    }
}
