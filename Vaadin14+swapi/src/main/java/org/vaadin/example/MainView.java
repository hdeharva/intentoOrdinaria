package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired GreetService service) {

        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        ComboBox<String> comboBox = new ComboBox<>("Browser");
        comboBox.setAllowCustomValue(false);
        comboBox.setItems("people", "planets", "starships");
        comboBox.setHelperText("Selecciona el tipo de peticion");

        Grid<Character> grid = new Grid<>(Character.class, false);
        grid.addColumn(Character::getName).setHeader("Nombre");
        grid.addColumn(Character::getHeight).setHeader("Altura");
        grid.addColumn(Character::getMass).setHeader("Peso");
        grid.addColumn(Character::getHair_color).setHeader("Color de pelo");
        grid.addColumn(Character::getEye_color).setHeader("Color de ojos");

        TextField requestId = new TextField("Request id:");
        requestId.addThemeName("bordered");

        inputs.add(comboBox, requestId);

        // Button click listeners can be defined as lambda expressions
        Button boton1 = new Button("Lee character",
                e -> {
                   String tipo = comboBox.getValue();
                   int id = Integer.parseInt(requestId.getValue());
                   try {
                      // System.out.println(service.getSWAPI(tipo, id));
                       results.removeAll();
                       results.add(service.getSWAPI(tipo,id));
                   } catch (URISyntaxException ex) {
                       throw new RuntimeException(ex);
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   } catch (InterruptedException ex) {
                       throw new RuntimeException(ex);
                   }
                });

        Button boton2 = new Button("Lee lista de caracteres",
                e -> {
                    String tipo = comboBox.getValue();
                    try {
                        grid.setItems(service.getCharList(tipo));
                        results.add(grid);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });

        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.
        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.
        boton1.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        add(inputs, boton1, boton2, results);
    }

}
