package example.study;

/**
 * 二叉树数据结构
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.addNode(8);
        bt.addNode(3);
        bt.addNode(10);
        bt.addNode(1);
        bt.addNode(6);
        bt.addNode(14);
        bt.addNode(4);
        bt.addNode(7);
        bt.addNode(13);
        bt.printNode();
    }
}

class BinaryTree {
    private Node root;
    public void addNode(int data) {
        if (root == null) {
            root = new Node(data);
        }
        else {
            root.add(data);
        }
    }
    public void printNode() {
        if (root != null) {
            root.print();
        }
    }

    private class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        private void add(int data) {
            if (this.data > data) {
                if (this.left == null) {
                    this.left = new Node(data);
                }
                else {
                    this.left.add(data);
                }
            }
            else {
                if (this.right == null) {
                    this.right = new Node(data);
                }
                else {
                    this.right.add(data);
                }
            }
        }

        // 中序遍历，左→根→右
        public void print() {
            if (this.left != null) {
                this.left.print();
            }
            if (this.right != null) {
                this.right.print();
            }
            System.out.println(this.data + "->");
        }
    }
}
