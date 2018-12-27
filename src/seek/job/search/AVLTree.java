package seek.job.search;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by likoguan on 26/12/18.
 */
class AVLTreeNode {
    public Integer key;
    public AVLTreeNode left;
    public AVLTreeNode right;
    public AVLTreeNode parent;
    public Integer height;
    public AVLTreeNode(Integer key, AVLTreeNode left, AVLTreeNode right, AVLTreeNode parent, Integer height) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.height = height;
    }
}

public class AVLTree {
    public AVLTreeNode root;

    public void iterate() {
        iterate(root);
    }

    private void iterate(AVLTreeNode tree) {
        if (tree == null) {
            return;
        } else {
            iterate(tree.left);
            System.out.println("(" + tree.key + ", " + tree.height +")");
            iterate(tree.right);
        }
    }

    public void add(Integer key) {
        if (root == null) {
            root = new AVLTreeNode(key, null, null, null, 1);
        } else {
            add(key, root);
        }
    }

    private void add(Integer key, AVLTreeNode tree) {
        if (key <= tree.key) {
            if (tree.left == null) {
                tree.left = new AVLTreeNode(key, null, null, tree, 1);
            } else {
                add(key, tree.left);
            }
            if (tree.height == tree.left.height) {
                tree.height++;
            }
        } else {
            if (tree.right == null) {
                tree.right = new AVLTreeNode(key, null, null, tree, 1);
            } else {
                add(key, tree.right);
            }
            if (tree.height == tree.right.height) {
                tree.height++;
            }
        }

        reBalance(tree);
    }

    public void delete(Integer key) {
        AVLTreeNode node = get(key);
        if (node != null) {
            delete(node);
        }
    }

    private void delete(AVLTreeNode node) {
        AVLTreeNode specialNode = null;
        if (node.left == null && node.right == null) {
            replace(node, specialNode);
            specialNode = node.parent;
        } else if (node.left == null) {
            specialNode = node.right;
            replace(node, specialNode);
        } else if (node.right == null) {
            specialNode = node.left;
            replace(node, specialNode);
        } else {
            AVLTreeNode successor = min(node.right);//node后继successor

            /*specialNode是删除后需要调整的可能不平衡的节点*/
            if (successor == node.right) {
                specialNode = successor;
            } else {
                specialNode = successor.parent;
            }

            replace(successor, successor.right);//后继的右孩子代替后继

            replace(node, successor);//后继代替删除的节点

            successor.left = node.left;
            if (successor.left != null)
                successor.left.parent = successor;

            successor.right = node.right;
            if (successor.right != null)
                successor.right.parent = successor;
        }

        reHeight(root);
        /*从specialNode节点开始查找最近的一个不平衡的节点，并调整*/
        reBalance(findFirstUnBalancedParentNode(specialNode));
    }

    private AVLTreeNode findFirstUnBalancedParentNode(AVLTreeNode node) {
        if (node == null) {
            return null;
        }

        Integer balance = getBalance(node);
        if (balance == -2 || balance == 2) {
            return node;
        } else {
            return findFirstUnBalancedParentNode(node.parent);
        }
    }

    private AVLTreeNode min(AVLTreeNode tree) {
        if (tree == null) {
            return null;
        }

        if (tree.left != null) {
            return min(tree.left);
        } else {
            return tree;
        }
    }

    public AVLTreeNode get(Integer key) {
        return get(key, root);
    }

    private AVLTreeNode get(Integer key, AVLTreeNode tree) {
        if (tree == null) {
            return null;
        }

        int cmp = key.compareTo(tree.key);
        if (cmp < 0) {
            return get(key, tree.left);
        } else if (cmp > 0) {
            return get(key, tree.right);
        } else {
            return tree;
        }
    }

    private void reBalance(AVLTreeNode node) {
        if (node == null) {
            return;
        }

        Integer balance = getBalance(node);
        if (balance == -1 || balance == 0 || balance == 1) {
            reHeight(root);
            return;
        }

        if (balance >= 2) {//L
            Integer leftChildBalance = getBalance(node.left);
            if (leftChildBalance >= 0) {//LL
                //reHeight(rotateRight(node));
                rotateRight(node);
            } else {//LR
                //reHeight(rotateLeftThenRight(node));
                rotateLeftThenRight(node);
            }
        }

        if (balance <= -2) {//R
            Integer rightChildBalance = getBalance(node.right);
            if (rightChildBalance <= 0) {//RR
                //reHeight(rotateLeft(node));
                rotateLeft(node);
            } else {//RL
                //reHeight(rotateRightThenLeft(node));
                rotateRightThenLeft(node);
            }
        }

        reHeight(root);
    }

    private AVLTreeNode rotateLeftThenRight(AVLTreeNode node) {
        rotateLeft(node.left);
        return rotateRight(node);
    }

    private AVLTreeNode rotateRightThenLeft(AVLTreeNode node) {
        rotateRight(node.right);
        return rotateLeft(node);
    }

    private AVLTreeNode rotateRight(AVLTreeNode node) {
        AVLTreeNode leftChild = node.left;
        node.left = leftChild.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        leftChild.right = node;
        replace(node, leftChild);
        node.parent = leftChild;
        return leftChild;
    }

    private AVLTreeNode rotateLeft(AVLTreeNode node) {
        AVLTreeNode rightChild = node.right;
        node.right = rightChild.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        rightChild.left = node;
        replace(node, rightChild);
        node.parent = rightChild;
        return rightChild;
    }

    private Integer getBalance(AVLTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    private void reHeight(AVLTreeNode node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                node.height = 1;
            } else if (node.left != null && node.right != null) {
                reHeight(node.left);
                reHeight(node.right);
                node.height = 1 + Math.max(node.left.height, node.right.height);
            } else if (node.left != null) {
                reHeight(node.left);
                node.height = 1 + node.left.height;
            } else {
                reHeight(node.right);
                node.height = 1 + node.right.height;
            }
        }
    }

    private Integer getHeight(AVLTreeNode node) {
        return node == null ? 0 : node.height;
    }

    //以v节点替换以u节点，让u的双亲节点变为v的双亲节点
    private void replace(AVLTreeNode u, AVLTreeNode v) {
        if (u.parent == null) {
            root = v;
        } else {
            if (u.parent.left == u) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
        }

        if (v != null) {
            v.parent = u.parent;
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        //Integer[] arr = {437, 897, 603, 435, 412, 997, 986, 225, 370, 722, 238, 831, 120, 967, 280, 977, 366, 252, 581, 548};
        Integer[] arr = {17, 12, 21, 10, 15, 19, 27, 14, 16};
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(arr[i]);
            avlTree.iterate();
        }
        System.out.println(Arrays.asList(arr));
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(10);
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(15);
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(17);
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(27);
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(19);
        avlTree.iterate();
        System.out.println("================iterate============");

        avlTree.delete(12);
        avlTree.iterate();
    }
}
