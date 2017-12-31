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
import java.util.EventListener;

public interface DayListener extends EventListener {
    public void day(DayEvent e);
}
