package ua.com.goit.gojava7.kickstarter.flow;


import java.io.Serializable;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;


public class MyLoginAction extends FormAction {
    public MyLoginAction() {
        setFormObjectClass(FormObject.class);
    }
    public static class FormObject implements Serializable {
        private String user;
         private String password;
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
         public String getPassword(){
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }
    public Event processLogin(RequestContext context) throws Exception {
        FormObject formObject = (FormObject)getFormObject(context);
        // todo login logic
        return success();
    }
}