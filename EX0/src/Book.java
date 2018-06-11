/**
 * This class represents a book, which has a title, author, year of publication
 * and different literary aspects.
 */
class Book {

    int noPatron = -1;
    /**
     * The title of this book.
     */
    String bookTitle;

    /**
     * The name of the author of this book.
     */
    String bookAuthor;

    /**
     * The year this book was published.
     */
    int bookYearOfPublication;

    /**
     * The comic value of this book.
     */
    int bookComicValue;

    /**
     * The dramatic value of this book.
     */
    int bookDramaticValue;

    /**
     * The educational value of this book.
     */
    int bookEducationalValue;

    /**
     * The id of the current borrowe of this book.
     */
    int currentBorrowerId = noPatron;

    /**
     * The status of the book, stocked \ unstocked.
     */
    boolean isStocked;

    /*----=  Constructors  =-----*/

    /**
     * Creates a new book with the given characteristic.
     *
     * @param bookTitle             The title of the book.
     * @param bookAuthor            The name of the author of the book.
     * @param bookYearOfPublication The year the book was published.
     * @param bookComicValue        The comic value of the book.
     * @param bookDramaticValue     The dramatic value of the book.
     * @param bookEducationalValue  The educational value of the book.
     */
    Book(String bookTitle, String bookAuthor, int bookYearOfPublication,
         int bookComicValue, int bookDramaticValue, int bookEducationalValue) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookYearOfPublication = bookYearOfPublication;
        this.bookComicValue = bookComicValue;
        this.bookDramaticValue = bookDramaticValue;
        this.bookEducationalValue = bookEducationalValue;
        this.currentBorrowerId = noPatron;
        this.isStocked = true;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * Returns a string representation of the book, which is a sequence
     * of the title, author, year of publication and the total literary value of the book, separated by
     * commas, inclosed in square brackets. For example, if the book is
     * titled "Monkey Feet", was written by Ernie Douglas, published in 1987 and has a comic value of 7,
     * dramatic value of 3 and an educational value of 1, this method will return the string:
     * "[MonkeyFeet,Ernie Douglas,1987,11]"
     *
     * @return the String representation of this book.
     */
    String stringRepresentation() {
        return "[" + bookTitle + "," + bookAuthor + "," + bookYearOfPublication + ","
                + getLiteraryValue() + "]";
    }


    /**
     * @return the literary value of this book, which is defined as the sum of its comic value, its dramatic
     * value and its educational value.
     */
    int getLiteraryValue() {
        return this.bookComicValue + this.bookDramaticValue
                + this.bookEducationalValue;
    }


    /**
     * Sets the given id as the id of the current borrower of this book,
     * noPatron if no patron is currently borrowing
     * it. will also set the stock status.
     * @param borrowerId the borrower id we set to the book
     */
    void setBorrowerId(int borrowerId) {
        this.currentBorrowerId = borrowerId;
        this.isStocked = borrowerId == noPatron;
    }

    /**
     * @return the id of the current borrower of this book.
     */
    int getCurrentBorrowerId() {
        return this.currentBorrowerId;
    }

    /**
     * Marks this book as returned.
     */
    void returnBook() {
        this.currentBorrowerId = noPatron;
        this.isStocked = true;
    }

    /**
     * getter for bookComicValue
     * @return bookComicValue
     */
    int getBookComicValue() {
        return this.bookComicValue;
    }


    /**
     * getter for bookDramaticValue
     * @return bookDramaticValue
     */
    int getBookDramaticValue() {
        return this.bookDramaticValue;
    }


    /**
     * getter for bookEducationalValue
     * @return bookEducationalValue
     */
    int getBookEducationalValue() {
        return this.bookEducationalValue;
    }

    /**
     * @return isStocked
     */
    boolean isStocked(){
        return this.isStocked;
    }
}
