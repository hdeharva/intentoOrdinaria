package org.vaadin.example;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    public MainView(@Autowired PokemonService service) {

        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();

        ComboBox<String> comboBox = new ComboBox<>("Selecciona uno...");
        comboBox.setAllowCustomValue(false);
        comboBox.setItems("Todos los pokemon", "Por nombre", "Por tipo");
        comboBox.setHelperText("Selecciona el tipo de petición");

        Grid<Pokemon> grid = new Grid<>(Pokemon.class, true);
        grid.addColumn(Pokemon::getName).setHeader("Nombre");
        grid.addColumn(Pokemon::getAttack).setHeader("Ataque");
        grid.addColumn(Pokemon::getDefense).setHeader("Defensa");
        grid.addColumn(Pokemon::getTipo1).setHeader("Tipo 1");
        grid.addColumn(Pokemon::getTipo2).setHeader("Tipo 2");
        inputs.add(comboBox);

        TextField datos = new TextField("Nombre/Tipo");
        datos.addThemeName("bordered");
        inputs.add(comboBox, datos);

        Button boton1 = new Button("Lee caracter",
                e -> {
                        String tipoPeticion = comboBox.getValue();
                        String dato = datos.getValue();
                        try {
                            results.removeAll();
                            if(tipoPeticion.equals("Por nombre")){
                                results.add(service.leePokemonPorNombre(dato));
                            } else if (tipoPeticion.equals("Por tipo")) {
                                results.add(service.leePokemonPorTipo(dato));
                            }else if (tipoPeticion.equals("Todos los pokemon")){
                               grid.setItems(service.leePokemons());
                                results.add(grid);
                            }
                        } catch (Exception ex) {

                        }
                });
        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);
        addClassName("centered-content");
        add(inputs, boton1, results);


    }

}
