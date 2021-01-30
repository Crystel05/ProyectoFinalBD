package VIEW;

import MODELO.MejorCorredor;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

public class Opcion3 extends AbsoluteLayout implements View {

    public Opcion3() {

        ventana();
    }

    private void ventana() {

        HorizontalLayout fondo = new HorizontalLayout();
        fondo.setSizeFull();

        FileResource resource = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoTotal.png"));
        Image fondoTotal = new Image("", resource);
        fondoTotal.setSizeFull();

        AbsoluteLayout mejoresCorredoresMontana = new AbsoluteLayout();
        mejoresCorredoresMontana.setWidth("1500px");
        mejoresCorredoresMontana.setHeight("700px");

        FileResource resource1 = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoOpciones.png"));
        Image fondoMejoresMontana = new Image("", resource1);
        fondoMejoresMontana.setHeight("700px");
        fondoMejoresMontana.setWidth("1500px");

        Label datos = new Label("LOS 10 MEJORES");
        datos.addStyleName(ValoTheme.LABEL_H2);

        Label datos2 = new Label("CORREDORES MONTAÑA");
        datos2.addStyleName(ValoTheme.LABEL_H2);

        Grid<MejorCorredor> mejoresCorredores1 = new Grid<>(MejorCorredor.class);
        mejoresCorredores1.removeColumn("tiempoAcumulado");
        mejoresCorredores1.getColumn("posicionFinal").setCaption("POSICIÓN");
        mejoresCorredores1.getColumn("nombre").setCaption("NOMBRE");
        mejoresCorredores1.getColumn("cantPuntos").setCaption("CANTIDAD PUNTOS");
        mejoresCorredores1.setHeight("228px");

        Grid<MejorCorredor> mejoresCorredores2 = new Grid<>(MejorCorredor.class);
        mejoresCorredores2.removeColumn("tiempoAcumulado");
        mejoresCorredores2.getColumn("posicionFinal").setCaption("POSICIÓN");
        mejoresCorredores2.getColumn("nombre").setCaption("NOMBRE");
        mejoresCorredores2.getColumn("cantPuntos").setCaption("CANTIDAD PUNTOS");
        mejoresCorredores2.setHeight("228px");

        Button atras = new Button();
        atras.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        atras.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        atras.setIcon(VaadinIcons.LEVEL_LEFT_BOLD);
        atras.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Inicio", Inicio.class);
            navigator.navigateTo("Inicio");
        });

        mejoresCorredoresMontana.addComponent(mejoresCorredores1, "top: 250px; left: 150px");
        mejoresCorredoresMontana.addComponent(mejoresCorredores2, "top: 250px; right: 150px");
        mejoresCorredoresMontana.addComponent(fondoMejoresMontana);
        mejoresCorredoresMontana.addComponent(datos, "top: 25px; left: 670px");
        mejoresCorredoresMontana.addComponent(datos2, "top: 50px; left: 620px");
        mejoresCorredoresMontana.addComponent(atras, "top: 650px; right: 50px");


        fondo.addComponent(mejoresCorredoresMontana);
        fondo.setComponentAlignment(mejoresCorredoresMontana, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondo);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
