import java.util.*;

public class Library {

    ArrayList<Book> books = new ArrayList<>();
    HashMap<Integer, Book> map = new HashMap<>();

    Stack<String> undoStack = new Stack<>();
    LinkedList<String> history = new LinkedList<>();
    Queue<String> waitingQueue = new LinkedList<>();

    public void addBook(Book b) {
        books.add(b);
        map.put(b.id, b);
        undoStack.push("Added Book ID: " + b.id);
    }

    public boolean deleteBook(int id) {
        Book b = map.get(id);
        if (b == null) return false;

        books.remove(b);
        map.remove(id);

        undoStack.push("Deleted Book ID: " + id);
        return true;
    }

    public String issueBook(int id) {
        Book b = map.get(id);
        if (b == null) return "Book not found";

        if (!b.issued) {
            b.issued = true;
            history.add("Issued: " + b.title);
            return "Book Issued Successfully";
        } else {
            waitingQueue.add(b.title);
            return "Already issued → Added to Queue";
        }
    }

    public String returnBook(int id) {
        Book b = map.get(id);
        if (b == null) return "Book not found";

        b.issued = false;
        history.add("Returned: " + b.title);
        return "Book Returned Successfully";
    }

    public ArrayList<Book> getBooks() {
        return books;
    }
}