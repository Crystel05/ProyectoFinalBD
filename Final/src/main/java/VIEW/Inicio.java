package VIEW;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import CONTROLLER.ControllerConexion;

import java.io.File;

public class Inicio extends AbsoluteLayout implements View {

    public Inicio() {

        ventana();

    }

    public void ventana(){
        HorizontalLayout fondoFinal = new HorizontalLayout();
        fondoFinal.setSizeFull();

        FileResource resource = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoTotal.png"));
        Image fondoTotal = new Image("", resource);
        fondoTotal.setSizeFull();

        AbsoluteLayout opciones = new AbsoluteLayout();
        opciones.setWidth("1500px");
        opciones.setHeight("700px");

        FileResource resource1 = new FileResource(new File("src/main/java/VIEW/Imagenes/fondoOpciones.png"));
        Image fondoOpciones = new Image("", resource1);
        fondoOpciones.setHeight("700px");
        fondoOpciones.setWidth("1500px");

        Label label = new Label("GIRO CICLÍSTICO");
        label.addStyleName(ValoTheme.LABEL_H1);

        Label datos = new Label("Ingrese los datos solicitados para hacer consultas");
        datos.addStyleName(ValoTheme.LABEL_H4);

        TextField nombreGiro = new TextField("NOMBRE GIRO");
        nombreGiro.setWidth("300px");
        nombreGiro.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        nombreGiro.setPlaceholder("FRANCIA");

        TextField annoGiro = new TextField("AÑO GIRO");
        annoGiro.setWidth("300px");
        annoGiro.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        annoGiro.setPlaceholder("1990");

        Button verOpciones = new Button("CONSULTAR");
        verOpciones.setWidth("300px");
        verOpciones.setIcon(VaadinIcons.ARCHIVE);
        verOpciones.addStyleName(ValoTheme.BUTTON_BORDERLESS);

        Button opcion1 = new Button("MEJORES CORREDORES");
        opcion1.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion1.setHeight("80px");
        opcion1.setWidth("300px");
        opcion1.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Opcion1", Opcion1.class);
            navigator.navigateTo("Opcion1");
        });

        Button opcion2 = new Button("CORREDORES MÁS REGULARES");
        opcion2.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion2.setHeight("80px");
        opcion2.setWidth("300px");
        opcion2.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Opcion2", Opcion2.class);
            navigator.navigateTo("Opcion2");
        });

        Button opcion3 = new Button("MEJORES PUNTAJES MONTAÑA");
        opcion3.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion3.setHeight("80px");
        opcion3.setWidth("300px");
        opcion3.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Opcion3", Opcion3.class);
            navigator.navigateTo("Opcion3");
        });

        Button opcion4 = new Button("MEJORES EQUIPOS");
        opcion4.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion4.setHeight("80px");
        opcion4.setWidth("300px");
        opcion4.addClickListener(e->{
            Navigator navigator = new Navigator(UI.getCurrent(), this);
            navigator.addView("Opcion4", Opcion4.class);
            navigator.navigateTo("Opcion4");
        });

        verOpciones.addClickListener(e-> {
            //comprobar que los datos sean correctos:

        });

        opciones.addComponent(fondoOpciones);
        opciones.addComponent(label, "left: 620px");
        opciones.addComponent(datos, "top: 75px; left: 550px");
        opciones.addComponent(nombreGiro, "top: 200px; left: 400px");
        opciones.addComponent(annoGiro, "top: 200px; right: 400px");
        opciones.addComponent(verOpciones, "top: 300px; left: 600px");
        opciones.addComponent(opcion1, "top: 400px; left: 75px");
        opciones.addComponent(opcion2, "top: 400px; left: 425px");
        opciones.addComponent(opcion3, "top: 400px; right: 425px");
        opciones.addComponent(opcion4, "top: 400px; right: 75px");

        fondoFinal.addComponent(opciones);
        fondoFinal.setComponentAlignment(opciones, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondoFinal);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
