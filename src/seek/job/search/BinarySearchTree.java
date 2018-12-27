package seek.job.search;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by likoguan on 24/12/18.
 */
class TreeNode {
    public Integer key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    public TreeNode(Integer key, TreeNode left, TreeNode right, TreeNode parent) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

public class BinarySearchTree {
    public TreeNode root;

    public TreeNode add(Integer key) {
        if (root == null) {
            return root = new TreeNode(key, null, null, null);
        } else {
            return add(key, root);
        }
    }

    public TreeNode get(Integer key) {
        return get(key, root);
    }

    public Integer max() {
        TreeNode node = max(root);
        return node != null ? node.key : null;
    }

    public Integer min() {
        TreeNode node = min(root);
        return node != null ? node.key : null;
    }

    public void iterate() {
        iterate(root);
    }

    public void delete(Integer key) {
        TreeNode target = get(key, root);
        if (target != null) {
            delete(target);
        }
    }

    private void delete(TreeNode node) {
        if (node.left == null || node.right == null) {
            TreeNode dst = null;
            if (node.left != null) {
                dst = node.left;
            }
            if (node.right != null) {
                dst = node.right;
            }
            replace(node, dst);
        } else {
            TreeNode successor = min(node.right);//node后继successor
            replace(successor, successor.right);
            replace(node, successor);

            successor.left = node.left;
            if (successor.left != null)
                successor.left.parent = successor;

            successor.right = node.right;
            if (successor.right != null)
                successor.right.parent = successor;
        }
    }

    //以v节点替换以u节点，让u的双亲节点变为v的双亲节点
    private void replace(TreeNode u, TreeNode v) {
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

    private TreeNode add(Integer key, TreeNode tree) {
        if (key <= tree.key) {
            if (tree.left == null) {
                tree.left = new TreeNode(key, null, null, tree);
            } else {
                tree.left = add(key, tree.left);
            }
        } else {
            if (tree.right == null) {
                tree.right = new TreeNode(key, null, null, tree);
            } else {
                tree.right = add(key, tree.right);
            }
        }
        return tree;
    }

    private TreeNode get(Integer key, TreeNode tree) {
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

    private TreeNode max(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        if (tree.right != null) {
            return max(tree.right);
        } else {
            return tree;
        }
    }

    private TreeNode min(TreeNode tree) {
        if (tree == null) {
            return null;
        }

        if (tree.left != null) {
            return min(tree.left);
        } else {
            return tree;
        }
    }

    private void iterate(TreeNode tree) {
        if (tree.left != null) {
            iterate(tree.left);
        }

        System.out.println(tree.key);

        if (tree.right != null) {
            iterate(tree.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Integer[] arr = new Integer[10];
        Random random = new Random();
        for (int i=0; i<10; i++) {
            arr[i] = random.nextInt(100);
            bst.add(arr[i]);
        }
        System.out.println(Arrays.asList(arr));

        System.out.println("================iterate============");
        bst.iterate();

        System.out.println("=================get=============");
        System.out.println(bst.get(arr[4]));
        System.out.println(bst.get(101));

        System.out.println("================max&min==============");
        System.out.println(bst.max());
        System.out.println(bst.min());

        System.out.println("==============delete=============");
        bst.delete(arr[4]);
        bst.iterate();
    }
}
