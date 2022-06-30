package com.fjh.pretree;
class Node{
     int path;
     int end;
     Node []paths= new Node[26];

}

public class PreTree {
 private Node root;
 public PreTree(){
     this.root = new Node();
 }


    public void insert(String world){
        if(world == null || world.length() == 0) {
            return ;
        }
      char [] chars = world.toCharArray();
        Node node = root;
        node.path++;
       for(int i = 0;i < chars.length;i++){
           int index = (int)(chars[i] - 'a');
           if(node.paths[index] == null){
              node.paths[index] = new Node();
           }
           node = node.paths[index];
           node.path++;
       }
       node.end++;
    }

    /**
     * 查找指定的字符串的出现的次数
     * @param world
     * @return
     */
    public int search(String world){
     if(world == null || world.length() == 0){
         return 0;
     }
     char[]chars = world.toCharArray();
     Node node = root;
     for(int i = 0;i < chars.length ;i++){
         int index  = (int)(chars[i] - 'a');
         if(node.paths[index] == null){
             return 0;
         }
         node  = node.paths[index];
     }
     return node.end;
    }

    /**
     * 查找前缀出现的次数
     */
    public int preIfNum(String world){
        if(world == null || world.length() == 0){
            return 0;
        }
        char[] chars = world.toCharArray();
        Node node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = (int)(chars[i] - 'a');
            if(node.paths[index] == null){
                return 0;
            }
            node = node.paths[index];
        }
        return node.path;
    }

    /**
     * 删除指定的字符串
     * @param world
     */
    public void delete(String world){
        if(search(world) != 0){
            Node node = root;
            node.path--;
            char[]chars = world.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = (int)(chars[i] - 'a');
                if(node.path == 0){
                    node = null;
                    return ;
                }
                node = node.paths[index];
                node.path--;
            }
            node.end--;
        }
    }
    public static void main(String[] args) {
     PreTree preTree = new PreTree();
        String []strings = {"abc","absk","abs","abs","abc"};
        for(int i = 0 ;i < strings.length ; i++){
            preTree.insert(strings[i]);
        }
        preTree.delete("abs");
        System.out.println(preTree.search("abs"));
        System.out.println(preTree.preIfNum("ab"));
    }
}
