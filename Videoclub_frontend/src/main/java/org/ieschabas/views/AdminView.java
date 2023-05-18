package org.ieschabas.views;



import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
@Route("admin-view")
// tag::snippet[]
public class AdminView extends AppLayout {


    public AdminView() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        Tabs tabs = getTabs();

        addToDrawer(tabs);
        addToNavbar(toggle, title);




    }
    // end::snippet[]

    private Tabs getTabs() {
        Tabs tabs = new Tabs();
        tabs.add(createTab(VaadinIcon.DASHBOARD, "Películas"),
                createTabEquipo(VaadinIcon.CART, "Actores","actor"),
                createTabEquipo(VaadinIcon.USER_HEART, "Directores","director"),
                createTab(VaadinIcon.PACKAGE, "Alquileres"));
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        return tabs;


    }

    private Tab createTab(VaadinIcon viewIcon, String viewName) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(ListadoPeliculasView.class);
        link.setTabIndex(-1);

        return new Tab(link);
    }

    private Tab createTabEquipo(VaadinIcon viewIcon, String viewName,String rol) {
        Icon icon = viewIcon.create();
        icon.getStyle().set("box-sizing", "border-box")
                .set("margin-inline-end", "var(--lumo-space-m)")
                .set("margin-inline-start", "var(--lumo-space-xs)")
                .set("padding", "var(--lumo-space-xs)");

        RouterLink link = new RouterLink();
        link.add(icon, new Span(viewName));
        link.setRoute(ListadoEquipoView.class,rol);
        link.setTabIndex(-1);

        return new Tab(link);
    }

      // tag::snippet[]






}
// end::snippet[]

