import javax.swing.SwingUtilities;

public class MainInterfaz {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            InterfazPrincipal interfaz =
                    new InterfazPrincipal();

            interfaz.setVisible(true);
        });
    }
}
