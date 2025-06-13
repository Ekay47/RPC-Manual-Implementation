package test;

import java.io.*;

public class Main {
    static class Node {
        int type;
        String content;
        Node globalPrev, globalNext;
        Node typePrev, typeNext;

        public Node() {}

        public Node(int type, String content) {
            this.type = type;
            this.content = content;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());

        Node head = new Node();
        Node tail = new Node();
        head.globalNext = tail;
        tail.globalPrev = head;

        Node[] typeHeads = new Node[101];
        Node[] typeTails = new Node[101];
        for (int i = 1; i <= 100; i++) {
            typeHeads[i] = new Node();
            typeTails[i] = new Node();
            typeHeads[i].typeNext = typeTails[i];
            typeTails[i].typePrev = typeHeads[i];
        }

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] parts = line.split(" ");
            if (parts[0].equals("in")) {
                int t = Integer.parseInt(parts[1]);
                String s = parts[2];
                Node newNode = new Node(t, s);

                newNode.globalPrev = tail.globalPrev;
                newNode.globalNext = tail;
                tail.globalPrev.globalNext = newNode;
                tail.globalPrev = newNode;

                newNode.typePrev = typeTails[t].typePrev;
                newNode.typeNext = typeTails[t];
                typeTails[t].typePrev.typeNext = newNode;
                typeTails[t].typePrev = newNode;

            } else if (parts[0].equals("out")) {
                int p = Integer.parseInt(parts[1]);
                if (p == 0) {
                    if (head.globalNext == tail) {
                        out.println("-1");
                    } else {
                        Node node = head.globalNext;
                        out.println(node.content);
                        removeNode(node);
                    }
                } else {
                    if (typeHeads[p].typeNext == typeTails[p]) {
                        out.println("-1");
                    } else {
                        Node node = typeHeads[p].typeNext;
                        out.println(node.content);
                        removeNode(node);
                    }
                }
            }
        }
        out.flush();
    }

    private static void removeNode(Node node) {
        node.globalPrev.globalNext = node.globalNext;
        node.globalNext.globalPrev = node.globalPrev;

        node.typePrev.typeNext = node.typeNext;
        node.typeNext.typePrev = node.typePrev;
    }
}
