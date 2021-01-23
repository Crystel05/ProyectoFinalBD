package VIEW;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

public class Inicio extends AbsoluteLayout implements View {

    public Inicio() {

        ventana();

    }

    public void ventana(){
        HorizontalLayout fondoFinal = new HorizontalLayout();
        fondoFinal.setSizeFull();

        FileResource resource = new FileResource(new File("src/main/java/VIEW/Imagenes/pruebas.png"));
        Image fondoTotal = new Image("", resource);
        fondoTotal.setSizeFull();

        AbsoluteLayout opciones = new AbsoluteLayout();
        opciones.setWidth("1500px");
        opciones.setHeight("700px");

        FileResource resource1 = new FileResource(new File("src/main/java/VIEW/Imagenes/pruebas2.png"));
        Image fondoOpciones = new Image("", resource1);
        fondoOpciones.setHeight("700px");
        fondoOpciones.setWidth("1500px");

        Label label = new Label("GIRO CICLÍSTICO");
        label.addStyleName(ValoTheme.LABEL_H1);

        Label datos = new Label("Ingrese los datos solicitados para hacer consultas");
        datos.addStyleName(ValoTheme.LABEL_H3);

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
        verOpciones.addStyleName(ValoTheme.BUTTON_PRIMARY);

        Button opcion1 = new Button("MEJORES CORREDORES");
        opcion1.setHeight("80px");
        opcion1.setWidth("300px");
        opcion1.setEnabled(false);

        Button opcion2 = new Button("CORREDORES MÁS REGULARES");
        opcion2.setHeight("80px");
        opcion2.setWidth("300px");
        opcion2.setEnabled(false);

        Button opcion3 = new Button("MEJORES PUNTAJES MONTAÑA");
        opcion3.setHeight("80px");
        opcion3.setWidth("300px");
        opcion3.setEnabled(false);

        Button opcion4 = new Button("MEJORES EQUIPOS");
        opcion4.setHeight("80px");
        opcion4.setWidth("300px");
        opcion4.setEnabled(false);

        verOpciones.addClickListener(e-> {
            //comprobar que los datos sean correctos:
            opcion1.setEnabled(true);
            opcion2.setEnabled(true);
            opcion3.setEnabled(true);
            opcion4.setEnabled(true);
        });

        opciones.addComponent(fondoOpciones);
        opciones.addComponent(label, "top: 25px; left: 620px");
        opciones.addComponent(datos, "top: 100px; left: 75px");
        opciones.addComponent(nombreGiro, "top: 200px; left: 400px");
        opciones.addComponent(annoGiro, "top: 200px; right: 400px");
        opciones.addComponent(verOpciones, "top: 250px; left: 600px");
        opciones.addComponent(opcion1, "top: 350px; left: 300px");
        opciones.addComponent(opcion2, "top: 350px; right: 300px");
        opciones.addComponent(opcion3, "top: 500px; left: 200px");
        opciones.addComponent(opcion4, "top: 500px; right: 200px");

        fondoFinal.addComponent(opciones);
        fondoFinal.setComponentAlignment(opciones, Alignment.MIDDLE_CENTER);

        addComponent(fondoTotal);
        addComponent(fondoFinal);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {}
}
