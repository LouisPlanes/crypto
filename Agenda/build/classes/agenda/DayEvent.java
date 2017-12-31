/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author Khoob
 */
import java.util.EventObject;
    
public class DayEvent extends EventObject {
    public DayEvent(Object source) {
        super(source);
    }
}
