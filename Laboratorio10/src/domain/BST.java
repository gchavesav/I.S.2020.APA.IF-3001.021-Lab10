/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Profesor Lic. Gilberth Chaves A.
 * BST = Binary Search Tree
 */
public class BST implements Tree {
    private BTreeNode root; //representa la unica entrada al arbol

    //Constructor
    public BST(){
        this.root = null;
    }
         
    
    @Override
    public int size() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return size(root);
    }
    
    private int size(BTreeNode node){
        if(node==null) return 0;
        else
            return 1+size(node.left)+size(node.right);
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch(root, element);
    }
    
    private boolean binarySearch(BTreeNode node, Object element){
        if(node==null) return false;
        else
            if(util.Utility.equals(node.data, element)){
                return true; //YA LO ENCONTRO
            }else
                if(util.Utility.lessT(element, node.data))
                    return binarySearch(node.left, element);
                else return binarySearch(node.right, element);
    }

    @Override
    public void add(Object element) {
        root = add(root, element);
    }
    
    private BTreeNode add(BTreeNode node, Object element){
        if(node==null){ //el arbol esta vacio
            node = new BTreeNode(element);
        }else
            //preguntamos si el elemento a insertar es menor o mayor que node.data
            if(util.Utility.lessT(element, node.data)){
                node.left = add(node.left, element);
            }else //sino inserta por la der
                if(util.Utility.greaterT(element, node.data)){        
                    node.right = add(node.right, element);
                }
        return node; //en todos los casos, retorna un nuevo nodo
    }

    @Override
    public void remove(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        root =remove(root, element);
    }
    
    private BTreeNode remove(BTreeNode node, Object element){
        if(node!=null){
            if(util.Utility.lessT(element, node.data)){
                node.left = remove(node.left, element);
            }else
                if(util.Utility.greaterT(element, node.data)){
                    node.right = remove(node.right, element);
                }else
                    if(util.Utility.equals(node.data, element)){
                        //CASO 1. EL NODO A SUPRIMIR ES UN NODO SIN HIJOS
                        //EN ESTE CASO, EL NODO A SUPRMIR ES UNA HOJA
                        if(node.left==null&&node.right==null){
                            return null;
                        }else
                            //CASO 2. EL NODO A SUPRIMIR SOLO TIENE UN HIJO
                            //EN ESE CASO, EL NODO A SUPRIMIR CON EL DATA A ELIMINAR
                            //ES REEMPLAZADO POR EL HIJO
                            if(node.left==null&&node.right!=null){
                                node = node.right;
                            }else
                                if(node.left!=null&&node.right==null){
                                    node = node.left;
                                }else
                                   //CASO 3 EL NODO A SUPRIMIR TIENE 2 HIJOS
                                    if(node.left!=null&&node.right!=null){
                                        //OBTENGA EL VALOR MIN DEL SUBARBOL DERECHO
                                        Object value =min(node.right);
                                        node.data = value;
                                        node.right = remove(node.right, value);
                                    }
            }//equals(node.data, element))
        }//node!=null
        return node;
    }
    
    private Object getLeaf(BTreeNode node){
        Object aux;
        if(node==null) return null;
        else 
            if(node.left==null&&node.right==null){
                return node.data; //es una hoja
            }else{
                aux = getLeaf(node.left);
                if(aux==null){ 
                  //quiere decir q todavia no ha encontrado una hoja
                  aux = getLeaf(node.right);  
                }
            }
        return aux; 
    }
    
    private BTreeNode removeLeaf(BTreeNode node, Object element){
        if(node==null) return null;
        else
            if(node.left==null&&node.right==null
               &&util.Utility.equals(node.data, element)){
                //ES UNA HOJA Y ES LA QUE ANDO BUSCANDO, 
                //YA LA PUEDO ELIMINAR
                return null;
            }else{
                node.left = removeLeaf(node.left, element);
                node.right = removeLeaf(node.right, element);
        }
        return node;
    }

    @Override
    public int height(Object element) throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root, element, 0);
    }
    private int height(BTreeNode node, Object element, int count){
        if(node==null) return 0;
        else
            if(util.Utility.equals(node.data, element)){
                return count;
            }else
                if(util.Utility.lessT(element, node.data)){
                   return height(node.left, element, ++count); 
                }else
                   return height(node.right, element, ++count);
    }

    @Override
    public int height() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root)-1;
    }
    
    private int height(BTreeNode node){
        if(node==null) return 0;
        else
            return Math.max(
                    height(node.left), 
                    height(node.right))+1;
    }

    @Override
    public Object min() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return min(root);
    }
    
    private Object min(BTreeNode node){
        if(node.left!=null)
            return min(node.left);
        return node.data;
    }

    @Override
    public Object max() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return max(root);
    }
    
    private Object max(BTreeNode node){
        if(node.right!=null)
            return max(node.right);
        return node.data;
    }

    @Override
    public String preOrder() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PreOrder Transversal Tour: "
                +preOrder(root);
    }
    
    //node-left-right
    private String preOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result = node.data+", ";
            result+=preOrder(node.left);
            result+=preOrder(node.right);
        }
        return result;
    }

    @Override
    public String InOrder() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return "InOrder Transversal Tour: "
                +inOrder(root);
    }
    
    //left-node-right
    private String inOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=inOrder(node.left);
            result+=node.data+", ";
            result+=inOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if(isEmpty()){
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PostOrder Transversal Tour: "
                +postOrder(root);
    }
    
    //left-right-node
    private String postOrder(BTreeNode node){
        String result="";
        if(node!=null){
            result=postOrder(node.left);
            result+=postOrder(node.right);
            result+=node.data+", ";
        }
        return result;
    }

    @Override
    public String toString() {
        if(isEmpty()){
            return "Binary Search Tree is empty";
        }
        String result="BINARY SEARCH TREE TOUR...";
        result+="\nPreOrder: "+preOrder(root);
        result+="\nInOrder: "+inOrder(root);
        result+="\nPostOrder: "+postOrder(root);
        return result;
    }
    
}
