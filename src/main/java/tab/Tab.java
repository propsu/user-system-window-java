package tab;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private String name;

    public Tab(String name){
        super(null);
        this.name = name;
        setBounds(0, 0, 500, 500);
        hideTab(true);
    }

    public void enableComponents(boolean state){
        for(Component component : this.getComponents()){
            component.setEnabled(state);
        }
    }

    public void hideTab(boolean state){
        if(state){
            setVisible(false);
            enableComponents(false);
        } else {
            setVisible(true);
            enableComponents(true);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
