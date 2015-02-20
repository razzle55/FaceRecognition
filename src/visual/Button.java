/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import javax.swing.JButton;

/**
 *
 * @author Shamricov
 */
public class Button extends JButton
{

    public Button(String text, int x, int y)
    {
        super(text);
        setLocation(x, y);
        setSize(120, 20);
        setVisible(true);
    }
}
