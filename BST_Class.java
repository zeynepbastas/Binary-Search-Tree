//Zeynep Bastas

public class BST_Class {
  
  // Node class for BST node
  public static class Node {
        int data;
        Node left, right;
 
        // constructor for the Node class
        public Node(int key)
        {
            data = key;
            left = right = null;
        }
    }
  
  // a variable for the root of the binary serach tree
  public Node root;
  
  //constructor for an empty Binary Search Tree
  public BST_Class(){
    root = null;
  }
  
  // Another constructor for when a value is assigned to the root of the binary search tree
  public BST_Class(int item){
    root = new Node(item);
  }
  
   //A wrapper class for the searchTree method so the return type is boolean
  public boolean search(int key){
    Node n = searchTree(root,key);
    if (n==null){
      return false;
    }
    else{
      return true;
    }
  }
  
  //An iterative search method to see if an item we are looking for exists in the tree which takes a Node and an int as
  // input and returns a Node
  //This class is private because I am calling it in the wrapper method below which will return boolean as 
  //was asked in the assignment
  private Node searchTree(Node root, int key) {
    Node trav = root;
    while (trav != null) {
      if (key == trav.data){
        return trav;
      }
      if (key < root.data) {
        trav = trav.left;
      }
      else
        trav = trav.right;
    }
    return null;
  }
  
  //An insert class who takes an int input and returns void
  public void insert(int key){
    
    //First part is like searching so we traverse the tree.
    Node parent = null;
    Node trav = root;
    while (trav != null){
      parent=trav;
      if(key<trav.data)
        trav=trav.left;
      else{
        trav = trav.right;
      }
    }
    
    //When we find the parent of the node we will be inserting, we will insert the node as a child
    
    // Inserting the new node:
    //if the tree is empty
    if (parent==null) {
      root = new Node(key);
    }
    
    //to check if the key is smaller than the parent, then it will be on the left
    else if (key<parent.data){
      parent.left = new Node(key);
    }
    
    //to check if the key is larger or equal, then it will be on the right
    else{
      parent.right = new Node(key);
    }
  }
  

  //A method to find the smallest element in the binary search tree
  public int minValue(Node root){
    int minimum = root.data;
    //the minimum value in a tree is on the most left possible in the tree
    // a loop that goes until the left node is null and finds the most left
    while (root.left != null) {
      minimum = root.left.data;
      root = root.left;
    }
    return minimum;
  }
  
  
  //A wrapper class for the delete method that returns void as was asked in the assignment
  public void deleteKey(int key){
    root = (deleteNode(root,key));
  }

  
    /**The code below is something I tried but I could not get something working so I wrote another code
    * but still wanted to have this one here. In this one, I am going by cases we discussed in class case 1&2 being
    * the node to be deleted having no child or one child and case 3 being the note to be deleted having two children.
    * 
  // A private delete method that takes a Node and an int as input and returns Node
  /**private Node deleteNode(Node root, int key) {
    root = deleteNode(root, key);
  }
  

    Node parent = null;
    Node trav = root;
    while (trav!= null && trav.data != key){
      parent = trav;
      if (key<trav.data){
        trav = trav.left;
      }
      else {
        trav=trav.right;
      }
      
      //Deleting the node
      //if the key does not exist in the tree
      if(trav == null){
        return;
      }
      else{
        deleteNode(trav,parent);
      }
    }
  }
  **/
 
  /**
  // a private deleteNode class to delete a node from the tree
  // takes two Node inputs
  private void deleteNode(Node toDelete, Node parent){
    //Cases 1&2: the nodes that will be deleted does not have a child or has one child
    if (toDelete.left == null || toDelete.right == null) {
      Node toDeleteChild = null;
      if (toDelete.left != null){
        toDeleteChild = toDelete.left;
      }
      else if (toDelete.right != null) {
        toDeleteChild = toDelete.right;
      }
      
      else {
        toDeleteChild = null;
      }
      
      if (toDelete.data == root.data){
        root = toDeleteChild;
      }
      
      else if (toDelete.data<parent.data) {
        parent.left=toDeleteChild;
      }
      
      else {
        parent.right=toDeleteChild;
      }
    }
    
   //Case 3: If the node we want to delete has 2 children.
    else{
      Node replacingParent = toDelete;
      Node replacing = toDelete.right;
      while(replacing.left!=null){
        replacingParent = replacing;
        replacing = replacing.left;
      }
      
      toDelete.data=replacing.data;
      toDelete.data=replacing.data;
      deleteNode(replacing, replacingParent);
    }
  }
 
  **/
  
  
  // Here is the private deleteNode method that takes a Node and an int as an input
  //It is private because I will call it in a public method that fits what you gave us in the assignment
  private Node deleteNode(Node root, int key){
    if (root==null){
    return root;
    }
    
    //if the key is smaller than the root, we will go to the left
    if (key< root.data){
      root.left = deleteNode(root.left ,key);
    }
    //if the key is larger, we will go right
    else if(key>root.data){
      root.right = deleteNode(root.right,key);
    }
    
    //Now we find the node we want to delete because its data is the same as the key
    else {
      if (root.left == null){
        return root.right;
      }
      if (root.right == null){
        return root.left;
      }
      
      //The deleted node will be replaced by the smallest in the right side of the node
      //I guess my issue with my delete method I put as a comment was here so I just used the minValue method I already
      //define in the class
      root.data = minValue(root.right);
      root.right = deleteNode(root.right, root.data);
      }
      return root;
  }
  
