package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

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

    private final VerticalLayout content;
    private final Tab pokemonTab;
    private final Tab tipoTab;
    private final Tab nombreTab;

    Grid<Pokemon> grid;
    VerticalLayout inputsTipoV;
    VerticalLayout inputsNombreV;
    public MainView(@Autowired PokemonService service) {

        VerticalLayout layout = new VerticalLayout();
        inputsTipoV = new VerticalLayout();
        inputsNombreV = new VerticalLayout();
        HorizontalLayout inputsTipo = new HorizontalLayout();
        HorizontalLayout inputsNombre = new HorizontalLayout();
        VerticalLayout titulos = new VerticalLayout();
        content = new VerticalLayout();

        //layout.setWidth("100%");
        //inputsNombre.setWidth("100%");
        //inputsTipo.setWidth("100%");
        //titulos.setWidth("100%");


        H1 titulo = new H1("Pokedex");
        H2 subtitulo = new H2("Seleccione una pestaña para ver todos los Pokemons, buscar por nombre o por tipo");
        titulo.getStyle().set("align-self", "center").set("margin", "10px");
        subtitulo.getStyle().set("align-self", "center").set("margin","2px");
        titulos.add(titulo, subtitulo);

        ArrayList<Pokemon> listaPokemonsNombre = new ArrayList<Pokemon>();


        grid = new Grid<>(Pokemon.class, false);
        grid.addColumn(Pokemon::getName).setHeader("Nombre").setSortable(true);
        grid.addColumn(Pokemon::getAttack).setHeader("Ataque").setSortable(true);
        grid.addColumn(Pokemon::getDefense).setHeader("Defensa").setSortable(true);
        grid.addColumn(Pokemon::getTipo1).setHeader("Tipo 1").setSortable(true);
        grid.addColumn(Pokemon::getTipo2).setHeader("Tipo 2").setSortable(true);
        grid.getStyle().set("margin", "10px");
        grid.setHeight("700px");

        Grid<Pokemon> gridTipo = new Grid<>(Pokemon.class, false);
        gridTipo.addColumn(Pokemon::getName).setHeader("Nombre").setSortable(true);
        gridTipo.addColumn(Pokemon::getAttack).setHeader("Ataque").setSortable(true);
        gridTipo.addColumn(Pokemon::getDefense).setHeader("Defensa").setSortable(true);
        gridTipo.addColumn(Pokemon::getTipo1).setHeader("Tipo 1").setSortable(true);
        gridTipo.addColumn(Pokemon::getTipo2).setHeader("Tipo 2").setSortable(true);
        gridTipo.getStyle().set("margin", "10px");
        gridTipo.setHeight("700px");

        Grid<Pokemon> gridNombre = new Grid<>(Pokemon.class, false);
        gridNombre.addColumn(Pokemon::getName).setHeader("Nombre").setSortable(true);
        gridNombre.addColumn(Pokemon::getAttack).setHeader("Ataque").setSortable(true);
        gridNombre.addColumn(Pokemon::getDefense).setHeader("Defensa").setSortable(true);
        gridNombre.addColumn(Pokemon::getTipo1).setHeader("Tipo 1").setSortable(true);
        gridNombre.addColumn(Pokemon::getTipo2).setHeader("Tipo 2").setSortable(true);
        gridTipo.getStyle().set("margin", "10px");
        gridTipo.setHeight("700px");

        try{
            grid.setItems(service.leerPokemons());

        } catch (Exception ex) {
            Notification.show("Error al leer el Pokémon");
        }

        TextField datosTipo = new TextField("Introduce el Tipo");
        Button botonTipo = new Button("Buscar", e -> {
            String dato = datosTipo.getValue();
            try{
                gridTipo.setItems(service.leerPokemonPorTipo(dato));
            } catch (Exception ex) {
                Notification.show("Error al leer el Pokémon");
            }
        });
        botonTipo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botonTipo.addClickShortcut(Key.ENTER);
        //addClassName("centered-content");

        TextField datosNombre = new TextField("Introduce el Nombre");
        Button botonNombre = new Button("Buscar", e -> {
            String dato = datosNombre.getValue();
            try{
                listaPokemonsNombre.add(service.leerPokemonPorNombre(dato));
                gridNombre.setItems(listaPokemonsNombre);
            } catch (Exception ex) {
                Notification.show("Error al leer el Pokémon");
            }
        });
        botonNombre.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botonNombre.addClickShortcut(Key.ENTER);
        addClassName("stretch");

        inputsTipo.add(datosTipo, botonTipo);
        inputsNombre.add(datosNombre, botonNombre);
        inputsTipoV.add(inputsTipo,gridTipo);
        inputsNombreV.add(inputsNombre,gridNombre);


        pokemonTab = new Tab("General");
        tipoTab = new Tab("Buscar Por Tipo");
        nombreTab = new Tab("Buscar Por Nombre");
        Tabs tabSheet = new Tabs(pokemonTab, tipoTab, nombreTab);
        pokemonTab.getStyle().set("width", "34%");
        tipoTab.getStyle().set("width", "33%");
        nombreTab.getStyle().set("width", "33%");
        tabSheet.getStyle().set("width", "100%");
        tabSheet.addSelectedChangeListener(event ->
                setContent(event.getSelectedTab())
        );
        setContent(tabSheet.getSelectedTab());

        layout.add(titulos, tabSheet, content);
        add(layout);
    }

    private void setContent(Tab tab) {
        content.removeAll();

        if (tab.equals(pokemonTab)) {
            content.add(grid);
        } else if (tab.equals(tipoTab)) {
            content.add(inputsTipoV);
        } else if (tab.equals(nombreTab)){
            content.add(inputsNombreV);
        } else {
            content.add(new Paragraph("Seleccione una Pestaña."));
        }
    }

}
