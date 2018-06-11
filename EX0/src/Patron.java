/**
 * This class represents a library patron that has a name and assigns values to
 * different literary aspects of books.
 */
public class Patron {
    /**
     * The first name of the patron.
     */
    String patronFirstName;

    /**
     * The last name of the patron.
     */
    String patronLastName;

    /**
     * The weight the patron assigns to the comic aspects of books.
     */
    int comicTendency;

    /**
     * The weight the patron assigns to the dramatic aspects of books.
     */
    int dramaticTendency;

    /**
     * The weight the patron assigns to the educational aspects of books.
     */
    int educationalTendency;

    /**
     * The minimal literary value a book must have for this patron to enjoy it.
     */
    int patronEnjoymentThreshold;

    /**
     * The number of books the patron currently have.
     */
    int numberOfBooks;

    /**
     * Creates a new patron with the given characteristics.
     *
     * @param patronFirstName The first name of the patron.
     * @param patronLastName The last name of the patron.
     * @param comicTendency The weight the patron assigns to the comic aspects of books.
     * @param dramaticTendency The weight the patron assigns to the dramatic aspects of books.
     * @param educationalTendency The weight the patron assigns to the educational aspects of books.
     * @param patronEnjoymentThreshold The minimal literary value a book must
     *                                have for this patron to enjoy it.
     */
    public Patron(String patronFirstName, String patronLastName, int comicTendency,
                  int dramaticTendency, int educationalTendency, int patronEnjoymentThreshold) {
        this.patronFirstName = patronFirstName;
        this.patronLastName = patronLastName;
        this.comicTendency = comicTendency;
        this.dramaticTendency = dramaticTendency;
        this.educationalTendency = educationalTendency;
        this.patronEnjoymentThreshold = patronEnjoymentThreshold;
        this.numberOfBooks = 0;
    }


    /**
     * Returns a string representation of the patron, which is a sequence of its first and last name,
     * separated by a single white space. For example, if the patron's first name is "Ricky" and his last
     * name is "Bobby", this method will return the String "Ricky Bobby".
     *
     * @return the String representation of this patron.
     */
    public String stringRepresentation() {
        return this.patronFirstName + " " + this.patronLastName;
    }


    /**
     * Returns the literary value this patron assigns to the given book.
     *
     * @param book the book to asses
     * @return the literary value this patron assigns to the given book.
     */
    public int getBookScore(Book book) {
        return (book.getBookComicValue() * this.comicTendency) + (book.getBookDramaticValue() * this
                .dramaticTendency) + (book.getBookEducationalValue() * this.educationalTendency);
    }


    /**
     * Returns true of this patron will enjoy the given book, false otherwise.
     *
     * @param book the book to asses
     * @return true of this patron will enjoy the given book, false otherwise.
     */
    boolean willEnjoyBook(Book book) {
        return this.patronEnjoymentThreshold < this.getBookScore(book);
    }


    /**
     * getter for numberOfBooks
     * @return numberOfBooks
     */
    int getNumberOfBooks() {
        return numberOfBooks;
    }

    /**
     * setter for numberOfBooks
     * @param numberOfBooks the new number of books
     */
    void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    /**
     * getter for the patron threshold
     */
    int getPatronEnjoymentThreshold(){
        return this.patronEnjoymentThreshold;
    }

}
