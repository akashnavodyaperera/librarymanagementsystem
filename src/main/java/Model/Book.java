package Model;


public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private String publisher;
    private int publicationYear;
    private boolean availability;
    

    public Book(int bookId, String title, String author, String category, String isbn, String publisher, int publicationYear, boolean availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availability = availability;
    }
    
    public Book(int bookId, String title, String author, String category, String isbn, String publisher, int publicationYear) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availability = true; 
    }
    
    public Book(String title, String author, String category, String isbn, String publisher, int publicationYear) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.availability = true; 
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public String getPublisher() {
        return publisher;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public boolean isAvailability() {
        return availability;
    }
    
    // Setter methods
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", availability=" + availability +
                '}';
    }
}