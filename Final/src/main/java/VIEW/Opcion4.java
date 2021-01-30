package VIEW;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

public class Opcion4 extends AbsoluteLayout implements View {

    public Opcion4() {

        ventana();

    }

    private void ventana() {

        HorizontalLayout fondo = new HorizontalLayout();
        fondo.setSizeFull();

        FileResource resource = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoTotal.png"));
        Image fondoTotal = new Image("", resource);
        fondoTotal.setSizeFull();

        AbsoluteLayout mejoresEquipos = new AbsoluteLayout();
        mejoresEquipos.setWidth("1500px");
        mejoresEquipos.setHeight("700px");

        FileResource resource1 = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoOpciones.png"));
        Image fondoEquipos = new Image("", resource1);
        fondoEquipos.setHeight("700px");
        fondoEquipos.setWidth("1500px");

        FileResource resource2 = new FileResource(new File("src/main/java/VIEW/Imagenes/PosEquipos.png"));
        Image posciones = new Image("", resource2);
        posciones.setWidth("1500px");

        Label datos = new Label("LOS 3 MEJORES EQUIPOS");
        datos.addStyleName(ValoTheme.LABEL_H2);

        Label datos2 = new Label("SEGÚN SU TIEMPO");
        datos2.addStyleName(ValoTheme.LABEL_H2);

        Label nombreE1 = new Label("LOS LOCOS");
        nombreE1.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE1.addStyleName(ValoTheme.LABEL_H3);
        nombreE1.addStyleName(ValoTheme.LABEL_COLORED);
        Label tiempoAcumuladoE1 = new Label("2 horas 30 min");
        tiempoAcumuladoE1.addStyleName(ValoTheme.LABEL_BOLD);

        Label nombreE2 = new Label("LOS REYES");
        nombreE2.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE2.addStyleName(ValoTheme.LABEL_H3);
        nombreE2.addStyleName(ValoTheme.LABEL_COLORED);
        Label tiempoAcumuladoE2 = new Label("1 hora 2 min");
        tiempoAcumuladoE2.addStyleName(ValoTheme.LABEL_BOLD);

        Label nombreE3 = new Label("LOS MEJORES");
        nombreE3.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE3.addStyleName(ValoTheme.LABEL_H3);
        nombreE3.addStyleName(ValoTheme.LABEL_COLORED);
        Label tiempoAcumuladoE3 = new Label("0 horas 50 min");
        tiempoAcumuladoE3.addStyleName(ValoTheme.LABEL_BOLD);

        Button atras = new Button();
        atras.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        atras.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        atras.setIcon(VaadinIcons.LEVEL_LEFT_BOLD);
        atras.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Inicio", Inicio.class);
            navigator.navigateTo("Inicio");
        });


        mejoresEquipos.addComponent(fondoEquipos);
        mejoresEquipos.addComponent(posciones);
        mejoresEquipos.addComponent(datos, "top: 25px; left: 650px");
        mejoresEquipos.addComponent(datos2, "top: 50px; left: 680px");
        mejoresEquipos.addComponent(nombreE1, "top: 175px; left: 700px");
        mejoresEquipos.addComponent(tiempoAcumuladoE1, "top: 250px; left: 700px");
        mejoresEquipos.addComponent(nombreE2, "top: 240px; left: 410px");
        mejoresEquipos.addComponent(tiempoAcumuladoE2, "top: 315px; left: 410px");
        mejoresEquipos.addComponent(nombreE3, "top: 260px; right: 400px");
        mejoresEquipos.addComponent(tiempoAcumuladoE3, "top: 335px; right: 400px");
        mejoresEquipos.addComponent(atras, "top: 650px; right: 50px");

        fondo.addComponent(mejoresEquipos);
        fondo.setComponentAlignment(mejoresEquipos, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondo);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
