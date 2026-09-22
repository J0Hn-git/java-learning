package DAY_2;

public class FinalChallenge {
    
}


abstract class ListItem {

    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    // Constructor.
    public ListItem(Object value) {
        this.value = value;
    }
    // Getters and Setters.
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }

    // Abstract methods.
    public abstract ListItem next();
    public abstract ListItem setNext(ListItem rightLink);
    public abstract ListItem previous();
    public abstract ListItem setPrevious(ListItem leftLink);
    public abstract int compareTo(ListItem item);
}

class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override 
    public ListItem next() {
        return rightLink;
    }
    @Override 
    public ListItem setNext(ListItem rightLink) {
        this.rightLink = rightLink;
        return rightLink;
    }
    @Override 
    public ListItem previous() {
        return leftLink;
    }
    @Override 
    public ListItem setPrevious(ListItem leftLink) {
        this.leftLink = leftLink;
        return leftLink;
    }
    @Override 
    public int compareTo(ListItem item) {

        if((int) value > (int) item.value){
            return 1;
        }
        else if( (int) value < (int) item.value) {
            return -1;
        }
        return 0;
    }
}

interface NodeList {

    ListItem getRoot();
    boolean addItem(ListItem item);
    boolean removeItem(ListItem item);
    void traverse(ListItem root);
}

class MyLinkedList implements NodeList {

    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    @Override 
    public ListItem getRoot() {
        return root;
    }
    @Override 
    public boolean addItem(ListItem item) {

        if(item == null) {
            return false;
        }
        if(root == null){
            root = item;
            return true;
        }
        //Item belongs before root.
        if(item.compareTo(root) < 0) {
            item.setNext(root);
            root.setPrevious(item);
            root = item;

            return true;
        }

        ListItem current  = root;

        while(current != null) {
            int comparison = item.compareTo(current);

            if(comparison == 0){
                return false;
            }
            if(comparison > 0) {
                if(current.next() == null){

                    current.setNext(item);
                    item.setPrevious(current);
                    return true;
                }
                current = current.next();
            }
            else {
                ListItem previous = current.previous();
                previous.setNext(item);
                item.setPrevious(previous);

                item.setNext(current);
                current.setPrevious(item);
                return true;
            }
        }
        return false;
    }
    @Override 
    public boolean removeItem(ListItem item) {

        if(item == null || root == null) {
            return false;
        }
        ListItem current = root;

        while(current != null) {
            int comparison = item.compareTo(current);

            if(comparison == 0){

                //Removing root
                if(current == root) {
                    root = current.next();

                    if(root != null){
                        root.setPrevious(null);
                    }
                }
                else {
                    ListItem previous = current.previous();
                    ListItem next = current.next();

                    previous.setNext(next);
                    if(next != null) {
                        next.setPrevious(previous);
                    }
                }
                return true;
            }
            if(comparison < 0){
                return false;
            }
            current = current.next();
        }
        return false;
    }

    @Override 
    public void traverse(ListItem root) {

        if(root == null) {
            System.out.println("The list is empty");
            return ;
        }
        ListItem current = root;
        while(current != null) {
            System.out.println(current.getValue());
            current = current.next();
        }
    }
}
