package pro.bugrim;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    static Random random = new Random();
    static int checkSum = 0;
    static final int levels = 23;

    public static void main(String[] args) {
        System.out.println("generating tree");
        TreeNode root = new TreeNode();
        createTree(root, levels);
        System.out.println("tree created = " + checkSum);

        System.out.println("simple count");
        long t1 = System.currentTimeMillis();
        int weight1= countTreeWeight(root);
        long t2 = System.currentTimeMillis();
        System.out.println("completed, weight =" + weight1 + ", time = " + (t2-t1));

        long t3 = System.currentTimeMillis();
        int weight2 = countFJPTreeWeight(root);
        long t4 = System.currentTimeMillis();
        System.out.println("fork join pool count");
        System.out.println("completed, weight = " + weight2 + ", time = " + (t4-t3));

    }

    private static int countFJPTreeWeight(TreeNode node) {
        class FJCounter extends RecursiveTask<Integer> {

            TreeNode node;

            public FJCounter(TreeNode node) {
                this.node = node;
            }

            @Override
            protected Integer compute() {
                FJCounter wLeft = null;
                FJCounter wRight = null;

                int sum = node.weight;
                if (node.left != null) {
                    wLeft = new FJCounter(node.left);
                    wLeft.fork();
                }
                if (node.right != null) {
                    wRight = new FJCounter(node.right);
                    wRight.fork();
                }

                if (wLeft != null) {
                    sum += wLeft.join();
                }
                if (wRight != null) {
                    sum += wRight.join();
                }
                return sum;
            }
        }

        return new ForkJoinPool().invoke(new FJCounter(node));
    }

    private static int countTreeWeight(TreeNode node) {
        return    node.weight + (node.left != null ? countTreeWeight(node.left) : 0) +
                                (node.right != null ? countTreeWeight(node.right) : 0);
    }

    private static void createTree(TreeNode node, int level) {
        node.left = new TreeNode();
        node.right = new TreeNode();
        node.weight = random.nextInt(10);
        checkSum += node.weight;
        level--;
        if (level == 0) {
            node.left.weight = random.nextInt(10);
            node.right.weight = random.nextInt(10);
            checkSum += node.left.weight;
            checkSum += node.right.weight;
            return;
        }
        createTree(node.left, level);
        createTree(node.right, level);
    }
}

class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int weight;


}
