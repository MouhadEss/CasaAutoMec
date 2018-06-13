package controller;

import Web.Util.UtilPath;
import bean.Client;
import bean.Commande;
import bean.CommandeItem;
import bean.ImageProduit;
import bean.Produit;
import bean.TypeProduit;
import bean.TypeVehicule;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import controller.util.SessionUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import service.ProduitFacade;

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
import static jdk.nashorn.internal.objects.NativeString.search;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import service.TypeProduitFacade;
import service.TypeVehiculeFacade;

@Named("produitController")
@SessionScoped
public class ProduitController implements Serializable {

    @EJB
    private service.ProduitFacade ejbFacade;
    @EJB
    private service.TypeVehiculeFacade typeVFacade;
    @EJB
    private service.TypeProduitFacade typePFacade;
    @EJB
    private service.ImageProduitFacade imgPFacade;
    @EJB
    private service.CommandeItemFacade ejbCommande;
    @EJB
    private service.CommandeFacade ejbCmd;

    private List<Produit> items = null;
    private List<ImageProduit> iMproduits = new ArrayList();
    private List<String> produitsimg = new ArrayList();
    private List<TypeProduit> itemsP = null;
    private List<TypeVehicule> itemsV = null;
    private Produit selected;
    private Produit justcreated;
    private TypeProduit typeP;
    private ImageProduit imgP;
    private TypeVehicule typeV;
    private boolean disabled = false;
    private boolean uploadp = true;
    private boolean readonly = false;
    private boolean readonlyp = false;
    private int i = 0;
    private int j = 0;
    private List<Produit> itemsO = new ArrayList();
    private List<CommandeItem> itemsC = new ArrayList();
    private List<Commande> itemsCmd = new ArrayList();
    private String search;

    public ProduitController() {
    }
    
    public List<ImageProduit> remplirImg(Produit produit){
        return iMproduits=ejbFacade.findImgs(produit);
    }

    public void findTypePbyV() {
        if (typeV != null) {
            itemsP = typePFacade.findTypeProduitsbyVehicule(typeV);
            typeV = null;
        } else {
            itemsP = typePFacade.findAll();
        }
    }

    public void supprimerProduit(Produit p) {
        System.out.println("hana");
        ejbFacade.suprimerProduit(p);
        selected = null;
        readonlyp = false;
        i = 0;
        items = ejbFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Produit supprimer avec succes"));
    }

