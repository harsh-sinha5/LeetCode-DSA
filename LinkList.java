import java.util.ArrayList;
import java.util.LinkedList;

public class LinkList {
    public Node head;
    public Node tail;
    public int size;

     class Node{
        public int data;
        public Node next;

        public Node(){}

        public Node(int data){
            this.data = data;
        }
    }

    public LinkList(){
        size=0;
    }

    public void insertAtFirst(int data){
            Node newNode = new Node();
            newNode.data = data;
        if(head == null){
            head = newNode;
            size++;
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtLast(int data){
        Node newNode = new Node();
        newNode.data = data;
        if(head == null){
            head = newNode;
            return;
        }
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next=newNode;
    }

    public Node reverseLinkedList(Node head){
        Node curr = head;
        Node prev = null;
        while(curr != null){
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public void traverse(Node head){
        Node temp = head;
        while(temp != null) {
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
    }

    public Node insert(Node head, int pos, int data){
        Node newNode = new Node();
        newNode.data = data;
        if(pos == 0){
            newNode.next = head;
            head = newNode;
            return head;
        }
        Node temp = head;
        for(int i = 0; i< pos-1; i++){
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        return  head;
    }

    public void delete(Node head, int pos){
        if(pos == 0){
            head = head.next;
            return;
        }
        Node temp = head;
        for(int i = 0; i <  pos - 1;i++){
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public Node mergeLinkedList(Node one, Node two){
        Node temp = new Node();
        temp.data = 0;
        Node tail = temp;
        while(one != null && two !=null){
            if(one.data <= two.data){
                tail.next = one;
                one = one.next;
            }
            else{
                tail.next = two;
                two = two.next;
            }
            tail = tail.next;
        }
        if(one == null) return two;
        if(two == null) return one;
        return temp.next;
    }

    static Node detectCycle(Node head){
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return slow;
            }
        }
        return null;
    }
    static Node firstCycleNode(Node head){
        Node meet = detectCycle(head);
        Node prev = meet;
        Node start = head;
        while (start != meet){
            start = start.next;
            meet = meet.next;
        }
        return meet;
    }

    static Node reverse(Node head){
        Node curr = head;
        Node prev = null;
        while(curr != null){
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public Node merge(Node one, Node two){
        Node dummy = new Node(0);
        Node tail = dummy;
        while(one != null && two !=null){
            if(one.data <= two.data){
                tail.next = one;
                one = one.next;
            }else{
                tail.next = two;
                two = two.next;
            }
            tail = tail.next;
        }
        if(one == null){
            return two;
        }
        if(two == null) return one;
        return dummy.next;
    }
}

