package org.ieschabas.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@PageTitle("Main")
@Route(value = "")
public class MainView extends AppLayout{
	 LoginForm loginForm = new LoginForm();

	 public MainView() {
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
	        tabs.add(createTabEquipo(VaadinIcon.USER, "Actor", "ACTOR"),
	        createTabEquipo(VaadinIcon.USER, "Director", "DIRECTOR"),
	        createTabPelicula(VaadinIcon.FILM, "Peliculas"),
	        createTabAlquiler(VaadinIcon.SHOP, "Alquiler"));
	        tabs.setOrientation(Tabs.Orientation.VERTICAL);
	        return tabs;
	    }


	    private Tab createTabPelicula(VaadinIcon viewIcon, String viewName) {
	        Icon icon = viewIcon.create();
	        icon.getStyle().set("box-sizing", "border-box")
	                .set("margin-inline-end", "var(--lumo-space-m)")
	                .set("margin-inline-start", "var(--lumo-space-xs)")
	                .set("padding", "var(--lumo-space-xs)");

	        RouterLink link = new RouterLink();
	        link.add(icon, new Span(viewName));
	        // Demo has no routes
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


	    private Tab createTabAlquiler(VaadinIcon viewIcon, String viewName) {
	        Icon icon = viewIcon.create();
	        icon.getStyle().set("box-sizing", "border-box")
	                .set("margin-inline-end", "var(--lumo-space-m)")
	                .set("margin-inline-start", "var(--lumo-space-xs)")
	                .set("padding", "var(--lumo-space-xs)");

	        RouterLink link = new RouterLink();
	        link.add(icon, new Span(viewName));
	        // Demo has no routes
	        link.setRoute(ListadoAlquilerView.class);
	        link.setTabIndex(-1);

	        return new Tab(link);
	    }



}




















