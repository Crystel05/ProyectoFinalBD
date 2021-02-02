package VIEW;

import CONTROLLER.ControllerUi;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;
import java.util.NoSuchElementException;

public class Inicio extends AbsoluteLayout implements View {

    private ControllerUi controller = ControllerUi.getInstance();

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

        ComboBox<String> nombreGiro = new ComboBox<>("NOMBRE GIRO");
        nombreGiro.setWidth("300px");
        nombreGiro.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        nombreGiro.setPlaceholder("FRANCIA");

        ComboBox<Integer> annoGiro = new ComboBox<>("AÑO GIRO");
        annoGiro.setWidth("300px");
        annoGiro.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
        annoGiro.setPlaceholder("1990");
        annoGiro.setItems(controller.mostrarAnnos());
        annoGiro.addSelectionListener(e->{
            try{
                int anno = annoGiro.getSelectedItem().get();
                nombreGiro.setItems(controller.mostrarNombresGiro(anno));
            }
            catch (NoSuchElementException exe){
                Notification.show("No hay año seleccionado", Notification.Type.TRAY_NOTIFICATION);
            }

        });

        if (controller.getAnno() != 0 && controller.getNombreGiro() != null) {
            annoGiro.setItems(controller.mostrarAnnos());
            annoGiro.setValue(controller.getAnno());
            nombreGiro.setItems(controller.mostrarNombresGiro(controller.getAnno()));
            nombreGiro.setValue(controller.getNombreGiro());
        }

        Button verOpciones = new Button("CONSULTAR");
        verOpciones.setWidth("300px");
        verOpciones.setIcon(VaadinIcons.ARCHIVE);
        verOpciones.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        verOpciones.addClickListener(e->{
            if(annoGiro.getSelectedItem().isPresent() && nombreGiro.getSelectedItem().isPresent()){
                controller.setAnno(annoGiro.getSelectedItem().get());
                controller.setNombreGiro(nombreGiro.getSelectedItem().get());
                Notification.show("Ahora puede acceder a las consultas", Notification.Type.TRAY_NOTIFICATION);
            }
            else{
                Notification.show("No seleccionó el año o el nombre del giro", Notification.Type.WARNING_MESSAGE);
            }

        });

        Button opcion1 = new Button("MEJORES CORREDORES");
        opcion1.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion1.setHeight("80px");
        opcion1.setWidth("300px");
        opcion1.addClickListener(e->{
            if((annoGiro.getSelectedItem().isPresent() && nombreGiro.getSelectedItem().isPresent()) || (controller.getAnno() != 0 && controller.getNombreGiro() != null)) {
                Navigator navigator = new Navigator(UI.getCurrent(), this);
                navigator.addView("Opcion1", Opcion1.class);
                navigator.navigateTo("Opcion1");
            }
            else{
                Notification.show("Seleccione los datos antes de contnuar", Notification.Type.WARNING_MESSAGE);
            }
        });

        Button opcion2 = new Button("CORREDORES MÁS REGULARES");
        opcion2.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion2.setHeight("80px");
        opcion2.setWidth("300px");
        opcion2.addClickListener(e->{
            if((annoGiro.getSelectedItem().isPresent() && nombreGiro.getSelectedItem().isPresent()) || (controller.getAnno() != 0 && controller.getNombreGiro() != null)) {
                Navigator navigator = new Navigator(UI.getCurrent(), this);
                navigator.addView("Opcion2", Opcion2.class);
                navigator.navigateTo("Opcion2");
            }
            else{
                Notification.show("Seleccione los datos antes de contnuar", Notification.Type.WARNING_MESSAGE);
            }
        });

        Button opcion3 = new Button("MEJORES PUNTAJES MONTAÑA");
        opcion3.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion3.setHeight("80px");
        opcion3.setWidth("300px");
        opcion3.addClickListener(e->{
            if((annoGiro.getSelectedItem().isPresent() && nombreGiro.getSelectedItem().isPresent()) || (controller.getAnno() != 0 && controller.getNombreGiro() != null)) {
                Navigator navigator = new Navigator(UI.getCurrent(), this);
                navigator.addView("Opcion3", Opcion3.class);
                navigator.navigateTo("Opcion3");
            }
            else{
                Notification.show("Seleccione los datos antes de contnuar", Notification.Type.WARNING_MESSAGE);
            }
        });

        Button opcion4 = new Button("MEJORES EQUIPOS");
        opcion4.addStyleName(ValoTheme.BUTTON_PRIMARY);
        opcion4.setHeight("80px");
        opcion4.setWidth("300px");
        opcion4.addClickListener(e->{
            if((annoGiro.getSelectedItem().isPresent() && nombreGiro.getSelectedItem().isPresent()) || (controller.getAnno() != 0 && controller.getNombreGiro() != null)) {
                Navigator navigator = new Navigator(UI.getCurrent(), this);
                navigator.addView("Opcion4", Opcion4.class);
                navigator.navigateTo("Opcion4");
            }
            else{
                Notification.show("Seleccione los datos antes de contnuar", Notification.Type.WARNING_MESSAGE);
            }
        });

        opciones.addComponent(fondoOpciones);
        opciones.addComponent(label, "left: 620px");
        opciones.addComponent(datos, "top: 75px; left: 550px");
        opciones.addComponent(nombreGiro, "top: 200px; right: 400px");
        opciones.addComponent(annoGiro, "top: 200px; left: 400px");
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