  // A recursive method for in order traversal where the order is left,root,right
  private void inOrderTraversal(Node root){
    if (root != null) {
      inOrderTraversal(root.left);
      System.out.print(root.data + " ");
      inOrderTraversal(root.right);
    }
    return;
  }
  
  //A recursive method for pre order traversal where the order is root, left, right
  private void preOrderTraversal(Node root){
    if(root != null) {
      System.out.print(root.data + " ");
      preOrderTraversal(root.left);
      preOrderTraversal(root.right);
    }
    return;
  }
  
  // A recursive method for post order traversal where the order is left, right, root
  private void postOrderTraversal(Node root){
    if (root != null){
      postOrderTraversal(root.left);
      postOrderTraversal(root.right);
      System.out.print(root.data + " ");
    }
    return;
  }
  
  //The methods above were private. Now I will call them in ppublic wrapper methods
  
  // Wrappers for recursive functions
  //I got the idea of using these from the sample code you provided so that the input 
    public void postOrder()  {
        postOrderTraversal(root);  }
    public void inOrder() {
        inOrderTraversal(root);   }
    public void preOrder() {
        preOrderTraversal(root);
    }
        
  
  public static void main (String[] args) {
    // an instance of BST_Class
    BST_Class bst = new BST_Class();
    
    //inserting numbers
    bst.insert(45);
    bst.insert(10);
    bst.insert(7);
    bst.insert(12);
    bst.insert(90);
    bst.insert(50);
    //printing the tree with in order traversal
    System.out.println("The BST created with input data with in order traversal: \nIt should be 7 10 12 45 50 90:");
    bst.inOrder();
    System.out.println("");
    //printing the tree with preorder traversal
    System.out.println("The BST created with input data with pre order traversal: \nIt should be 45 10 7 12 90 50:");
    bst.preOrder();
    System.out.println("");
    //printing the tree with post order traversal
    System.out.println("The BST created with input data with post order traversal: \nIt should be 7 12 10 50 90 45:");
    bst.postOrder();
    System.out.println("");
    //deleting 12
    bst.deleteKey(12);
    System.out.println("The BST after the deletion of 12 with in order traversal: \nIt should be 7 10 45 50 90:");
    bst.inOrder();
    System.out.println("");
    System.out.println("The BST after the deletion of 12 with pre order traversal: \nIt should be 45 10 7 90 50:");
    bst.preOrder();
    System.out.println("");
    System.out.println("The BST after the deletion of 12 with post order traversal: \nIt should be 7 10 50 90 45:");
    bst.postOrder();
    System.out.println("");
    //deleting 90
    bst.deleteKey(90);
    bst.inOrder();
    System.out.println("");
    bst.preOrder();
    System.out.println("");
    bst.postOrder();
    System.out.println("");
    //deleting 45
    bst.deleteKey(45);
    bst.inOrder();
    System.out.println("");
    bst.preOrder();
    System.out.println("");
    bst.postOrder();
    System.out.println("");
    System.out.println("Checking if 50 exists: \nThe answer should be true:");
    System.out.println(bst.search(50));
    System.out.println("Checking if 12 exists: \nThe answer should be false:");
    System.out.println(bst.search(12));
    System.out.println("Checking the smallest value: \nThe answer should be 7:");
    System.out.println(bst.minValue(bst.root));
    
    System.out.println("------------");
    
    //Trying a new one
    BST_Class tree = new BST_Class();
    tree.root = new Node(45);
    tree.root.left = new Node(10);
    tree.root.right = new Node(90);
    tree.root.left.left = new Node(7);
    tree.root.left.right = new Node(12);
    //PreOrder Traversal
    System.out.println("BST => PreOrder Traversal:");
    tree.preOrder();
    //InOrder Traversal
    System.out.println("\nBST => InOrder Traversal:");
    tree.inOrder();
    //PostOrder Traversal
    System.out.println("\nBST => PostOrder Traversal:");
    tree.postOrder();
  }
}
  