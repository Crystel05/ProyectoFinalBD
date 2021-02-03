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

public class Opcion4 extends AbsoluteLayout implements View {

    private ControllerUi controller = ControllerUi.getInstance();

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

        ArrayList<MejorCorredor> equiposGanadores = controller.mejoresEquipos(controller.getAnno(), controller.getNombreGiro());

        AbsoluteLayout eq1 = new AbsoluteLayout();
        eq1.setHeight("140px");
        eq1.setWidth("250px");

        VerticalLayout eq1V = new VerticalLayout();
        eq1V.setHeight("140px");
        eq1V.setWidth("250px");

        Label nombreE1 = new Label(equiposGanadores.get(0).getNombre());
        nombreE1.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE1.addStyleName(ValoTheme.LABEL_H3);
        nombreE1.addStyleName(ValoTheme.LABEL_COLORED);

        Label tiempoAcumuladoE1 = new Label(equiposGanadores.get(0).getTiempo());
        tiempoAcumuladoE1.addStyleName(ValoTheme.LABEL_BOLD);
        tiempoAcumuladoE1.addStyleName(ValoTheme.LABEL_COLORED);

        eq1V.addComponents(nombreE1, tiempoAcumuladoE1);
        eq1V.setComponentAlignment(nombreE1, Alignment.TOP_CENTER);
        eq1V.setComponentAlignment(tiempoAcumuladoE1, Alignment.MIDDLE_CENTER);
        eq1.addComponent(eq1V);

        AbsoluteLayout eq2 = new AbsoluteLayout();
        eq2.setHeight("130px");
        eq2.setWidth("250px");

        VerticalLayout eq2V = new VerticalLayout();
        eq2V.setWidth("250px");
        eq2V.setHeight("130px");

        Label nombreE2 = new Label(equiposGanadores.get(1).getNombre());
        nombreE2.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE2.addStyleName(ValoTheme.LABEL_H3);
        nombreE2.addStyleName(ValoTheme.LABEL_COLORED);

        Label tiempoAcumuladoE2 = new Label(equiposGanadores.get(1).getTiempo());
        tiempoAcumuladoE2.addStyleName(ValoTheme.LABEL_BOLD);
        tiempoAcumuladoE2.addStyleName(ValoTheme.LABEL_COLORED);

        eq2V.addComponents(nombreE2, tiempoAcumuladoE2);
        eq2V.setComponentAlignment(nombreE2, Alignment.TOP_CENTER);
        eq2V.setComponentAlignment(tiempoAcumuladoE2, Alignment.MIDDLE_CENTER);
        eq2.addComponent(eq2V);

        AbsoluteLayout eq3 = new AbsoluteLayout();
        eq3.setWidth("250px");
        eq3.setHeight("135px");

        VerticalLayout eq3V = new VerticalLayout();
        eq3V.setWidth("250px");
        eq3V.setHeight("135px");

        Label nombreE3 = new Label(equiposGanadores.get(2).getNombre());
        nombreE3.addStyleName(ValoTheme.LABEL_BOLD);
        nombreE3.addStyleName(ValoTheme.LABEL_H3);
        nombreE3.addStyleName(ValoTheme.LABEL_COLORED);

        Label tiempoAcumuladoE3 = new Label(equiposGanadores.get(2).getTiempo());
        tiempoAcumuladoE3.addStyleName(ValoTheme.LABEL_BOLD);
        tiempoAcumuladoE3.addStyleName(ValoTheme.LABEL_COLORED);

        eq3V.addComponents(nombreE3, tiempoAcumuladoE3);
        eq3V.setComponentAlignment(nombreE3, Alignment.TOP_CENTER);
        eq3V.setComponentAlignment(tiempoAcumuladoE3, Alignment.MIDDLE_CENTER);
        eq3.addComponent(eq3V);

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
        mejoresEquipos.addComponent(eq1, "top: 170px; left: 626px");
        mejoresEquipos.addComponent(eq2, "top: 245px; left: 327px");
        mejoresEquipos.addComponent(eq3, "top: 260px; left: 926px");
        mejoresEquipos.addComponent(atras, "top: 650px; right: 50px");

        fondo.addComponent(mejoresEquipos);
        fondo.setComponentAlignment(mejoresEquipos, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondo);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
