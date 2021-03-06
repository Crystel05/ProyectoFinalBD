package VIEW;

import CONTROLLER.ControllerUi;
import MODELO.MejorCorredor;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;
import java.util.ArrayList;

public class Opcion1 extends AbsoluteLayout implements View {

    ControllerUi controller = ControllerUi.getInstance();

    public Opcion1() {

        ventana();

    }

    private void ventana() {
        HorizontalLayout fondo = new HorizontalLayout();
        fondo.setSizeFull();

        FileResource resource = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoTotal.png"));
        Image fondoTotal = new Image("", resource);
        fondoTotal.setSizeFull();

        AbsoluteLayout mejoresCorredoresGiro = new AbsoluteLayout();
        mejoresCorredoresGiro.setWidth("1500px");
        mejoresCorredoresGiro.setHeight("700px");

        FileResource resource1 = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoOpciones.png"));
        Image fondoMejoresGiro = new Image("", resource1);
        fondoMejoresGiro.setHeight("700px");
        fondoMejoresGiro.setWidth("1500px");

        Label mejor = new Label("MEJORES 10 CORREDORES");
        mejor.addStyleName(ValoTheme.LABEL_H2);

        Label mejor2 = new Label("EN EL GIRO");
        mejor2.addStyleName(ValoTheme.LABEL_H2);

        ArrayList<MejorCorredor> mejoresC = controller.mejoresGiro(controller.getAnno(), controller.getNombreGiro());

        Grid<MejorCorredor> mejoresGiro1 = new Grid(MejorCorredor.class);
        mejoresGiro1.setHeight("228px");
        mejoresGiro1.setWidth("600px");
        mejoresGiro1.setColumnOrder("posicionFinal", "nombre", "tiempo");
        mejoresGiro1.removeColumn("cantPuntos");
        mejoresGiro1.getColumn("posicionFinal").setCaption("POSICIÓN");
        mejoresGiro1.getColumn("nombre").setCaption("NOMBRE");
        mejoresGiro1.getColumn("tiempo").setCaption("TIEMPO");
        mejoresGiro1.setItems(mejoresC.subList(0, 5));

        Grid<MejorCorredor> mejoresGiro2 = new Grid<>(MejorCorredor.class);
        mejoresGiro2.setHeight("228px");
        mejoresGiro2.setWidth("600px");
        mejoresGiro2.setColumnOrder("posicionFinal", "nombre", "tiempo");
        mejoresGiro2.getColumn("posicionFinal").setCaption("POSICIÓN");
        mejoresGiro2.getColumn("nombre").setCaption("NOMBRE");
        mejoresGiro2.removeColumn("cantPuntos");
        mejoresGiro2.getColumn("tiempo").setCaption("TIEMPO");
        mejoresGiro2.setItems(mejoresC.subList(5,10));

        Button atras = new Button();
        atras.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        atras.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        atras.setIcon(VaadinIcons.LEVEL_LEFT_BOLD);
        atras.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Inicio", Inicio.class);
            navigator.navigateTo("Inicio");
        });


        mejoresCorredoresGiro.addComponent(mejoresGiro1, "top: 250px; left: 100px");
        mejoresCorredoresGiro.addComponent(mejoresGiro2, "top: 250px; right: 100px");
        mejoresCorredoresGiro.addComponent(fondoMejoresGiro);
        mejoresCorredoresGiro.addComponent(mejor, "top: 25px; left: 650px");
        mejoresCorredoresGiro.addComponent(mejor2, "top: 50px; left: 720px");
        mejoresCorredoresGiro.addComponent(atras, "top: 650px; right: 50px");

        fondo.addComponent(mejoresCorredoresGiro);
        fondo.setComponentAlignment(mejoresCorredoresGiro, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondo);
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
