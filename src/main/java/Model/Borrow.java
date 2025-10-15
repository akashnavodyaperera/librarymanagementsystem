package Model;

import java.sql.Date;


public class Borrow {
    private int borrowId;
    private int memberId;
    private int bookId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private String status;
    
    public Borrow(int borrowId, int memberId, int bookId, Date issueDate, Date dueDate, Date returnDate, String status) {
        this.borrowId = borrowId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public Borrow(int memberId, int bookId, Date issueDate, Date dueDate, Date returnDate, String status) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public Borrow(int memberId, int bookId, Date issueDate, Date dueDate, String status) {
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    public int getBorrowId() {
        return borrowId;
    }
    
    public int getMemberId() {
        return memberId;
    }
    
    public int getBookId() {
        return bookId;
    }
    
    public Date getIssueDate() {
        return issueDate;
    }
    
    public Date getDueDate() {
        return dueDate;
    }
    
    public Date getReturnDate() {
        return returnDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }
    
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Borrow{" +
                "borrowId=" + borrowId +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}