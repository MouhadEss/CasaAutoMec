package controller;

import bean.Passager;
import bean.Unite;
import bean.Ville;
import bean.Voyage;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.PassagerFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Polyline;
import service.CircuitVoyageFacade;
import service.VoyageFacade;

@Named("passagerController")
@SessionScoped
public class PassagerController implements Serializable {

    @EJB
    private service.PassagerFacade ejbFacade;
    @EJB
    private VoyageFacade voyageFacade;
    @EJB
    private CircuitVoyageFacade cicuitFacade;
    
    private List<Passager> items = null;
    private Passager selected;
    private Voyage voyageAchercher;
    private Voyage selectedVoyagedetaille;
    private int numberMin = 0;
    private int numberMax = 200;
    private int untiteBagage;
    private List<Voyage> voyages;
    private Unite uniteSelected;

    private MapModel polylineModel;

    @PostConstruct
    public void init() {
//        polylineModel = new DefaultMapModel();
//        Polyline polyline = new Polyline();
//        //Shared coordinates
////        LatLng coord1 = new LatLng(36.879466, 30.667648);
////        LatLng coord2 = new LatLng(36.883707, 30.689216);
////        LatLng coord3 = new LatLng(36.879703, 30.706707);
////        LatLng coord4 = new LatLng(36.885233, 30.702323);
//        System.out.println(selectedVoyagedetaille);
//        List<Ville> villeCircuits = getCicuitFacade().getVilleCircuitByVoyage(selectedVoyagedetaille);
//        System.out.println(villeCircuits);
//        for (Ville villeCircuit : villeCircuits) {
//            polyline.getPaths().add(
//                    new LatLng(new Double(villeCircuit.getLat()),
//                            new Double(villeCircuit.getLang())
//                    ));
//        }
//        //Polyline
//
////        polyline.getPaths().add(coord1);
////        polyline.getPaths().add(coord2);
////        polyline.getPaths().add(coord3);
////        polyline.getPaths().add(coord4);
//        polyline.setStrokeWeight(10);
//        polyline.setStrokeColor("#FF9900");
//        polyline.setStrokeOpacity(0.7);
//
//        polylineModel.addOverlay(polyline);
    }

    public MapModel getPolylineModel() {

        return polylineModel;
    }

    public PassagerController() {
    }

//    public String chercherVoyage() {
//        System.out.println(voyageAchercher);
//        return "resultat.xhtml";
//    }
    public void resultatRecherche() {

        System.out.println("le max " + numberMax + ",le Min " + numberMin + "");
        voyages = voyageFacade.chercherInVoyage(voyageAchercher.getVilleDepart(),
                voyageAchercher.getVilleArriver(),
                voyageAchercher.getDateVoyage(),
                new Double("" + getNumberMax()), new Double("" + getNumberMin()));

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("resultaForm");
        System.out.println(voyages);
//            return "/passager/Resultat?faces-redirect=true"; 
    }

    public int getUntiteBagage() {
        return untiteBagage;
    }

    public void setUntiteBagage(int untiteBagage) {
        this.untiteBagage = untiteBagage;
    }

    public Unite getUniteSelected() {
        if (uniteSelected == null) {
            uniteSelected = new Unite();
        }
        return uniteSelected;
    }

    public void setUniteSelected(Unite unite) {
        this.uniteSelected = unite;
    }

    public CircuitVoyageFacade getCicuitFacade() {
        return cicuitFacade;
    }

    public void setCicuitFacade(CircuitVoyageFacade cicuitFacade) {
        this.cicuitFacade = cicuitFacade;
    }

    public List<Voyage> getVoyages() {
        return voyages;
    }

    public void setVoyages(List<Voyage> voyages) {
        this.voyages = voyages;
    }

    public PassagerFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PassagerFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Voyage getSelectedVoyagedetaille() {
        if (selectedVoyagedetaille == null) {
            selectedVoyagedetaille = new Voyage();
        }
        return selectedVoyagedetaille;
    }

    public void setSelectedVoyagedetaille(Voyage selectedVoyagedetaille) {
        this.selectedVoyagedetaille = selectedVoyagedetaille;
    }

    public Voyage getVoyageAchercher() {
        if (voyageAchercher == null) {
            voyageAchercher = new Voyage();
        }
        return voyageAchercher;
    }

    public void setVoyageAchercher(Voyage voyageAchercher) {
        this.voyageAchercher = voyageAchercher;
    }

    public VoyageFacade getVoyageFacade() {
        return voyageFacade;
    }

    public void setVoyageFacade(VoyageFacade voyageFacade) {
        this.voyageFacade = voyageFacade;
    }

    public int getNumberMin() {
        return numberMin;
    }

    public void setNumberMin(int numberMin) {
        this.numberMin = numberMin;
    }

    public int getNumberMax() {
        return numberMax;
    }

    public void setNumberMax(int numberMax) {
        this.numberMax = numberMax;
    }

    public Passager getSelected() {
        return selected;
    }

    public void setSelected(Passager selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PassagerFacade getFacade() {
        return ejbFacade;
    }

    public Passager prepareCreate() {
        selected = new Passager();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PassagerCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PassagerUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PassagerDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Passager> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Passager getPassager(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Passager> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Passager> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Passager.class)
    public static class PassagerControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PassagerController controller = (PassagerController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "passagerController");
            return controller.getPassager(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Passager) {
                Passager o = (Passager) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Passager.class.getName()});
                return null;
            }
        }

    }

}
