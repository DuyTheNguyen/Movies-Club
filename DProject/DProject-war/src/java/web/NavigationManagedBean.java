/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author 101036886
 */
@Named(value = "navigationManagedBean")
@RequestScoped
public class NavigationManagedBean implements Serializable {

    /**
     * Creates a new instance of NavigationManagedBean
     */
    public NavigationManagedBean() {
    }
    
    public String MainMenuNavigation(String destination){
        String result = "error";
        switch(destination){
        case "your_account":
            result = goToYourAccount();
            break;
        case "your_ticket":
            result = goToYourTicket();
            break;
        case "list_of_showtime":
            result = goToListOfShowtime();
            break;
        default:
             result = goToErrorPage();
            break;
        }
        
        return result;
    }
    
    public String ShowtimeNavigation(String destination)
    {
        String result = "error";
        switch(destination){
            case "purchase":
                result = goToPurchase();
                break;
            default:
                result = goToErrorPage();
                break;
        }
        
        return result;
    }
    
    private String goToPurchase(){
        return "purchase";
    }
    
    private String goToYourAccount(){
        return "your_account";
    }
    
    private String goToYourTicket(){
        return "your_ticket";
    }
    
    private String goToListOfShowtime(){
        return "list_of_showtime";
    }
    
    private String goToErrorPage(){
        return "error";
    }
}
