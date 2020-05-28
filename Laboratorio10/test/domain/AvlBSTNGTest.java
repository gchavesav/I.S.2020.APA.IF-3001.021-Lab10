/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.annotations.Test;

/**
 *
 * @author Profesor Lic. Gilberth Chaves A.
 */
public class AvlBSTNGTest {
    
    public AvlBSTNGTest() {
    }

    @Test
    public void testSomeMethod() {
        try {
            AvlBST avl = new AvlBST();
            //agrego los elementos: 1 hasta 7
            for (int i = 1; i <= 7; i++) {
                avl.add(i);
            }
            //agrego los elementos: 15 hasta 8 descendente
            for (int i = 15; i >= 8; i--) {
                avl.add(i);
            }
            System.out.println(avl.toString());

                System.out.println(avl.isBalanced()
                        ?"El arbol de busqueda binaria AVL esta balanceado"
                        :"El arbol de busqueda binaria AVL no esta balanceado"
                );
                System.out.println("SECUENCIA DE ROTACIONES\n"
                        +avl.sequence());
                
        } catch (TreeException ex) {
            Logger.getLogger(AvlBSTNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
