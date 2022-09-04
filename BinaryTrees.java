import java.util.*;
import java.util.Stack;

public class BinaryTrees {

     static class Node{
        Node left;
        Node right;
        int data;

          Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

     static class BinaryTree {
         static int index = -1;
         public static Node insertTree(int[] nodes) {
             index++;
             if (nodes[index] == -1) {
                 return null;
             }
             Node newNode = new Node(nodes[index]);
             newNode.left = insertTree(nodes);
             newNode.right = insertTree(nodes);
             return newNode;
         }
     }

    public static void main(String[] args) {
        int[] arr = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree binaryTree = new BinaryTree();
        Node tree =binaryTree.insertTree(arr);

        inOrder(tree);
        System.out.println();
        preOrder(tree);
        System.out.println();
        postOrder(tree);
        int h = height(tree);
        System.out.println("height: "+h);
    }

    static void inOrder(Node root){
        if(root == null ) return;
        inOrder(root.left);
        System.out.print(root.data+"-->");
        inOrder(root.right);
    }
    static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.data+"-->");
        preOrder(root.left);
        preOrder(root.right);
    }
    static void postOrder(Node root){
        if(root == null) return;
        preOrder(root.left);
        preOrder(root.right);
        System.out.print(root.data+"-->");
    }

    static List<Integer> postOrderIterative(Node root){
        Stack<Node> traverseStack = new Stack<>();
        List<Integer> preOrder = new ArrayList<>();
        if(root == null) return preOrder;
        traverseStack.push(root);
        while(!traverseStack.isEmpty()){
            root = traverseStack.pop();
            preOrder.add(root.data);
        }
        return preOrder;
    }

    static Node lowestCommonAncestor(Node root, int first, int second){
        if(root == null) return  null;
        if(root.data == first || root.data == second) return root;
        Node left = lowestCommonAncestor(root.left, first, second);
        Node right = lowestCommonAncestor(root.right, first, second);

        if(left == null) return  right;
        if(right == null) return  left;

        return root;
    }
    static boolean nodePath(Node root, List<Integer> arr, int target){
        if(root == null) return  false;
        arr.add(root.data);
        if(root.data == target) return  true;
        if(nodePath(root.left, arr, target ) || nodePath(root.right, arr,target)) return true;
        arr.remove(arr.size()-1);
        return  false;
    }

    static void leftView(Node root, List<Integer> arr, int depth){
        if(root == null) return;
        if( depth == arr.size()) arr.add(root.data);
        leftView(root.left, arr, depth + 1);
        leftView(root.right, arr, depth + 1);
    }

    static void levelOrderTraversal(Node root){
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            Node temp = q.poll();
            System.out.println(temp.data + " ");
            if(temp.left != null)
                q.offer(temp.left);
            if (temp.right != null)
                q.offer(temp.right);
        }
    }

    static int height(Node root){
        if(root == null)
            return  0;
        if(root.left == null && root.right ==null){
            return 2;
        }


        return 1+ Math.max(height(root.left), height(root.right));
    }

    static int countGoodNodes(Node root){
         if(root == null) return 0;
         return dfs(root, root.data);
    }

    static int dfs(Node root, int max){
         if(root == null) return 0;
         max = Math.max(max, root.data);
         if(max <= root.data)
             return 1 + dfs(root.left, max) + dfs(root.right, max);
         else
             return dfs(root.left, max) + dfs(root.right, max);
     }

     static void printLeafNodes(Node root){
         if(root == null) return;
         if(root.left ==null && root.right == null){ System.out.print(root.data+" "); return;}
         if (root.left != null) printLeafNodes(root.left);
         if (root.right !=null) printLeafNodes(root.right);
     }

}
