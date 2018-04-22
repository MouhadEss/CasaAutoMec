package controller;

import bean.Message;
import bean.MessageDetail;
import controller.util.JsfUtil;
import controller.util.JsfUtil.PersistAction;
import service.MessageDetailFacade;

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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.MessageFacade;

@Named("messageDetailController")
@SessionScoped
public class MessageDetailController implements Serializable {

    @EJB
    private service.MessageDetailFacade ejbFacade;
    @EJB
    private service.MessageFacade messageFacad;
    @EJB
    private service.PersonneFacade personneFacad;
    private List<MessageDetail> items = new ArrayList();
    private MessageDetail selected;
    private Message selectedMessage;

    public MessageDetailController() {
    }

    public void addListSelect() {
        items.add(ejbFacade.clonage(selected));
    }
    
    public void createMessage(){
        getSelectedMessage().setPersonne(personneFacad.find("test@gmail.com"));
        getMessageFacad().createMessage(selectedMessage, items);
        
    }

    public Message getSelectedMessage() {
        if(selectedMessage==null)
            selectedMessage = new Message();
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }
    
    public MessageDetailFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(MessageDetailFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public MessageFacade getMessageFacad() {
        return messageFacad;
    }

    public void setMessageFacad(MessageFacade messageFacad) {
        this.messageFacad = messageFacad;
    }

    
    public MessageDetail getSelected() {
        if (selected == null) {
            selected = new MessageDetail();
        }
        return selected;
    }

    public void setSelected(MessageDetail selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MessageDetailFacade getFacade() {
        return ejbFacade;
    }

    public MessageDetail prepareCreate() {
        selected = new MessageDetail();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MessageDetailCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MessageDetailUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MessageDetailDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MessageDetail> getItems() {
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

    public MessageDetail getMessageDetail(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<MessageDetail> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MessageDetail> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MessageDetail.class)
    public static class MessageDetailControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MessageDetailController controller = (MessageDetailController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "messageDetailController");
            return controller.getMessageDetail(getKey(value));
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
            if (object instanceof MessageDetail) {
                MessageDetail o = (MessageDetail) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MessageDetail.class.getName()});
                return null;
            }
        }

    }

}
