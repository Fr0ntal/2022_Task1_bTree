import java.lang.reflect.Array;
import java.security.KeyStore;
import java.util.*;

public class Tree {
    private Node rootNode;

    public Tree() {
        rootNode = null;
    }

    public Tree(int... integers) {
        for (int i : integers) add(i);
    }

    public Node contains(int value) {
        if (this.rootNode == null) {
            return null;
        }
        Node currentNode = rootNode;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    public void add(int value) {
        Node newNode = new Node(value);
        if (rootNode == null) {
            rootNode = newNode;
        } else {
            Node currentNode = rootNode;
            Node parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {
                    return;
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    public void remove(int val) {
        if (this.rootNode == null) return;
        this.rootNode = remove(this.rootNode, val);
    }

    private Node remove(Node node, int value) {
        if (node == null) return new Node(value);
        if (node.getValue() > value) {
            node.setLeftChild(remove(node.getLeftChild(), value));
        } else if (node.getValue() < value) {
            node.setRightChild(remove(node.getRightChild(), value));
        } else {
            if (node.getLeftChild() == null || node.getRightChild() == null) {
                node = (node.getLeftChild() == null) ? node.getRightChild() : node.getLeftChild();
            } else {
                Node mostLeftChild = mostLeftChild(node.getRightChild());
                node.setValue(mostLeftChild.getValue());
                node.setRightChild(remove(node.getRightChild(), node.getValue()));
            }
        }
        if (node != null) {
            node = rebalanceTree(node);
        }
        return node;
    }

    private Node rotateLeftSmall (Node node) {
        Node b = node.getLeftChild();
        Node c = node.getRightChild();
        Node d = node.getRightChild().getLeftChild();
        Node e = node.getRightChild().getRightChild();

        Node n = new Node(c.getValue());
        n.setLeftChild(node);
        n.setRightChild(e);
        n.getLeftChild().setLeftChild(b);
        n.getLeftChild().setRightChild(d);

        return n;
    }

    private Node rotateLeftLarge (Node node) {
        Node b = node.getLeftChild();
        Node c = node.getRightChild();
        Node d = node.getRightChild().getLeftChild();
        Node e = node.getRightChild().getRightChild();
        Node f = node.getRightChild().getLeftChild().getLeftChild();
        Node g = node.getRightChild().getLeftChild().getRightChild();

        Node n = new Node(d.getValue());
        n.setLeftChild(node);
        n.setRightChild(c);
        n.getLeftChild().setLeftChild(b);
        n.getLeftChild().setRightChild(f);
        n.getRightChild().setLeftChild(g);
        n.getRightChild().setRightChild(e);

        return n;
    }

    private Node rotateRightSmall (Node node) {
        Node b = node.getLeftChild();
        Node c = node.getRightChild();
        Node d = node.getLeftChild().getLeftChild();
        Node e = node.getLeftChild().getRightChild();

        Node n = new Node(b.getValue());
        n.setRightChild(node);
        n.setLeftChild(d);
        n.getRightChild().setLeftChild(e);
        n.getRightChild().setRightChild(c);

        return n;
    }

    private Node rotateRightLarge (Node node) {
        Node b = node.getLeftChild();
        Node c = node.getRightChild();
        Node d = node.getLeftChild().getLeftChild();
        Node e = node.getLeftChild().getRightChild();
        Node f = node.getLeftChild().getRightChild().getLeftChild();
        Node g = node.getLeftChild().getRightChild().getRightChild();

        Node n = new Node(e.getValue());
        n.setLeftChild(b);
        n.setRightChild(node);
        n.getLeftChild().setLeftChild(d);
        n.getLeftChild().setRightChild(f);
        n.getRightChild().setLeftChild(g);
        n.getRightChild().setRightChild(c);

        return n;
    }

    private Node rebalanceTree (Node node) {
        if (node.getRightChild() != null) {
            if (getHeight(node.getRightChild()) - getHeight(node.getLeftChild()) == 2) {
                if (getHeight(node.getRightChild().getLeftChild()) <= getHeight(node.getRightChild().getRightChild())) {
                    node = rotateLeftSmall(node);
                } else {
                    node = rotateLeftLarge(node);
                }
            }
        }
        if (node.getLeftChild() != null) {
            if (getHeight(node.getLeftChild()) - getHeight(node.getRightChild()) == 2) {
                if (getHeight(node.getLeftChild().getRightChild()) <= getHeight(node.getLeftChild().getLeftChild())) {
                    node = rotateRightSmall(node);
                } else {
                    node =  rotateRightLarge(node);
                }
            }
        }
        return node;
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
    }

    private Node mostLeftChild(Node node) {
        Node current = node;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stack<Node> nodesStack = new Stack<>();
        Node currentNode = this.rootNode;
        nodesStack.push(currentNode);
        addLeftBranch(currentNode.getLeftChild(), nodesStack);
        while(!nodesStack.isEmpty()) {
            currentNode = nodesStack.pop();
            Node workOutNode = currentNode;
            addLeftBranch(workOutNode.getRightChild(), nodesStack);
            sb.append(currentNode.getValue());
            sb.append(" ");
        }
        sb.delete(sb.length()-1, sb.length());
        return sb.toString();
    }

    private void addLeftBranch(Node n, Stack<Node> nodesStack) {
        if (n != null) {
            nodesStack.push(n);
            addLeftBranch(n.getLeftChild(), nodesStack);
        }
    }
}