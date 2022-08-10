import java.util.*;
import java.lang.Comparable;

// Node of the binary tree
class TreeNode <T>
{
    T val;
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode(T x)
    {
        val = x;
    }
}

public class BinarySearchTree<T extends Comparable<T>>  implements Iterable<T> {


    TreeNode <T> root;
    String name;

    List<T> list = new ArrayList<T>();

    public BinarySearchTree(String name)
    {
        this.name = name;
    }

    public void addAll(List <T> data)
    {
        for(T element : data)
        {
            insert(element);
        }
    }
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return new BinarySearchTreeIterator(this.root);
    }


    void insert(T key)  {
        root = insert_Recursive(root, key);
    }

    TreeNode insert_Recursive(TreeNode root, T key) {
        //tree is empty
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }
        if(key.compareTo((T)root.val) < 0) {
            root.left = insert_Recursive(root.left, key);
        }
        else if(key.compareTo((T)root.val) >= 0) {
            root.right = insert_Recursive(root.right, key);
        }
        return  root;
    }

    public String toString(){
        return "["+ this.name+ "] " + print(root);

    }

    private String print(TreeNode root) {
        if (root == null) {
            return "";
        }
        else if(root.left != null && root.right != null) {
            return root.val + " " + "L:(" +print(root.left)+ ")" + " "+ "R:(" +print(root.right)+ ")";
        }
        else if(root.left != null && root.right == null) {
            return root.val + " " + "L:(" +print(root.left)+ ")";
        }
        else if(root.left == null && root.right != null) {
            return root.val + " " + "R:(" +print(root.right)+ ")";
        }
        else {
            return root.val + "";
        }
    }

    public static void main(String[] args) throws Exception {

        // each tree has a name, provided to its constructor
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>("Oak");
        // adds the elements to t1 in the order 5, 3, 0, and then 9
        t1.addAll(Arrays.asList(5, 3, 0, 9));
        BinarySearchTree<Integer> t2 = new BinarySearchTree<>("Maple");
        // adds the elements to t2 in the order 9, 5, and then 10
        t2.addAll(Arrays.asList(9, 5, 10));
        System.out.println(t1); // see the expected output for exact format
        t1.forEach(System.out::println); // iteration in increasing order
        System.out.println(t2); // see the expected output for exact format
        t2.forEach(System.out::println); // iteration in increasing order
        BinarySearchTree<String> t3 = new BinarySearchTree<>("Cornucopia");
        t3.addAll(Arrays.asList("coconut", "apple", "banana", "plum", "durian",
                "no durians on this tree!", "tamarind"));
        System.out.println(t3); // see the expected output for exact format
        t3.forEach(System.out::println); // iteration in increasing order
    }

    public static  <T extends Comparable<T>> List<T> merge(List<BinarySearchTree> bstlist){
        List<T> ans = new ArrayList<T>();

        List<T> res= new ArrayList<T>();

        Thread thread[] = new Thread[bstlist.size()];
        for(int i=0;i<bstlist.size();i++){
            thread[i]=new Thread(new MyThread(bstlist.get(i).root,res));
            thread[i].start();
        }
        for(int i=0;i<bstlist.size();i++){
            try {
                thread[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        PriorityQueue<T> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));

            for(int j=0;j<res.size();j++){
                queue.add(res.get(j));
            }

        while(!queue.isEmpty()){

            ans.add(queue.poll());
        }
        return ans;
    }

    public static synchronized void  inorder_dfs(TreeNode node,List l) {

        if (node == null) {
            return;
        }
        inorder_dfs(node.left, l);
        l.add(node.val);
        inorder_dfs(node.right, l);
    }
}

class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private Stack<TreeNode<T>> stack;
    private TreeNode<T> current;
    public BinarySearchTreeIterator(TreeNode<T> root) {
        stack = new Stack<>();
        current = root;
    }
    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || current != null;
    }
    @Override
    public T next() {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        current = stack.pop();
        TreeNode<T> temp = current;
        current = current.right;
        return temp.val;
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

class  MyThread<T extends Comparable<T>> extends Thread {
    private TreeNode<T> root;
    private List<T> list;

    public  MyThread(TreeNode<T> root,List<T> list){
        this.root = root;
        this.list = list;
    }

    @Override
    public void run(){
        BinarySearchTree.inorder_dfs(root,list);
    }

}

