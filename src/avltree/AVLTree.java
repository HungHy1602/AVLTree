/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avltree;

/* Name: Le Cong Hung
Student Code: SE161248
Purpose: AVLTree.
 */
public class AVLTree {

    Node root;

    int height(Node N) {
        if (N == null) {
            return 0;
        }
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform the rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform the rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int getBalanceFactor(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    Node insertNode(Node node, int item) {
        /* 1. Standard BST Insertion */
        if (node == null) {
            return new Node(item);
        }

        if (item < node.item) {
            node.left = insertNode(node.left, item);
        } else if (item > node.item) {
            node.right = insertNode(node.right, item);
        } else {
            return node; // Duplicate items are not allowed
        }
        /* 2. Update height of this ancestor node */
        node.height = 1 + Math.max(height(node.left), height(node.right));

        /* 3. Get the balance factor for this ancestor node */
        int balanceFactor = getBalanceFactor(node);

        // If the node becomes unbalanced, there are four possible cases
        // Left-Left Case (LL)
        if (balanceFactor > 1 && item < node.left.item) {
            return rightRotate(node);
        }

        // Right-Right Case (RR)
        if (balanceFactor < -1 && item > node.right.item) {
            return leftRotate(node);
        }

        // Left-Right Case (LR)
        if (balanceFactor > 1 && item > node.left.item) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left Case (RL)
        if (balanceFactor < -1 && item < node.right.item) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node deleteNode(Node root, int item) {
        /* 1. Standard BST delete */
        if (root == null) {
            return root;
        }

        if (item < root.item) {
            root.left = deleteNode(root.left, item);
        } else if (item > root.item) {
            root.right = deleteNode(root.right, item);
        } else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                }

                root = temp;
            } else {
                Node temp = nodeWithMinimumValue(root.right);
                root.item = temp.item;
                root.right = deleteNode(root.right, temp.item);
            }
        }

        if (root == null) {
            return root;
        }

        /* 2. Update height of the current node */
        root.height = 1 + Math.max(height(root.left), height(root.right));

        /* 3. Get the balance factor for this node */
        int balanceFactor = getBalanceFactor(root);

        // If the node becomes unbalanced, there are four possible cases
        // Left-Left Case (LL)
        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }

        // Right-Right Case (RR)
        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        // Left-Right Case (LR)
        if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right-Left Case (RL)
        if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node nodeWithMinimumValue(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.item + " (" + getBalanceFactor(node) + ") "); // Display balance factor
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private void printTree(Node currPtr, String indent, boolean last) {
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }
            System.out.println(currPtr.item + " (" + currPtr.height + ")");
            printTree(currPtr.left, indent, false);
            printTree(currPtr.right, indent, true);
        }
    }

    public void displayTree() {
        printTree(root, "", true);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes
        tree.root = tree.insertNode(tree.root, 33);
        tree.root = tree.insertNode(tree.root, 13);
        tree.root = tree.insertNode(tree.root, 53);
        tree.root = tree.insertNode(tree.root, 9);
        tree.root = tree.insertNode(tree.root, 21);
        tree.root = tree.insertNode(tree.root, 61);
        tree.root = tree.insertNode(tree.root, 8);
        tree.root = tree.insertNode(tree.root, 11);

        // Display the AVL tree with balance factors after insertion
        System.out.println("AVL Tree with Balance Factors after Insertion:");
        tree.displayTree();  // Modify your displayTree method to show balance factors

        // Delete nodes
        tree.root = tree.deleteNode(tree.root, 13);
        tree.root = tree.deleteNode(tree.root, 53);

        // Display the AVL tree with balance factors after deletion
        System.out.println("\nAVL Tree with Balance Factors after Deletion:");
        tree.displayTree();  // Modify your displayTree method to show balance factors
    }

}
