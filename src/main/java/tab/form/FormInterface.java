package tab.form;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public interface FormInterface {
    boolean submit() throws SQLException;
    JPanel createForm();
}
