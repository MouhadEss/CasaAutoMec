package controller;

import Web.Util.UtilPath;
import bean.Client;
import bean.ImgCommande;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import service.ImgCommandeFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("imgCommandeController")
@SessionScoped
public class ImgCommandeController implements Serializable {

    @EJB
    private service.ImgCommandeFacade ejbFacade;
    private List<ImgCommande> items = null;
    private List<String> urls = new ArrayList();
    private ImgCommande selected;

    public ImgCommandeController() {
    }

    public void creerImg() {
            selected.setClient((Client) SessionUtil.getAttribute("connectedUser"));
            ejbFacade.create(selected , urls);
            urls = new ArrayList();
            selected = null;
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        System.out.println(event.getFile().getFileName());
        System.out.println(file.getFileName());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
        String nomImage = "" + ejbFacade.returnId() + "" + file.getFileName() + "";
        urls.add(nomImage);
        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "imgCommande" + File.separator + nomImage;
        try {
            InputStream in = (InputStream) file.getInputstream();
            FileOutputStream out = new FileOutputStream(pathDefinition);
            System.out.println(file.getFileName());

            byte[] buffer = new byte[(int) file.getSize()];
            int contador = 0;

            while ((contador = in.read(buffer)) != -1) {
                out.write(buffer, 0, contador);

            }
            System.out.println(file.getFileName());

            in.close();
            out.close();
            System.out.println(file.getFileName());

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "image uploader"));

        } catch (IOException ioe) {
            ioe.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "echec d'upload "));
            System.out.println(file.getFileName());
        }

    }

    public ImgCommande getSelected() {
        if (selected == null) {
            selected = new ImgCommande();
        }
        return selected;
    }

    public void setSelected(ImgCommande selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImgCommandeFacade getFacade() {
        return ejbFacade;
    }

    public ImgCommande prepareCreate() {
        selected = new ImgCommande();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ImgCommandeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ImgCommandeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ImgCommandeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImgCommande> getItems() {
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

    public ImgCommande getImgCommande(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<ImgCommande> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImgCommande> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImgCommande.class)
    public static class ImgCommandeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImgCommandeController controller = (ImgCommandeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imgCommandeController");
            return controller.getImgCommande(getKey(value));
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
            if (object instanceof ImgCommande) {
                ImgCommande o = (ImgCommande) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImgCommande.class.getName()});
                return null;
            }
        }

    }

}