    public void editProduit(Produit produit) {
        if (i == 0) {
            selected = produit;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "editer ce produit"));
            i = 1;
            readonlyp = true;
        } else if (i == 1) {
            i = 0;
            selected = null;
            readonlyp = false;
        }
    }

    public void creerImg() {
        if (j == 1) {
            imgPFacade.create(produitsimg, "jpg", justcreated);
            j = 0;
            produitsimg = new ArrayList();
            justcreated = null;
            uploadp = true;
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        System.out.println(event.getFile().getFileName());
        System.out.println(file.getFileName());
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
        String nomImage = "" + imgPFacade.returnId() + "" + justcreated.getReference() + "" + file.getFileName() + "";
        String url =nomImage ;
        System.out.println(url);
        produitsimg.add(url);
        j = 1;
        System.out.println(produitsimg);
        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "imgProduit" + File.separator + nomImage;
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
        creerImg();

    }

    public void createP() {
        if (i == 0) {
            if (!selected.getReference().equals("")) {
                ejbFacade.create(selected);
                justcreated = selected;
                selected = null;
                typeV = null;
                uploadp = false;
                items = ejbFacade.findAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Produit creer avec succes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Entrer la reference du produit"));
            }
        } else {
            ejbFacade.editerProduit(selected);
            selected = null;
            items = ejbFacade.findAll();
            i = 0;
            readonlyp = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Produit modifier avec succes"));
        }
    }

    public void createV() {
        if (i == 0) {
            if (!typeV.getVehicule().equals("")) {
                typeVFacade.create(typeV);
                typeV = null;
                itemsV = typeVFacade.findAll();
                itemsP = typePFacade.findAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type vehicule creer avec succes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Entrer le nom du type"));
            }
        } else {
            if (!typeV.getVehicule().equals("")) {
                typeVFacade.editerV(typeV);
                typeV = null;
                itemsV = typeVFacade.findAll();
                i = 0;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type vehicule modifier avec succes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Entrer le nom du type"));
            }
        }
    }

    public void createTP() {
        if (i == 0) {
            if (!typeP.getType().equals("")) {
                typePFacade.create(typeP);
                typeP = null;
                itemsP = typePFacade.findAll();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type produit creer avec succes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Entrer le nom du type"));
            }
        } else {
            if (!typeP.getType().equals("")) {
                typePFacade.editeP(typeP);
                typeP = null;
                itemsP = typePFacade.findAll();
                i = 0;
                disabled = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type produit modifier avec succes"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Entrer le nom du type"));
            }
        }
    }

    public void supprimer() {
        typeVFacade.supprimer(typeV);
        typeV = null;
        itemsV = typeVFacade.findAll();
        itemsP = typePFacade.findAll();
        items = ejbFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type vehicule supprimer avec succes"));
    }

    public void supprimerTP() {
        typePFacade.supprimer(typeP);
        typeP = null;
        itemsP = typePFacade.findAll();
        items = ejbFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type produit supprimer avec succes"));
    }

    public void editerV() {
        if (typeV != null) {
            if (i == 0) {
                i = 1;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type vehicule a editer"));
            } else {
                i = 0;
                typeV = null;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "selection un type"));
        }

    }

    public void editerP() {
        if (typeP != null) {
            if (i == 0) {
                i = 1;
                disabled = true;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "sucess", "Type produit a editer"));
            } else {
                i = 0;
                typeP = null;
                disabled = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "selection un type"));
        }

    }

    public String page(TypeProduit typeP) {
        itemsO = ejbFacade.findByType(typeP.getType(), typeP.getTypeVehicule().getVehicule());
        SessionUtil.setAttribute("type", itemsO);
        return "/faces/produit/Catalogue?faces-redirect=true.xhtml";
    }
    
    public String pageIndex(TypeProduit typeP) {
        itemsO = ejbFacade.findByType(typeP.getType(), typeP.getTypeVehicule().getVehicule());
        SessionUtil.setAttribute("type", itemsO);
        return "/faces/Cata?faces-redirect=true.xhtml";
    }

    public void cart(Produit p) {
        CommandeItem cmd = new CommandeItem();
        cmd.setProduit(p);
        itemsC.add(cmd);
        System.out.println(itemsC);
    }

    public String createCmd() {
        Commande cmd = new Commande();
        cmd.setClient((Client) SessionUtil.getAttribute("connectedUser"));
        System.out.println(cmd);
        ejbCommande.createCItem(cmd, itemsC);
        itemsC=new ArrayList();
        return "/faces/commande/HistoriqueCommande?faces-redirect=true.xhtml";
    }

    public void deleteFromCart(CommandeItem cmdItem) {
        List<CommandeItem> cs = new ArrayList();
        for (CommandeItem commandeItem : itemsC) {
            if (!commandeItem.getProduit().getReference().equals(cmdItem.getProduit().getReference())) {
                cs.add(commandeItem);
            }
        }
        itemsC = cs;
    }

    public void historique() {
        itemsCmd = ejbCmd.findByClient((Client) SessionUtil.getAttribute("connectedUser"));
    }

    public void searchProduct() {
        items = ejbFacade.findByDesc(search);
    }

    public List<ImageProduit> getiMproduits() {
        return iMproduits;
    }

    public void setiMproduits(List<ImageProduit> iMproduits) {
        this.iMproduits = iMproduits;
    }
    
    

    public List<Commande> getItemsCmd() {
        return itemsCmd;
    }

    public void setItemsCmd(List<Commande> itemsCmd) {
        this.itemsCmd = itemsCmd;
    }

    public List<CommandeItem> getItemsC() {
        return itemsC;
    }

    public void setItemsC(List<CommandeItem> itemsC) {
        this.itemsC = itemsC;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Produit> getItemsO() {
        if (itemsO == null) {
            itemsO = (List<Produit>) SessionUtil.getAttribute("type");
        }
        return itemsO;
    }

    public void setItemsO(List<Produit> itemsO) {
        this.itemsO = itemsO;
    }

    public Produit getSelected() {
        if (selected == null) {
            selected = new Produit();
        }
        return selected;
    }

    public void setSelected(Produit selected) {
        this.selected = selected;
    }

    public Produit getJustcreated() {
        if (justcreated == null) {
            justcreated = new Produit();
        }
        return justcreated;
    }

    public void setJustcreated(Produit justcreated) {
        this.justcreated = justcreated;
    }

    public List<TypeProduit> getItemsP() {
        if (itemsP == null) {
            itemsP = typePFacade.findAll();
        }
        return itemsP;
    }

    public void setItemsP(List<TypeProduit> itemsP) {
        this.itemsP = itemsP;
    }

    public List<TypeVehicule> getItemsV() {
        if (itemsV == null) {
            itemsV = typeVFacade.findAll();
        }
        return itemsV;
    }

    public void setItemsV(List<TypeVehicule> itemsV) {
        this.itemsV = itemsV;
    }

    public TypeProduit getTypeP() {
        if (typeP == null) {
            typeP = new TypeProduit();
        }
        return typeP;
    }

    public void setTypeP(TypeProduit typeP) {
        this.typeP = typeP;
    }

    public TypeVehicule getTypeV() {
        if (typeV == null) {
            typeV = new TypeVehicule();
        }
        return typeV;
    }

    public void setTypeV(TypeVehicule typeV) {
        this.typeV = typeV;
    }

    public ImageProduit getImgP() {
        if (imgP == null) {
            imgP = new ImageProduit();
        }
        return imgP;
    }

    public void setImgP(ImageProduit imgP) {
        this.imgP = imgP;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public boolean isUploadp() {
        return uploadp;
    }

    public void setUploadp(boolean uploadp) {
        this.uploadp = uploadp;
    }

    public boolean isReadonlyp() {
        return readonlyp;
    }

    public void setReadonlyp(boolean readonlyp) {
        this.readonlyp = readonlyp;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProduitFacade getFacade() {
        return ejbFacade;
    }

    public TypeVehiculeFacade getVFacade() {
        return typeVFacade;
    }

    public TypeProduitFacade getPFacade() {
        return typePFacade;
    }

    public Produit prepareCreate() {
        selected = new Produit();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProduitCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProduitUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProduitDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Produit> getItems() {
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

    public Produit getProduit(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Produit> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Produit> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Produit.class)
    public static class ProduitControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProduitController controller = (ProduitController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "produitController");
            return controller.getProduit(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Produit) {
                Produit o = (Produit) object;
                return getStringKey(o.getReference());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Produit.class.getName()});
                return null;
            }
        }

    }

}
